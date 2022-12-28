package com.quanly.thuvien.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import com.quanly.thuvien.model.AccountModel;
import com.quanly.thuvien.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quanly.thuvien.reponse.LoginResponse;
import com.quanly.thuvien.request.LoginRequest;
import com.quanly.thuvien.security.CustomUserDetails;
import com.quanly.thuvien.security.JwtTokenProvider;

@RestController
@RequestMapping("/auth")
@Controller
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

	@Autowired
	private AccountRepository accountRepository;
	
	@PostMapping("/login")
	@CrossOrigin(origins = "*", maxAge = 3600)
	public ResponseEntity<?> authenticateUser(@RequestBody @Valid LoginRequest loginRequest) {
		Map<String, Object> response = new HashMap<>();
		try {
			Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					loginRequest.getUsername().toLowerCase(), loginRequest.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = tokenProvider.generateToken(authentication);
			Optional<AccountModel> account = accountRepository.findByUsername(loginRequest.getUsername());
			response.put("success", true);
			response.put("message", "Login successfuly !");
			response.put("data", new LoginResponse(jwt));
			response.put("accountId", account.get().getId());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getMessage());
			response.put("message", "Username or password not found!");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}
	};
}
