package com.supply.catagregate.repository;


import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.supply.catagregate.models.documents.Supplier;

@Repository
public interface SupplierRepository extends ReactiveMongoRepository<Supplier, String>{

}
