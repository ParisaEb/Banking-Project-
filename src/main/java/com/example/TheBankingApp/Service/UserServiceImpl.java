package com.example.TheBankingApp.Service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.TheBankingApp.Dtos.LoginResponseDto;
import com.example.TheBankingApp.Dtos.UserDetailDto;
import com.example.TheBankingApp.Dtos.UserDto;
import com.example.TheBankingApp.Model.User;
import com.example.TheBankingApp.Repository.RoleRepository;
import com.example.TheBankingApp.Repository.UserRepository;
import com.example.TheBankingApp.Security.Role;
import com.example.TheBankingApp.Utils.JWTUtil;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTUtil jwtUtil;


    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;

    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    private Role checkRoleExist() {
        Role role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }

    private String secret = "secret";


    public LoginResponseDto login(UserDto requestDto) {
        
        Authentication authenticationResult = authenticationManager.
                authenticate(new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));
        UserDetailDto user = (UserDetailDto) authenticationResult.getPrincipal();
        String token = jwtUtil.getJWTToken(user);
        LoginResponseDto resultDto = new LoginResponseDto();
        resultDto.setToken(token);
        return resultDto;
    }

    @Override
    public void saveUser(UserDto userDto) {
        // TODO Auto-generated method stub

    }

    @Override
    public List<UserDto> findAllUsers() {
        // TODO Auto-generated method stub
        return null;
    }


}


