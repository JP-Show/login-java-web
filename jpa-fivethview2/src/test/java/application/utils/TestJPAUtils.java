package application.utils;

import static application.users.UsersServices.deleteById;
import static application.users.UsersServices.findById;
import static application.users.UsersServices.findByName;
import static application.users.UsersServices.insertUser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.junit.jupiter.api.Test;

import application.country.Country;
import application.country.CountryServices;
import application.users.User;

class TestJPAUtils {
	private static EntityManagerFactory emf;
	private static ThreadLocal<EntityManager> tem = new ThreadLocal<EntityManager>();

	void sameObject() {

		if (emf == null)
			emf = Persistence.createEntityManagerFactory("mypu");

		EntityManager em = tem.get();
		if (em == null || !em.isOpen()) {
			em = emf.createEntityManager();
			tem.set(em);
		}
		System.out.println(em);
		System.out.println(tem.get());
	}

	void secondSameObject() {
		System.out.println(tem.get());
	}
	
	void testFindById() {
		try {
			User user = findById(2);
			System.out.println(user.getName());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}
	
	void testFindByName() {
		List<User> users = findByName("");
		for (User user : users) {
			System.out.println(user.getCreateAt().getZone());	
		}
	}

	void testEncriptPassword() {
//		System.out.println("Test");
//		String senha = "";
//		senha = criptPassword("SenhaSecreta02");
//		System.out.println(senha);
	}
	@Test
	void testInsertUser() {
		LocalDateTime ldt = LocalDateTime.now().minusHours(1);
		ZonedDateTime zdt = ZonedDateTime.of(ldt, ZoneId.of("Etc/GMT-8"));
		Country hongKong = CountryServices.findById("hong kong");
		Country unitedKingdon = CountryServices.findById("United Kingdom");
		Country brazil = CountryServices.findById("Brazil");
		
		User user = new User("Ayumi", LocalDate.of(2002, 10, 29),  zdt, zdt, "JesusMyLord!312", hongKong);
		
		ZonedDateTime zdt2 = ZonedDateTime.of(ldt, ZoneId.of("Etc/GMT-0"));
		User user2 = new User("johnathan", LocalDate.of(1974, 1, 4),  zdt2, zdt2, "JesusMyLord!312", unitedKingdon);
		
		ZonedDateTime zdt3 = ZonedDateTime.of(ldt, ZoneId.of("Etc/GMT+3"));
		User user3 = new User("Gustavo", LocalDate.of(2000, 12, 23),  zdt3, zdt3, "JesusMyLord!312", brazil);
		
		insertUser(user, ZoneId.of("Etc/GMT-9"));
		insertUser(user2, ZoneId.of("Etc/GMT-0"));
		insertUser(user3, ZoneId.of("Etc/GMT+3"));
	}
	
	
	void testFindCountry(){
		Country hongKong = CountryServices.findById("hong kong");
	}
	
	void testDeleta() {
		deleteById(1);
	}
	
	void testDataConvert() {
		
	}
}
