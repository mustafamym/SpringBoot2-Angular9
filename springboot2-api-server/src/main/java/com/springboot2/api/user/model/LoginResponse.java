package com.springboot2.api.user.model;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;

public class LoginResponse {
	private Long id;
	private String accessToken;
	private String type = "Bearer";
	private String username;
	private Collection<? extends GrantedAuthority> authorities;

	public LoginResponse(Long id,String accessToken, String username, Collection<? extends GrantedAuthority> authorities) {
		this.id=id;
		this.accessToken = accessToken;
		this.username = username;
		this.authorities = authorities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
}
