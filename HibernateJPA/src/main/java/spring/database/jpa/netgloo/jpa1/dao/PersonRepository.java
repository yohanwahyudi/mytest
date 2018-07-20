package spring.database.jpa.netgloo.jpa1.dao;

import javax.transaction.Transactional;

import spring.database.jpa.netgloo.jpa1.models.Person;

@Transactional
public interface PersonRepository extends UserBaseRepository<Person>{

}
