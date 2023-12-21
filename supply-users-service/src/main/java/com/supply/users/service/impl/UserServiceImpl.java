package com.supply.users.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.users.clients.ClientClients;
import com.supply.users.exceptions.BadRequestException;
import com.supply.users.exceptions.ResourceNotFoundException;
import com.supply.users.models.Client;
import com.supply.users.models.entity.Role;
import com.supply.users.models.entity.User;
import com.supply.users.repository.RoleRepository;
import com.supply.users.repository.UserRepository;
import com.supply.users.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private ClientClients clientClients;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Page<User> findUserByAllPageable(Pageable pageable) {

		List<User> list = userRepository.findAll(pageable).filter(p -> p.getActivated().equals(true)).map(e -> {
			Client client = clientClients.getClient(e.getCodClient());
			e.setClient(client);
			return e;
		}).toList();

		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Page not found " + pageable);
		}
		return new PageImpl<>(list, pageable, list.size());
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findUserByAll() {

		List<User> listaUsers = userRepository.findAll();

		return listaUsers.stream().filter(p -> p.getActivated().equals(true)).map(e -> {
			Client client = clientClients.getClient(e.getCodClient());
			e.setClient(client);
			return e;
		}).toList();
		
		
	}

	@Override
	@Transactional(readOnly = true)
	public User findUserById(Long id) {

		User user = userRepository.findById(id).filter(p -> p.getActivated().equals(true))
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));

		Client client = clientClients.getClient(user.getCodClient());
		user.setClient(client);

		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByUser(String username) {

		List<User> listUsers = userRepository.findByUsername(username);

		return listUsers.stream().filter(e -> e.getActivated().equals(true))
				.map(e -> {
					Client client = clientClients.getClient(e.getCodClient());
					e.setClient(client);
			
			return e;
		}).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public List<User> findByRole(String role) {
		
		List<User> listAll=this.findUserByAll();
		

		return listAll.stream().filter(e->role.equalsIgnoreCase(e.getRole().getName()))
				.toList();
	}

	@Override
	@Transactional
	public User saveUser(User user) {

		Optional<User> userResponse=userRepository.findByUsername(user.getUsername().toUpperCase())
				.stream()
				.filter(e -> e.getActivated().equals(true))
				.findFirst();
		
		if(userResponse.isPresent()) {
			throw new BadRequestException("Username already exists for User");
		}
		

		Client client = clientClients.getClient(user.getCodClient());
		user.setClient(client);
		
		 String paswordBc=passwordEncoder.encode(user.getPassword());
	     user.setPassword(paswordBc);
			
		return userRepository.save(user);
	}

	@Override
	public User updateUser(User user, Long id) {

		User userBd=this.findUserById(id);
		
		Optional<User> userResponse=userRepository.findByUsername(user.getUsername())
				.stream()
				.filter(e -> e.getActivated().equals(true) && e.getId() !=id)
				.findFirst();
		
		if(userResponse.isPresent()) {
			throw new BadRequestException("Username already exists for User");
		}
		
		userBd.setRole(user.getRole());
		userBd.setModifiedBy("SHP-WEB");
		userBd.setModificationDate(LocalDate.now());
		
		return userRepository.save(userBd);
	}

	@Override
	public void deleteUser(Long id) {
		User userBd=this.findUserById(id);
		userBd.setActivated(false);
		userBd.setModifiedBy("SHP-WEB");
		userBd.setModificationDate(LocalDate.now());
		
		userRepository.save(userBd);
		

	}

	@Override
	@Transactional(readOnly = true)
	public List<Role> findRoleAll() {
		List<Role> roles = roleRepository.findAll();

		return roles.stream().filter(r -> r.getActivated().equals(true)).toList();

	}

	@Override
	@Transactional(readOnly = true)
	public Role findRoleById(Long id) {

		return roleRepository.findById(id).filter(p -> p.getActivated().equals(true))
				.orElseThrow(() -> new ResourceNotFoundException("Role not found with id: " + id));
	}

}
