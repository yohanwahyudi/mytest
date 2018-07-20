package spring.database.jpa.petri.todo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.Repository;

interface TodoRepository /*extends Repository<Todo, Long> */{
	
	void delete(Todo deleted);	 
    List<Todo> findAll();    
    Optional<Todo> findOne(Long id);
    Todo save(Todo persisted);
    
}
