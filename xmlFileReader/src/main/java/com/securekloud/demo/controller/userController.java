package com.securekloud.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.securekloud.demo.model.UserInfo;
import com.securekloud.demo.service.UserService;

@Controller
public class userController {

	@Autowired
	private UserService userService; 
	
	@GetMapping("/userList")
	@ResponseBody
	public List<UserInfo> getUserList() {
		System.out.println("entered into userlist");
		List<UserInfo> ulist = userService.getUserList();
		System.out.println(ulist.get(1).getAddress());
		 return ulist;
	}
	
	
	
	
	@GetMapping("/main")
	public String getUserPage() {
		System.out.println("entered into main page controler");
		return "userInfo";
	}
	
	
	@GetMapping("fetchUser/{id}")
	@ResponseBody
	public UserInfo getUser(@PathVariable("id") int id) throws JsonProcessingException {
		System.out.println("idd="+id);
		System.out.println("obj="+userService.getUser(id).getAddress());
		UserInfo userInfo = userService.getUser(id);
		 return userInfo;
	}
	
	@ExceptionHandler(value=Exception.class)
	public String exceptionHandle(Exception e) {
		System.out.println("error entered");
		e.printStackTrace();
		return "unexpectedException.jsp";
	}
}
