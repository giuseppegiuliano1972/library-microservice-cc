package com.la.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.la.auth.domain.User;

@Repository
public interface UserDao extends JpaRepository <User,Long>{
	
	public User findByUserid(String userid);

}
