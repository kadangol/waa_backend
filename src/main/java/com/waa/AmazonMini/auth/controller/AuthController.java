package com.waa.AmazonMini.auth.controller;

import com.waa.AmazonMini.annotation.ApiPageable;
import com.waa.AmazonMini.auth.model.*;
import com.waa.AmazonMini.auth.payload.request.*;
import com.waa.AmazonMini.auth.payload.response.*;
import com.waa.AmazonMini.auth.repository.RoleRepository;
import com.waa.AmazonMini.auth.repository.UserRepository;
import com.waa.AmazonMini.auth.security.jwt.JwtUtils;
import com.waa.AmazonMini.auth.security.services.UserDetailsImpl;
import com.waa.AmazonMini.domain.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController

@ApiPageable
@Api(description = "REST API for Authentification", tags = { "Auth" })

@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Username is already taken!"));
//		}
//
//		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Email is already in use!"));
//		}
//
//		// Create new user's account
//		User user = new User(signUpRequest.getUsername(),
//							encoder.encode(signUpRequest.getPassword()),
//								signUpRequest.getFullName(),
//							 signUpRequest.getEmail(),
//							 signUpRequest.getPhone(),
//							 signUpRequest.getSeller(),
//							 signUpRequest.getBuyer(),
//							 signUpRequest.getRoles());
//
//		Set<Role> strRoles = signUpRequest.getRoles();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepository.findByName(ERole.ROLE_BUYER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role.getName()) {
//					case ROLE_ADMIN:
//					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//					case ROLE_SELLER:
//					Role sellerRole = roleRepository.findByName(ERole.ROLE_SELLER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(sellerRole);
//
//					break;
//				default:
//					Role buyerRole = roleRepository.findByName(ERole.ROLE_BUYER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(buyerRole);
//				}
//			});
//		}
//
//		user.setRoles(roles);
//		userRepository.save(user);
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}
}
