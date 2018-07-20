package spring.database.jpa.update.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import spring.database.jpa.update.Product;

public interface ProductServiceNative {
	
	public List<Product> findByPrice(BigDecimal price); 
	public List<Product> rightOuterJoinTest();
	public List<Object[]> getDescription();
	public List<Product> findByDescAndName(String description, String name);
	public List<Product> findByDateRange(Date dtFrom, Date dtTo);
}
