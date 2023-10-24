package com.example.TheBankingApp.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.el.stream.Optional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.TheBankingApp.Dtos.UserDetailDto;
import com.example.TheBankingApp.Model.Account;
import com.example.TheBankingApp.Repository.AccountRepository;
import com.example.TheBankingApp.Repository.UserRepository;


@Service
public class AccountService {
	 private final AccountRepository accountRepository ;
	 private final UserRepository    userRepository;
	 
	 
	 public AccountService(AccountRepository accountRepository, UserRepository userRepository) {
		    this.accountRepository = accountRepository;
		     this.userRepository =  userRepository;
		  }

	 public Account getAccountById(Long id) {
		    return accountRepository.findById(id).orElse(null);
		}
	 
	 public List<Account> getAccountByUserId(long id) {
		     
		 return  accountRepository.findByUserId(id);
		 
	 }
		  
	 public Account createAccount() { 
		 Random random = new Random(); 
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 UserDetailDto userDetailDto =  (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
	     Account account = new Account(random.nextInt(2000000087),userRepository.findById(userDetailDto.getUserId()).get());  	       
		  return accountRepository.save(account);
		
	}
	 
	 
	
	public void deleteAccount(Account account) {
		accountRepository.delete(account);
	}
	
}
