package com.example.reviseddemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.reviseddemo.entity.AuthRequest;
import com.example.reviseddemo.entity.UserInfo;
import com.example.reviseddemo.service.DemoService;
import com.example.reviseddemo.service.JwtService;

@RestController
@RequestMapping("/api")
public class MainController {

	@Autowired
	private DemoService demoService;
	
	@Autowired
    private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/home")
	public String home() {
		return "Welcome Screen";
	}

	@PostMapping("/new-user")
	public String addNewUser(@RequestBody UserInfo userinfo) {
		return demoService.saveNewUser(userinfo);
	}

	@GetMapping("/users")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	@ResponseBody
	public List<UserInfo> getAllUsers() {
		return demoService.getAllusers();
	}

	@GetMapping("/users/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String getAllUsers(@PathVariable int id) {
		return "Showing user " + id;
	}

	@PostMapping("/authenticate")
	public String authenticateAndGenerateToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		}else {
			throw new UsernameNotFoundException("invalid Username");
		}
		
	}
}
