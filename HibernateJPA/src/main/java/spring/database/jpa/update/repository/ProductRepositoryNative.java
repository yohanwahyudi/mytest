package spring.database.jpa.update.repository;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import spring.database.jpa.update.Product;

public interface ProductRepositoryNative extends JpaRepository<Product, Integer>{

	@Query(value="SELECT * FROM PRODUCTS WHERE PRICE=?1", nativeQuery=true)
	public List<Product> findByPrice(BigDecimal price);
	
	@Query(value="SELECT * FROM PRODUCTS WHERE DESCRIPTION = :description AND NAME = :name", nativeQuery=true)
	public List<Product> findByDescAndName(@Param("description") String description, @Param("name") String name);
	
	@Query(value="SELECT * FROM PRODUCTS WHERE CREATED_DT >= :dtFrom AND CREATED_DT <= :dtTo", nativeQuery=true)
	public List<Product> findByDateRange(@Param("dtFrom") Date dtFrom, @Param("dtTo") Date dtTo);
	
	@Query(value="select prod.* from products as prod left outer join product2 as prod2 on prod.id=prod2.id where prod2.id is null", nativeQuery=true)
	public List<Product> rightOuterJoinTest();
	
	@Query(value="select description,name from test.products", nativeQuery=true)
	public List<Object[]> getDescription();
}
