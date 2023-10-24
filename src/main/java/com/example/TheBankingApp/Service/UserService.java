package com.example.TheBankingApp.Service;

import java.util.List;

import com.example.TheBankingApp.Dtos.LoginResponseDto;
import com.example.TheBankingApp.Dtos.UserDto;
import com.example.TheBankingApp.Model.User;


public interface UserService {
    void saveUser(UserDto userDto);
    User findUserByEmail(String email);
    LoginResponseDto login(UserDto requestDto);
    List<UserDto> findAllUsers();
}