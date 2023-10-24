package com.example.TheBankingApp.Service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.TheBankingApp.Dtos.UserDetailDto;
import com.example.TheBankingApp.Model.User;
import com.example.TheBankingApp.Repository.UserRepository;
import com.example.TheBankingApp.Security.Role;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetailDto loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if (user != null) {
            UserDetailDto userDetailDto = new UserDetailDto();
            userDetailDto.setUserId(user.getId());
            userDetailDto.setUsername(user.getEmail());
            userDetailDto.setName(user.getName());
            userDetailDto.setPassword(user.getPassword());
            userDetailDto.setAuthorities(mapRolesToAuthorities(user.getRoles()));
            return userDetailDto;
        } else {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
    }

    private Set<GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        Set<GrantedAuthority> mapRoles = roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toSet());
        return mapRoles;
    }
}