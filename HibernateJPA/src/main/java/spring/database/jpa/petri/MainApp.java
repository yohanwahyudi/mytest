package spring.database.jpa.petri;

//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.petri.config.PersistenceContext;

public class MainApp {
	
	private final static Logger logger = Logger.getLogger(MainApp.class);
	
	public static void main(String args[]) {
		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j.xml");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceContext.class);
//		context.getBean(AccountService.class);
		
		
		context.close();
		
	}

}
