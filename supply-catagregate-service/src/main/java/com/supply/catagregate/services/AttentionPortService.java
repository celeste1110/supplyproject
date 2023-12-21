package com.supply.catagregate.services;

import java.util.List;

import com.supply.catagregate.models.entity.AttentionPort;


public interface AttentionPortService {

	
	public List<AttentionPort> findAttentionPortAll();
	
	public AttentionPort findAttentionPortById(Long id);
}
