package application.users;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;

import application.users.exceptions.UsersExceptions;
import application.utils.JPAUtils;

public class UsersServices {

	
	public static void insertUser(User user, ZoneId timezone) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			validateUser(user, timezone);
			String encryptPassword = encryptPassword(user.getPassword());
			user.setPassword(encryptPassword);
			tx.begin();
			em.persist(user);
			tx.commit();
		} catch (UsersExceptions e) {
			tx.rollback();
			throw new UsersExceptions(e.getMessage());
		}catch (RuntimeException e){
			tx.rollback();
			throw new RuntimeException(e.getMessage(), e.getCause());
		} finally {
			if (em.isOpen())
				em.close();
		}
	}

	public static List<User> findByName(String name) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		List<User> list;
		try {
			list = em.createQuery("SELECT u FROM User u where u.name like '%" + name + "%'", User.class)
					.getResultList();
			if (list.isEmpty())
				throw new UsersExceptions("Not found: " + name);
		} catch (Exception e) {
			throw new UsersExceptions(e.getMessage());
		} finally {
			if (em.isOpen())
				em.close();
		}
		return list;
	}
	public static User findByNameUnique(String name) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		User user = null;
		try {
			user = em.createQuery("SELECT u FROM User u where u.name like '%" + name + "%'", User.class).getSingleResult();
			if (user == null)
				throw new UsersExceptions("Not found: " + name);
		} catch (Exception e) {
			throw new UsersExceptions(e.getMessage());
		} finally {
			if (em.isOpen())
				em.close();
		}
		return user;
	}

	public static User findById(long id) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		User user = null;
		try {
			user = em.createQuery("SELECT u FROM User u WHERE u.id = " + id, User.class).getSingleResult();
			if (user == null)
				throw new UsersExceptions("Not found user by id:" + id);
		} catch (Exception e) {
			throw new UsersExceptions(e.getMessage());
		} finally {
			JPAUtils.closeEntityManager();
		}
		return user;
	}

	public static User deleteById(long id) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		User user = null;
		EntityTransaction tx = em.getTransaction();
		try {
			user = em.createQuery("SELECT u FROM User u WHERE u.id = " + id, User.class).getSingleResult();
			if (user == null)
				throw new UsersExceptions("Not found user by id:" + id);
			tx.begin();
			em.remove(user);
			tx.commit();
		} catch (Exception e) {
			throw new UsersExceptions("- ERROR DELETE BY ID - \n" + e.getMessage());
		} finally {
			JPAUtils.closeEntityManager();
		}
		return user;
	}

	public static boolean updateName(String name, long id, ZonedDateTime zdt) throws UsersExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		boolean updated = false;
		EntityTransaction tx = em.getTransaction();
		try {
			String sql = "UPDATE User SET name = :name, lastUpdate = :lastUpdate WHERE id = :id";
			tx.begin();
			em.createQuery(sql).setParameter("name", name).setParameter("lastUpdate", zdt).setParameter("id", id).executeUpdate();
			tx.commit();
			updated = true;
		} catch (Exception e) {
			tx.rollback();
			throw new UsersExceptions("- ERROR Updating - \n" + e.getMessage());
		} finally {
			JPAUtils.closeEntityManager();
		}
		return updated;
	}

	private static String encryptPassword(String senha) throws RuntimeException {
		String pass = "";
		try {
			Argon2PasswordEncoder argonEncoder = new Argon2PasswordEncoder(16, 64, 2, 128000, 10);
			pass = argonEncoder.encode(senha);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage());
		}
		return pass;
	}
	
	private static void validateUser (User user, ZoneId timezone) throws UsersExceptions {
		if (user == null) throw new NullPointerException("Error creating user: User can't be empty");
		
		//password test
		passwordTest(user.getPassword());
		
		//Name test
		if(user.getName().length() > 100) throw new UsersExceptions("Error creating user: The name can't be more than 100 characters");

		//birth date Test
		if(user.getBirthDate() == null) throw new UsersExceptions("Error creating user: BirthDate can't be empty");
		Clock clock = Clock.system(timezone);
		ChronoLocalDate cld = LocalDate.now(clock);
		if(user.getBirthDate().isAfter(cld)) throw new UsersExceptions("Error creating user: Your BirthDate" + user.getBirthDate() + "is after your current date " + cld.toString());
	}
	
	private static void passwordTest(String password) {
		if(password == null || password == "") throw new UsersExceptions("Error creating user: password can't be empty");
		if(password.length() < 15) throw new UsersExceptions("Error creating user: password can't be less than 15 characters");
	}
}
