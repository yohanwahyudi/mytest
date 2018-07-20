package spring.database.jpa.orm3;

import java.io.File;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.orm3.config.ApplicationConfig;
import spring.database.jpa.orm3.model.T3;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class);

	public static void main(String args[]) {
		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		EntityManagerFactory emFactory = ctx.getBean("entityManagerFactory", EntityManagerFactory.class);
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		T3 t3 = new T3("str1_Test2", "str2_Test2","str3_Test2","str4_Test2");
		
		entityManager.persist(t3);

		entityManager.getTransaction().commit();
		entityManager.close();
		
		ctx.close();


	}

}
