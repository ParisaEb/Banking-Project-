package com.example.TheBankingApp.Dtos;

import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public class TokenDto {
    private String username;
    private Long userId;
    private Set<GrantedAuthority> authorities;
    private String userFullName;
    
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Set<GrantedAuthority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(Set<GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	public String getUserFullName() {
		return userFullName;
	}
	public void setUserFullName(String userFullName) {
		this.userFullName = userFullName;
	}
    
    
}