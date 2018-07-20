package spring.database.jpa.orm2;

import java.io.File;
import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;

import spring.database.jpa.orm2.model.NonTeachingStaff;
import spring.database.jpa.orm2.model.Product;
import spring.database.jpa.orm2.model.TeachingStaff;
import spring.database.jpa.orm2.model.TeachingStaffChild;
import spring.database.jpa.orm2.SpringDataJPAMain;
import spring.database.jpa.orm2.service.ProductService;
import spring.database.jpa.orm2.config.AppConfig;

import java.lang.reflect.*;

public class SpringDataJPAMain {

	private final static Logger logger = Logger.getLogger(SpringDataJPAMain.class);

	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException {

		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");

		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

		// Get service from context.
		ProductService productService = ctx.getBean(ProductService.class);

		productService.add(new Product(1, "Television"));
		productService.add(new Product(2, "Phone"));
		productService.addAll(Arrays.asList(new Product(3, "Peach"), new Product(4, "Strawberry"),
				new Product(5, "Melone"), new Product(6, "Onion")));

		// Test entity listing
		logger.debug("findAll=" + productService.findAll());

		// Test specified find methods
		logger.debug("findByName is 'Peach': " + productService.findByNameIs("Peach"));
		logger.debug("findByNameContainingIgnoreCase 'on': " + productService.findByNameContainingIgnoreCase("on"));

		Product product = new Product();
		product.setId(1);
		product.setName("nama");

		System.out.println(
				"=====================================================================================================");
		for (Field field : product.getClass().getDeclaredFields()) {
			field.setAccessible(true);
			String name = field.getName();
			Object val = field.get(product);

			logger.debug(name + " : " + val);
		}
		System.out.println(
				"=====================================================================================================");

		

//		EntityManagerFactory emFactory = (EntityManager) Persistence.createEntityManagerFactory("Test");
//		EntityManager entitymanager = ((EntityManagerFactory) emFactory).createEntityManager();
		
		EntityManagerFactory emFactory = ctx.getBean("entityManagerFactory",EntityManagerFactory.class);
		
		EntityManager entityManager = emFactory.createEntityManager();
		entityManager.getTransaction().begin();

		// Teaching staff entity
		TeachingStaff ts1 = new TeachingStaff("Gopal", "MSc MEd", "Maths");
		TeachingStaff ts2 = new TeachingStaff("Manisha", "BSc BEd", "English");

//		TeachingStaffChild ts1 = new TeachingStaffChild("Gopal", "MSc MEd", "Maths",3);
//		TeachingStaffChild ts2 = new TeachingStaffChild("Manisha", "BSc BEd", "English",10);
		
		// Non-Teaching Staff entity
		NonTeachingStaff nts1 = new NonTeachingStaff("Satish", "Accounts");
		NonTeachingStaff nts2 = new NonTeachingStaff("Krishna", "Office Admin");

		// storing all entities
		entityManager.persist(ts1);
		entityManager.persist(ts2);
		entityManager.persist(nts1);
		entityManager.persist(nts2);

		entityManager.getTransaction().commit();
		entityManager.close();
		
		ctx.close();
	}

}
