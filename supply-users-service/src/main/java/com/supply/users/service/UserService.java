package com.supply.users.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.supply.users.models.entity.Role;
import com.supply.users.models.entity.User;

public interface UserService {
	
	public Page<User> findUserByAllPageable(Pageable pageable);
	public List<User> findUserByAll();
	public User findUserById(Long id);
	public List<User> findByUser(String username);
	public List<User> findByRole(String role);
	public User saveUser(User user) ;
	public User updateUser(User user,Long id);
	public void deleteUser(Long id);
	
	public List<Role> findRoleAll();
	public Role findRoleById(Long id);
	
	

}
