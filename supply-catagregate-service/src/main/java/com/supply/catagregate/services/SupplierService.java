package com.supply.catagregate.services;

import java.util.List;

import com.supply.catagregate.models.entity.Supplier;

public interface SupplierService {
	
public List<Supplier> findSupplierAll();
	
	public Supplier findSupplierPortById(Long id);

}
