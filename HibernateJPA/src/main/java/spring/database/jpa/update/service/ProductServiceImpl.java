package spring.database.jpa.update.service;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.database.jpa.update.Product;
import spring.database.jpa.update.repository.ProductRepository;

@Transactional
@Service("productService")
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository repo;
	
	@Override
	public Product save(Product product) {
		return repo.save(product);
	}
	
	@Override
	public Product find(int id) {
		
		return repo.findById(id).get();		
		
	}

	@Override
	public Product update(Product product) {
		return repo.save(product);
	}

	@Override
	public Iterable<Product> update(Iterable<Product> iterable) {
		
		return repo.saveAll(iterable);
		
	}

	@Override
	public void addAll(Collection<Product> product){
		for(Product prod : product) {
			repo.save(prod);
		}
	}

	@Override
	public List<Product> findByPrice(BigDecimal price) {
		return repo.findByPrice(price);
	}
	
}
