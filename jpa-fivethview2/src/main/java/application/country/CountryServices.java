package application.country;

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
			JPAUtils.closeEntityManager();
		}
		return country;
	}
}
