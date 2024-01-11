package com.example.reviseddemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.reviseddemo.entity.UserInfo;
import com.example.reviseddemo.repository.UserInfoRepository;

@Service
public class DemoService {

	@Autowired
	private UserInfoRepository userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	public List<UserInfo> getAllusers(){
		return userRepo.findAll();
	}
	
	public String saveNewUser(UserInfo userInfo) {
		userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));
		userRepo.save(userInfo);
		return "user saved";
	}

	
}
