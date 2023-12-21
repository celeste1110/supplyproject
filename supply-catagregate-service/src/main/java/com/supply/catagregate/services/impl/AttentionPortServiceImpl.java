package com.supply.catagregate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.catagregate.exceptions.ResourceNotFoundException;
import com.supply.catagregate.models.entity.AttentionPort;
import com.supply.catagregate.repository.AttentionPortRepository;
import com.supply.catagregate.services.AttentionPortService;

@Service
public class AttentionPortServiceImpl implements AttentionPortService{
	
	@Autowired
	AttentionPortRepository arepository;

	@Override
	@Transactional(readOnly = true)
	public List<AttentionPort> findAttentionPortAll() {
		
		return arepository.findAll()
				.stream().filter(c->c.getActivated().equals(true)).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public AttentionPort findAttentionPortById(Long id) {
		
		return arepository.findById(id).
				filter(c->c.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Attention Port not found with id: " + id));
	}

}
