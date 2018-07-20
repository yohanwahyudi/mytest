package spring.database.jpa.update.main;

import java.io.File;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.update.ApplicationConfig;
import spring.database.jpa.update.Product;
import spring.database.jpa.update.service.ProductService;
import spring.database.jpa.update.service.ProductServiceNative;

public class MainSelect {

	private final static Logger logger = LogManager.getLogger(MainPopulate.class);
	private static AnnotationConfigApplicationContext ctx;

	public static void main(String args[]) throws IllegalArgumentException, IllegalAccessException {

		ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);

		ProductService productService = ctx.getBean("productService", ProductService.class);

		List<Product> productList = productService.findByPrice(new BigDecimal(102));

		for (Product prod : productList) {

			for (Field field : prod.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				Object val = field.get(prod);

				logger.debug(name + " : " + val);
			}
		}
		

		ProductServiceNative productServiceNative = ctx.getBean("productServiceNative", ProductServiceNative.class);
		productList = new ArrayList<Product>();
		productList = productServiceNative.findByPrice(new BigDecimal(102));
		
		logger.debug("\n\n\n");
		logger.debug("native query .............");
		for (Product prod : productList) {

			for (Field field : prod.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				Object val = field.get(prod);

				logger.debug(name + " : " + val);
			}
		}
		
		productList = new ArrayList<>();
		productList = productServiceNative.rightOuterJoinTest();
		
		logger.debug("\n\n\n");
		logger.debug("native query .............");
		for (Product prod : productList) {

			for (Field field : prod.getClass().getDeclaredFields()) {
				field.setAccessible(true);
				String name = field.getName();
				Object val = field.get(prod);

				logger.debug(name + " : " + val);
			}
		}
		
		List<Object[]> descriptionList = new ArrayList<Object[]>();
		descriptionList = productServiceNative.getDescription();
		
		for(Object[] a : descriptionList) {
			String aS = (String) a[0];
			String bS = (String) a[1];
			System.out.println(aS+"-"+bS);
		} 
		
		List<Product> getByDescAndName = productServiceNative.findByDescAndName("x", "product1");
		for(Product p : getByDescAndName) {
			System.out.println(p.getDescription()+" "+p.getName());
		}
		
//		Calendar calFrom = Calendar.getInstance();
//		calFrom.set(2018, 5, 13);
//		calFrom.set(Calendar.HOUR_OF_DAY, 0);
//		calFrom.set(Calendar.MINUTE, 0);
//		calFrom.set(Calendar.SECOND, 0);
//		calFrom.set(Calendar.MILLISECOND, 0);
//		
//		Calendar calTo = Calendar.getInstance();
//		calTo.set(2018, 6, 1);
//		calTo.set(Calendar.HOUR_OF_DAY, 24);
//		calTo.set(Calendar.MINUTE, 59);
//		calTo.set(Calendar.SECOND, 59);
//		calTo.set(Calendar.MILLISECOND, 59);
//		
//		java.util.Date dt = new java.util.Date(2018, 6, 13, 0, 0, 0);
//		java.util.Date dt2 = new java.util.Date(2018, 6, 20, 0, 0, 0);
//		
//		Date dtFrom = new Date(calFrom.getTimeInMillis());
//		Date dtTo = new Date(calTo.getTimeInMillis());
//		
//		List<Product> getByDateRange = productServiceNative.findByDateRange(dtFrom, dtTo);
// 
//		System.out.println(new GregorianCalendar(2018,6,13,0,0,0).getTime());
//		System.out.println(calFrom.getTime());
//		System.out.println(calTo.getTime());
//		
//		System.out.println("bydaterange");
//		for(Product p: getByDateRange) {
//			System.out.println(p.getDescription()+" "+p.getName());
//		}
	}

}
