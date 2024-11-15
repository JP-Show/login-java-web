package application.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public final class JPAUtils {
	private static final String PERSISTENCE_UNIT = "mypu";
	private static EntityManagerFactory emf;
	private static ThreadLocal<EntityManager> tem = new ThreadLocal<EntityManager>();
	
	public static EntityManager getEntityManager() {
		if(emf == null) emf = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		
		EntityManager em = tem.get();
		if(em == null || !em.isOpen()) {
			em = emf.createEntityManager();
			tem.set(em);
		}
		return em;
	}
	public static void closeEntityManager() {
		EntityManager em = tem.get();
		
		if(em != null) {
			EntityTransaction tx = em.getTransaction();
			
			if(tx.isActive()) tx.commit();
			em.close();
			
			tem.set(null);
		}
	}
	public static void closeEntityManagerFactory() {
		closeEntityManager();
		emf.close();
	}
}
