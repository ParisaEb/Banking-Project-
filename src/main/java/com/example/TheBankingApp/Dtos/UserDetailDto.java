package com.example.TheBankingApp.Dtos;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.example.TheBankingApp.Model.User;
import com.example.TheBankingApp.Security.Role;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;


public class UserDetailDto implements UserDetails {
    private String username;
    private String password;
    private Long userId;
    private String name;

    private Set<GrantedAuthority> authorities;
    
    public UserDetailDto() {
       
    }

    public UserDetailDto(User appUser) {
        this.userId=appUser.getId();   
        this.password=appUser.getPassword();
        this.name=appUser.getName();
        
        authorities = new HashSet<>();
        for (Role role : appUser.getRoles()) {
            this.authorities.add(new SimpleGrantedAuthority(role.getName())); 
        }
    }

    public void setName(String name) {
      this.name=name;
      
   }
    
   public void setPassword(String password2) {
       this.password=password2;
       
   }

   public void setUserId(Long id) {
      this.userId=id;
      
      
   }
   public void setUsername(String userName) {
	   this.username=userName;
	   
   }
   
   public void setAuthorities(Set<GrantedAuthority> authorities) {
	   this.authorities=authorities;
	   
	   
   }
   

   @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
      // TODO Auto-generated method stub
      return authorities;
      
   }

   @Override
   public String getPassword() {
      // TODO Auto-generated method stub
      return password;
   }

   public String getName() {
      // TODO Auto-generated method stub
      return name;
   }

   
   @Override
   public boolean isEnabled() {
      // TODO Auto-generated method stub
      return true;
   }

   public long getUserId() {
      // TODO Auto-generated method stub
      return userId ;
   }

   @Override
   public String getUsername() {
      // TODO Auto-generated method stub
      return null;
   }
}