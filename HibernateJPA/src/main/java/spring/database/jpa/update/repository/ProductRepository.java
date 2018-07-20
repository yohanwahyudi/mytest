package spring.database.jpa.update.repository;

import java.math.BigDecimal;
import java.util.List;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import spring.database.jpa.update.Product;

public interface ProductRepository extends CrudRepository<Product, Integer>{

	@Query("from Product where price = ?1")
	public List<Product> findByPrice(BigDecimal price);
	
}
