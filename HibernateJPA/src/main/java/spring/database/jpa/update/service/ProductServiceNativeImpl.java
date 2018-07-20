package spring.database.jpa.update.service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.database.jpa.update.Product;
import spring.database.jpa.update.repository.ProductRepositoryNative;

@Transactional
@Service("productServiceNative")
public class ProductServiceNativeImpl implements ProductServiceNative{

	@Autowired
	private ProductRepositoryNative repo;
	
	@Override
	public List<Product> findByPrice(BigDecimal price) {
		return repo.findByPrice(price);
	}

	@Override
	public List<Product> rightOuterJoinTest() {
		return repo.rightOuterJoinTest();
	}

	@Override
	public List<Object[]> getDescription() {
		return repo.getDescription();
	}

	@Override
	public List<Product> findByDescAndName(String description, String name) {
		return repo.findByDescAndName(description, name);
	}

	@Override
	public List<Product> findByDateRange(Date dtFrom, Date dtTo) {
		return repo.findByDateRange(dtFrom, dtTo);
	}

}
