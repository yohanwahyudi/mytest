package spring.database.jpa.boraji;

import java.io.File;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.boraji.config.AppConfig;
import spring.database.jpa.boraji.entity.Person;
import spring.database.jpa.boraji.service.PersonService;

public class MainApp {

	private final static Logger logger = LogManager.getLogger(MainApp.class);
	
	public static void main(String args[]) {
//		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j.xml");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

		PersonService personService = context.getBean(PersonService.class);

		// Add Persons
		personService.add(new Person("Sunil", "Bora", "suni.bora@example.com"));
		personService.add(new Person("David", "Miller", "david.miller@example.com"));
		personService.add(new Person("Sameer", "Singh", "sameer.singh@example.com"));
		personService.add(new Person("Paul", "Smith", "paul.smith@example.com"));

		// Get Persons
		List<Person> persons = personService.listPersons();
		for (Person person : persons) {
			System.out.println("Id = " + person.getId());
			System.out.println("First Name = " + person.getFirstName());
			System.out.println("Last Name = " + person.getLastName());
			System.out.println("Email = " + person.getEmail());
			System.out.println();
		}

		context.close();

	}

}
