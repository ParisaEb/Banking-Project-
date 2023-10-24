package com.example.TheBankingApp.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.TheBankingApp.Dtos.UserDetailDto;
import com.example.TheBankingApp.Model.Account;
import com.example.TheBankingApp.Service.AccountService;


@RestController
@RequestMapping("/accounts")
public class AccountController {

	private final AccountService accountService;
	public AccountController(AccountService accountService) {
		this.accountService=accountService;
	}
	
@PostMapping("/generate")
public ResponseEntity<Account> createAccount() {
    Account newAccount = accountService.createAccount();
    return ResponseEntity.ok(newAccount);
}

@GetMapping("/getAccounts")
public List<Account> getTheAccounts(){
	UserDetailDto userDetailDto =  (UserDetailDto) SecurityContextHolder.getContext().getAuthentication().getDetails();
	  return accountService.getAccountByUserId(userDetailDto.getUserId());  
	
}
    
@GetMapping("/{id}")

	public ResponseEntity<Account> getAccount(@PathVariable Long id) {
	    Account account = accountService.getAccountById(id);
	    if (account != null) {
	        return ResponseEntity.ok(account);
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	    }
	    }
	    





