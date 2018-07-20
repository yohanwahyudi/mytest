package spring.database.jpa.boraji.dao;

import java.util.List;

import spring.database.jpa.boraji.entity.Person;

public interface PersonDao {
	void add(Person person);
	List<Person> listPersons();
	
}
