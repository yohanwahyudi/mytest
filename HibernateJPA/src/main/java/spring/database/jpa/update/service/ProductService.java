package spring.database.jpa.update.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import spring.database.jpa.update.Product;

public interface ProductService {
	
	public Product save(Product product);
	public Product find (int id);
	public Product update (Product product);
	public Iterable<Product> update(Iterable<Product> iterable);
	public void addAll(Collection<Product> product);
	
	public List<Product> findByPrice(BigDecimal price);
	
}
