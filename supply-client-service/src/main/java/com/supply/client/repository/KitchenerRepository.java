package com.supply.client.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.supply.client.models.entity.Kitchener;

public interface KitchenerRepository extends JpaRepository<Kitchener, Long>{
	
	@Query("select k from Kitchener k where k.dni=?1 and k.activated=true")
	public List<Kitchener> findByDni(String dni);
	
	@Query("select p from Kitchener p join fetch p.embarkation e where ( p.name like %?1% or p.lastname like %?1% )  and ( p.dni=?2 or ?2='0' ) and (e.id=?3 or ?3=0) and p.activated =true and e.activated =true ")
	public List<Kitchener> findKitchenerByNameAndDniAndEmbarcacion(String name,String dni,Long cod_embarcacion);

}
