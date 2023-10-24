package com.example.TheBankingApp.Controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.TheBankingApp.Dtos.UserDto;
import com.example.TheBankingApp.Service.UserService;

import java.util.List;

@Controller
public class AuthController {

    private UserService userService;
    
    public AuthController(UserService userService) {
        this.userService = userService;
    }

  

}

