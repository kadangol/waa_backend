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
import com.waa.AmazonMini.service.BuyerService;
import com.waa.AmazonMini.service.SellerService;
import com.waa.AmazonMini.utils.dto.ResponseMessage;
import com.waa.AmazonMini.utils.enums.Status;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import lombok.var;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
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
@Api(description = "REST API for Authentification", tags = {"Auth"})

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

    @Autowired
    private SellerService sellerService;

    @Autowired
    BuyerService buyerService;

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


        long sellerId = 0;
        long buyerId = 0;
        if (userDetails.getAuthorities().iterator().next().toString().equals(ERole.ROLE_SELLER.toString())) {
            var seller = sellerService.getSellerByUserId(userDetails.getId());
            sellerId = seller.getId();
            if (seller.getApprovalStatus() == Status.NOTAPPROVEDYET) {
                return ResponseEntity.ok(new ResponseMessage(Status.NOTAPPROVEDYET.toString(), HttpStatus.OK));
            }
        }

        if (userDetails.getAuthorities().iterator().next().toString().equals(ERole.ROLE_BUYER.toString())) {
            var buyer = buyerService.getBuyerByUserId(userDetails.getId());
            buyerId = buyer.getId();
        }

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles, buyerId, sellerId));
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
