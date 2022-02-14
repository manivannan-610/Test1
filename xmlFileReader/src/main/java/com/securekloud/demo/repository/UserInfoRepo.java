package com.securekloud.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.securekloud.demo.model.UserInfo;

public interface UserInfoRepo extends JpaRepository<UserInfo, Integer>{
	
}
