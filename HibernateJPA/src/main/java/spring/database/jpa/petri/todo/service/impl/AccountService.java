package spring.database.jpa.petri.todo.service.impl;

import java.util.List;

import spring.database.jpa.petri.todo.model.Account;

public interface AccountService {

	public List<Account> getAll();
	public Account saveAndFlush(Account acc);
	public void delete(long id);
	
}
