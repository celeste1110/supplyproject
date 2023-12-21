package com.supply.catagregate.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supply.catagregate.models.entity.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long>{

}
