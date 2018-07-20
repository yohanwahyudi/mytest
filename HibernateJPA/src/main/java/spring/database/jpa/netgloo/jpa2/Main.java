package spring.database.jpa.netgloo.jpa2;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.netgloo.jpa2.config.ApplicationConfig;
import spring.database.jpa.netgloo.jpa2.dao.CustomerRepository;
import spring.database.jpa.netgloo.jpa2.dao.CustomerService;
import spring.database.jpa.netgloo.jpa2.models.Customer;
import spring.database.jpa.netgloo.jpa2.Main;

public class Main {

	private final static Logger logger = Logger.getLogger(Main.class);
	
//	@Autowired
	private static CustomerService customerService;

	public static void main(String args[]) {
		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");

		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);

//		logger.debug(customerService.findCustomerById((long) 1));
//		customerService.saveCustomer(new Customer("a","b"));
//		customerService.print();
		
		customerService = context.getBean("customerService",CustomerService.class);
		customerService.print();
		
		context.close();
	}

}
