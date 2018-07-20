package spring.database.jpa.orm.main;

import java.io.File;
import java.util.Arrays;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import spring.database.jpa.orm.model.Product;
import spring.database.jpa.orm.service.ProductService;

public class SpringDataJPAMain {

	private final static Logger logger = Logger.getLogger(SpringDataJPAMain.class);

	public static void main(String[] args) {

		DOMConfigurator.configure(System.getProperty("user.dir") + File.separator + "log4j.xml");

		// Create Spring application context
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:/spring.xml");

		// Get service from context.
		ProductService productService = ctx.getBean(ProductService.class);

		// Add some items
		productService.add(new Product(1, "Television"));
		productService.add(new Product(2, "Phone"));
		productService.addAll(Arrays.asList(new Product(3, "Peach"), new Product(4, "Strawberry"),
				new Product(5, "Melone"), new Product(6, "Onion")));

		// Test entity listing
		logger.debug("findAll=" + productService.findAll());

		// Test specified find methods
		logger.debug("findByName is 'Peach': " + productService.findByNameIs("Peach"));
		logger.debug("findByNameContainingIgnoreCase 'on': " + productService.findByNameContainingIgnoreCase("on"));

		ctx.close();

	}

}
