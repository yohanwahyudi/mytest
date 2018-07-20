package spring.database.jpa.netgloo.jpa2.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

import spring.database.jpa.netgloo.jpa2.models.Customer;

@Transactional
public interface CustomerRepository extends CrudRepository<Customer,Long>{
	
//	List<Customer> findByLastName(String lastName);

}
