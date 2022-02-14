package com.securekloud.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.securekloud.demo.model.UserInfo;
import com.securekloud.demo.repository.UserInfoRepo;

@Service
public class UserService {

	@Autowired
	private UserInfoRepo userRepo;
	
	public List<UserInfo> getUserList() {
		return userRepo.findAll();
	}
	
	public UserInfo getUser(int id) {
		return userRepo.getById(id);
	}
	
	
}
