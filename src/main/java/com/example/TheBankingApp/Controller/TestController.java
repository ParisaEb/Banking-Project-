package com.example.TheBankingApp.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.TheBankingApp.Dtos.LoginResponseDto;
import com.example.TheBankingApp.Dtos.UserDto;
import com.example.TheBankingApp.Service.UserService;

@RestController
public class TestController {
	
	private UserService userService;
	
	public TestController(UserService userService) {
		this.userService = userService;
	}

@GetMapping("/test")
public String sayHiy() {
	return "Hi";
	
}

@PostMapping(value = "/parisa",
consumes = MediaType.APPLICATION_JSON_VALUE,
produces = MediaType.APPLICATION_JSON_VALUE)
public LoginResponseDto userlogin(@RequestBody UserDto userDto){
	return   userService.login(userDto);
	
}
}
