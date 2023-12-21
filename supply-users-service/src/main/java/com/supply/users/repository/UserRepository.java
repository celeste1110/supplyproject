package com.supply.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supply.users.models.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	List<User> findByUsername(String username);
	
	

}
