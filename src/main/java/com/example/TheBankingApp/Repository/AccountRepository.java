package com.example.TheBankingApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.TheBankingApp.Model.Account;


public interface AccountRepository extends JpaRepository<Account, Long>{
	
		public List<Account> findByUserId(long id);
		
		 
		 	
	}
	
	

