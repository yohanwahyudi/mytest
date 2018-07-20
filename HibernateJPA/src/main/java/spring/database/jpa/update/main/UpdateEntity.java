package spring.database.jpa.update.main;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.update.ApplicationConfig;
import spring.database.jpa.update.Product;
import spring.database.jpa.update.service.ProductService;

public class UpdateEntity {

	private final static Logger logger = LogManager.getLogger(MainPopulate.class);
	private static AnnotationConfigApplicationContext ctx;

	public static void main(String args[]) {

		ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		ProductService productService = ctx.getBean("productService", ProductService.class);

		Product product = productService.find(3);
		System.out.println("before feature: " + product.isFeatured() + " product id: "+product.getId());
		product.setFeatured(false);
		productService.update(product);
		System.out.println("after feature: " + product.isFeatured()+" product id: "+product.getId());
		
		
	}

}
