package spring.database.jpa.update.main;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.update.ApplicationConfig;
import spring.database.jpa.update.Product;
import spring.database.jpa.update.service.ProductService;

public class MainPopulate {
	
	private final static Logger logger = LogManager.getLogger(MainPopulate.class);
	private static AnnotationConfigApplicationContext ctx;
	
	public static void main(String args[]) {
		
		ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		
		ProductService productService = ctx.getBean("productService", ProductService.class);
		
		List<Product> list = new ArrayList<Product>();		
		
		Product product = new Product();
		product.setName("product1");
		product.setDescription("description1");
		product.setFeatured(true);
		product.setPhoto("photo1");
		product.setPrice(new BigDecimal(102));
		product.setQuantity(1);
		list.add(product);
		
		product = new Product();
		product.setName("product2");
		product.setDescription("description2");
		product.setFeatured(true);
		product.setPhoto("photo2");
		product.setPrice(new BigDecimal(102));
		product.setQuantity(2);
		list.add(product);
		
		product = new Product();
		product.setName("product3");
		product.setDescription("description3");
		product.setFeatured(true);
		product.setPhoto("photo3");
		product.setPrice(new BigDecimal(103));
		product.setQuantity(3);
		list.add(product);
		
		product = new Product();
		product.setName("product4");
		product.setDescription("description4");
		product.setFeatured(true);
		product.setPhoto("photo4");
		product.setPrice(new BigDecimal(104));
		product.setQuantity(4);
		list.add(product);
		
		product = new Product();
		product.setName("product5");
		product.setDescription("description5");
		product.setFeatured(true);
		product.setPhoto("photo5");
		product.setPrice(new BigDecimal(105));
		product.setQuantity(5);
		list.add(product);
		
		productService.addAll(list);
		
		
		
	}

}
