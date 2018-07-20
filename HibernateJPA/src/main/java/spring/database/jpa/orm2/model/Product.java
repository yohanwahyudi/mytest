package spring.database.jpa.orm2.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Product {
	
    @Id
	public Integer id;
    public String name;
	
	public Product() {
		//Default constructor needed for JPA.
	}
	public Product(Integer id, String name) {
		this.id = id;
		this.name = name;
	}
		
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + "]";
	}
	
}