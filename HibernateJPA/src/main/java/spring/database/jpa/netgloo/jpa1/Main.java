package spring.database.jpa.netgloo.jpa1;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import spring.database.jpa.netgloo.jpa1.dao.CompanyRepository;
import spring.database.jpa.netgloo.jpa1.dao.PersonRepository;
import spring.database.jpa.netgloo.jpa1.dao.UserRepository;
import spring.database.jpa.netgloo.jpa1.models.Company;
import spring.database.jpa.netgloo.jpa1.models.Person;
import spring.database.jpa.netgloo.jpa1.config.PersistenceContext;

public class Main {

	private final static Logger logger = LogManager.getLogger(Main.class);
	
	@Autowired
	private static CompanyRepository companyRepository;

	@Autowired
	private static PersonRepository personRepository;

	@Autowired
	private static UserRepository userRepository;

	public static void main(String args[]) {		
		
//		DOMConfigurator.configure(System.getProperty("user.dir")+File.separator+"log4j.xml");
		
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(PersistenceContext.class);

		try {
			Person person = new Person();
			person.setEmail("yohan1@yohan.com");
			person.setFirstName("yohan1");
			personRepository.save(person);
			
			person = new Person();
			person.setEmail("yohan2@yohan.com");
			person.setFirstName("yohan2");
			personRepository.save(person);
			
			
			Company company = new Company();
			company.setEmail("company1@yohan.com");
			company.setName("company1");
			companyRepository.save(company);
			
			company = new Company();
			company.setEmail("company2@yohan.com");
			company.setName("company2");
			companyRepository.save(company);
			
		} catch (Exception ex) {
			logger.debug("Error creating the person: " + ex.toString());
		}
		
		
	}
}
