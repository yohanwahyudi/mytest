package spring.database.jpa.orm2.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import spring.database.jpa.orm2.model.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	public List<Product> findByNameContainingIgnoreCase(String searchString);

	@Query("select p from Product p where p.name = :name")
	public List<Product> findByNameIs(@Param("name") String name);

}
