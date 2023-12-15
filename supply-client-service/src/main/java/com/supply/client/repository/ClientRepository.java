package com.supply.client.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supply.client.models.entity.Client;


public interface ClientRepository extends JpaRepository<Client, String>{
	
	@Query("select c from Client c where c.ruc=?1 and c.activated=true")
	List<Client> findByRuc(String ruc);
	
	@Query("select c from Client c  where ( ?1=1 and (c.ruc=?2 or ?2='' ))  or ( ?1=2 and (c.socialReason like %?2% or ?2='' )) and c.activated =true ")
	public List<Client> findClientByRucAndSocialReason(Integer type,String data );
	
	

}
