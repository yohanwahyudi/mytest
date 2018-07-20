package spring.database.jpa.petri.todo.dao;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExtendedRepository<T, ID extends Serializable> extends JpaRepository<T, ID>{
	
	public List<T> findByAttributeContainsText(String attributeName, String text);

}
