package spring.database.jpa.boraji.service;

import java.util.List;

import spring.database.jpa.boraji.entity.Person;

public interface PersonService {
	void add(Person person);
    List<Person> listPersons();
}
