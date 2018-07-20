package spring.database.jpa.boraji.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.database.jpa.boraji.dao.PersonDao;
import spring.database.jpa.boraji.entity.Person;
import spring.database.jpa.boraji.service.PersonService;

@Service
public class PersonServiceImpl implements PersonService{

	@Autowired
	private PersonDao userDao;
	
	@Transactional
	@Override
	public void add(Person person) {
		userDao.add(person);
	}

	@Transactional(readOnly=true)
	@Override
	public List<Person> listPersons() {
		return userDao.listPersons();
	}

}
