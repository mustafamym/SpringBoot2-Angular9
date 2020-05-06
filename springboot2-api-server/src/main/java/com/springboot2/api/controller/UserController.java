package com.springboot2.api.controller;

import com.springboot2.api.jwt.JwtProvider;
import com.springboot2.api.user.domain.Role;
import com.springboot2.api.user.domain.RoleName;
import com.springboot2.api.user.domain.User;
import com.springboot2.api.user.model.LoginRequest;
import com.springboot2.api.user.model.LoginResponse;
import com.springboot2.api.user.model.UserDto;
import com.springboot2.api.user.model.WebApiGenericResponse;
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
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/users")
public class UserController {

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


	@PostMapping("/register")
	public ResponseEntity<?> registerUser( @RequestBody UserDto signUpRequest) {
		modelMapper=new ModelMapper();
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			
			return new ResponseEntity<>(new WebApiGenericResponse(Boolean.FALSE,"Fail -> Username is already taken!",null),
					HttpStatus.BAD_REQUEST);
		}
		Boolean exists=userRepository.existsByUsername(signUpRequest.getUsername());
		if (exists) {
			return new ResponseEntity<>(new WebApiGenericResponse(Boolean.FALSE,"Fail -> Email is already in use!",null),
					HttpStatus.BAD_REQUEST);
		}

		// Creating user's account
//		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
//				encoder.encode(signUpRequest.getPassword()));

		Set<Role> roles = new HashSet<>();
		Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
				.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
		roles.add(adminRole);
//		strRoles.forEach(role -> {
//			switch (role) {
//			case "admin":
//				Role adminRole = roleRepository.findByName(RoleName.ROLE_ADMIN)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(adminRole);
//
//				break;
//			case "pm":
//				Role pmRole = roleRepository.findByName(RoleName.ROLE_PM)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(pmRole);
//
//				break;
//			default:
//				Role userRole = roleRepository.findByName(RoleName.ROLE_USER)
//						.orElseThrow(() -> new RuntimeException("Fail! -> Cause: User Role not find."));
//				roles.add(userRole);
//			}
//		});
		User user=modelMapper.map(signUpRequest,User.class);
		user.setPassword(encoder.encode(signUpRequest.getPassword()));
		user.setRoles(roles);
		userRepository.save(user);
		return new ResponseEntity<>(new WebApiGenericResponse(Boolean.TRUE,"User registered successfully!",null), HttpStatus.OK);
	}
}
