package com.supply.client.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supply.client.models.entity.Embarkation;

public interface EmbarkationRepository extends JpaRepository<Embarkation,Long>{

	@Query("select e from Embarkation e join fetch e.client c where c.id=?1 and e.name like %?2% and e.activated=true")
	public List<Embarkation> findEmbarkationByNameAndClientId(String id, String name);
	
	@Query("select e from Embarkation e where e.name=?1 and e.activated=true")
	public List<Embarkation> findByName(String name);
}
