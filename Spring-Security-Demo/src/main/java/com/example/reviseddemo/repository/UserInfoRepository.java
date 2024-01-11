package com.example.reviseddemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.reviseddemo.entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,	Integer>{

	Optional<UserInfo> findByUsername(String username);

	

}
