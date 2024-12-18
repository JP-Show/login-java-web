package application;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import application.utils.JPAUtils;

@WebListener
public class Main implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("fechou");
		JPAUtils.closeEntityManagerFactory();
		ServletContextListener.super.contextDestroyed(sce);
	}
}
