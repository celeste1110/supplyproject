package com.supply.catagregate.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

import com.supply.catagregate.models.documents.AttentionPort;

public interface AttentionPortRepository extends ReactiveMongoRepository<AttentionPort, String>{

}
