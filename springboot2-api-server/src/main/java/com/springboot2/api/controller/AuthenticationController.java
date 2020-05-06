package com.springboot2.api.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.Valid;

import com.springboot2.api.jwt.JwtProvider;
import com.springboot2.api.jwt.UserPrinciple;
import com.springboot2.api.user.domain.Role;
import com.springboot2.api.user.domain.RoleName;
import com.springboot2.api.user.domain.User;
import com.springboot2.api.user.model.*;
import com.springboot2.api.user.repository.RoleRepository;
import com.springboot2.api.user.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtProvider jwtProvider;

    ModelMapper  modelMapper;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		String jwt = jwtProvider.generateJwtToken(authentication);

		UserPrinciple userPrinciple=(UserPrinciple)authentication.getPrincipal();

		return ResponseEntity.ok(new LoginResponse(userPrinciple.getId(),jwt, userPrinciple.getUsername(), userPrinciple.getAuthorities()));
	}
}
