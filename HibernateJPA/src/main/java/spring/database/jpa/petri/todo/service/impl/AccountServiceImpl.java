package spring.database.jpa.petri.todo.service.impl;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import spring.database.jpa.petri.todo.dao.impl.AccountRepository;
import spring.database.jpa.petri.todo.model.Account;

@Service
public class AccountServiceImpl implements AccountService{
	
	@Autowired
	private AccountRepository accountRepository;
	
	@PostConstruct
	@Transactional
	public void populate() {
		Account account = new Account();
		account.setName("name1");
		accountRepository.saveAndFlush(account);
		
		account = new Account();
		account.setName("name2");
		accountRepository.saveAndFlush(account);
		
		account = new Account();
		account.setName("name3");		
		accountRepository.saveAndFlush(account);
	}
	
	@Transactional//(readOnly=true)
	public List<Account> getAll() {
		return accountRepository.findAll();
	}
	
	@SuppressWarnings("AssignmentToMethodParameter")
	@Transactional
	public Account saveAndFlush(Account acc) {
		if(acc!=null) {
			acc = accountRepository.saveAndFlush(acc);
		}
		
		return acc;
	}
	
	@Transactional//(readOnly=true)
	public void delete(long id) {
		accountRepository.deleteById(id);
	}

}
