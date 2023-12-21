package com.supply.users.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.supply.users.models.dto.FilterUserDto;
import com.supply.users.models.entity.User;
import com.supply.users.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired	
	private UserService userService;
	
	@GetMapping
	public ResponseEntity<Page<User>> listAll(Pageable pageable) {
		
		Page<User> listUser=userService.findUserByAllPageable(pageable);
		return new ResponseEntity<>(listUser,HttpStatus.OK);
	}
	@GetMapping("/list")
	public ResponseEntity<List<User>> listAll(){
		List<User> listUsers=userService.findUserByAll();
		
		return new ResponseEntity<>(listUsers,HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		User user = userService.findUserById(id);
		
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@PostMapping("/filterUsername")
	public ResponseEntity<List<User>> filterUsername(@Valid @RequestBody FilterUserDto request) {
		
		List<User> listUsers=userService.findByUser(request.getData());
		return new ResponseEntity<>(listUsers,HttpStatus.OK);
			
	}
	@PostMapping("/filterRole")
	public ResponseEntity<List<User>> filterRole(@Valid @RequestBody FilterUserDto request) {
		
		List<User> listUsers=userService.findByRole(request.getData());
		return new ResponseEntity<>(listUsers,HttpStatus.OK);
			
	}
	
	@PostMapping
	public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
		
		User saveUser=userService.saveUser(user);
		return new ResponseEntity<>(saveUser,HttpStatus.CREATED);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> editUser(@Valid @RequestBody User user,@PathVariable Long id) {
		
		User editUser=userService.updateUser(user,id);
		return new ResponseEntity<>(editUser,HttpStatus.OK);

	}

	@PutMapping("/{id}/disabled")
	public ResponseEntity<String> enabledUser(@PathVariable Long id) {
		
		userService.deleteUser(id);
		return new ResponseEntity<>("User succesfully deleted",HttpStatus.OK);
		
	}
	//ROLES
	
		@GetMapping("/roles")
		public ResponseEntity<?> listarRoles(){
			return ResponseEntity.ok(userService.findRoleAll());
		}


}
