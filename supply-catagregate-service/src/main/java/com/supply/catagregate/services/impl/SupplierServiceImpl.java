package com.supply.catagregate.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.catagregate.exceptions.ResourceNotFoundException;
import com.supply.catagregate.models.entity.Supplier;
import com.supply.catagregate.repository.SupplierRepository;
import com.supply.catagregate.services.SupplierService;

@Service
public class SupplierServiceImpl implements SupplierService{
	
	@Autowired
	SupplierRepository supplierRepo;

	@Override
	@Transactional(readOnly = true)
	public List<Supplier> findSupplierAll() {
		
		return supplierRepo.findAll()
				.stream().filter(c->c.getActivated().equals(true)).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public Supplier findSupplierPortById(Long id) {
		return supplierRepo.findById(id).
				filter(c->c.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Supplier not found with id: " + id));
	}

}
