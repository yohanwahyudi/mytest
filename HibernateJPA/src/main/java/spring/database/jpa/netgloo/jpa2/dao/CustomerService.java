package spring.database.jpa.netgloo.jpa2.dao;

import java.util.Optional;

import spring.database.jpa.netgloo.jpa2.models.Customer;

public interface CustomerService {
	
	Optional<Customer> findCustomerById(Long id);
	void saveCustomer(Customer customer);
	void print();
}
