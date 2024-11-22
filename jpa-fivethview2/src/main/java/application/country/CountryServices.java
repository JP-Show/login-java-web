package application.country;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import application.country.exceptions.CountryExceptions;
import application.utils.JPAUtils;

public class CountryServices {
	public static Country findById(String name) throws CountryExceptions {
		EntityManager em = JPAUtils.getEntityManager();
		Country country = null;
		try {
			country = em.createQuery("SELECT c FROM Country c WHERE LOWER(c.name) = LOWER('" + name +"')", Country.class).getSingleResult();
			if (country == null)
				throw new CountryExceptions("Not found country by name: " + name);
		} catch (Exception e) {
			throw new CountryExceptions(e.getMessage());
		} finally {
			if (em.isOpen())
				em.close();
		}
		return country;
	}
	public static List<Country> findAll() {
		EntityManager em = JPAUtils.getEntityManager();
		List<Country> countries = new ArrayList<>();
		try {
			countries = em.createQuery("SELECT c FROM Country c ORDER BY c.name", Country.class).getResultList();
		} catch (Exception e) {
			throw new CountryExceptions(e.getMessage());
		} finally {
			if (em.isOpen())
				em.close();
		}
		return countries;
	}
}
