package spring.database.jpa.orm2.service;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import spring.database.jpa.orm2.model.Product;
import spring.database.jpa.orm2.repository.ProductRepository;

@Component
public class ProductService {
	
	@Autowired
	private ProductRepository productRepository;

	@Transactional
	public void add(Product product) {
		productRepository.save(product);
	}
	
	@Transactional(readOnly=true)
	public List<Product> findAll() {
		return (List<Product>) productRepository.findAll();
	}

	@Transactional
	public void addAll(Collection<Product> products) {
		for (Product product : products) {
			productRepository.save(product);
		}
	}

	@Transactional(readOnly=true)
	public List<Product> findByNameIs(String name) {
		return productRepository.findByNameIs(name);
	}
	
	@Transactional(readOnly=true)
	public List<Product> findByNameContainingIgnoreCase(String searchString) {
		return productRepository.findByNameContainingIgnoreCase(searchString);
	}
	
}
