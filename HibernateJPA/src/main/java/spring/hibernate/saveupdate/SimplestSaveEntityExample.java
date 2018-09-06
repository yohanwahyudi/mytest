package spring.hibernate.saveupdate;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class SimplestSaveEntityExample {
	private static final Logger logger = LogManager.getLogger(SimplestSaveEntityExample.class);

	public static void main(String[] args) {
//		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");

		// Session sessionOne = HibernateUtil.getSessionFactory().openSession();
		SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

		try {
			Session sessionOne = sessionFactory.openSession();
			sessionOne.beginTransaction();

			// Create new Employee object
			EmployeeEntity emp = new EmployeeEntity();
			emp.setEmployeeId(1);
			emp.setFirstName("Lokesh");
			emp.setLastName("Gupta");

			// Save employee
			sessionOne.save(emp);

			sessionOne.getTransaction().commit();

			// Session sessionTwo = HibernateUtil.getSessionFactory().openSession();

			Session sessionTwo = sessionFactory.openSession();
			sessionTwo.beginTransaction();

			emp.setLastName("Temp");

			sessionTwo.saveOrUpdate(emp);
			sessionTwo.getTransaction().commit();
			
			sessionOne.close();
			sessionTwo.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				sessionFactory.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
}
