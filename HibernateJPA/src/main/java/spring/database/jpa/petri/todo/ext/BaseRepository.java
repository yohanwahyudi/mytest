package spring.database.jpa.petri.todo.ext;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface BaseRepository<T, ID extends Serializable> /*extends Repository<T, ID>*/{

	void delete(T deleted);	 
    List<T> findAll();     
    Optional<T> findOne(ID id); 
    T save(T persisted);
	
}
