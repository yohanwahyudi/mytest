package spring.database.jpa.netgloo.jpa2.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.database.jpa.netgloo.jpa2.models.Customer;

@Service("customerService")
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Optional<Customer> findCustomerById(Long id) {
		
		return customerRepository.findById(id);
	}
	
	public void saveCustomer(Customer customer) {
		customerRepository.save(customer);
	}
	
	public void print() {
		System.out.println("haloooooooo....");
	}
	
}
