package com.supply.client.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.client.exceptions.BadRequestException;
import com.supply.client.exceptions.ResourceNotFoundException;
import com.supply.client.models.dto.KitchenerRequestDto;
import com.supply.client.models.dto.KitchenerResponseDto;
import com.supply.client.models.dto.filter.FilterKitchenerDto;
import com.supply.client.models.dto.mapper.KitchenerMapper;
import com.supply.client.models.entity.Kitchener;
import com.supply.client.repository.KitchenerRepository;
import com.supply.client.services.EmbarkationService;
import com.supply.client.services.KitchenerService;

@Service
public class KitchenerServiceImpl implements KitchenerService{
	
	@Autowired
	KitchenerRepository kitchenerRepository;
	
	@Autowired
	EmbarkationService embarkationService;

	@Override
	@Transactional(readOnly = true)
	public Page<KitchenerResponseDto> findKitchenerPageableByAll(Pageable pageable) {
		
		List<KitchenerResponseDto> list= kitchenerRepository.findAll( pageable)
				.filter(p->p.getActivated().equals(true))
				.map(KitchenerMapper::mapToKitchenerResponsenDto).toList();
		
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Page not found " + pageable );
		}
		return new PageImpl<>(list,pageable,list.size());
		
	}

	@Override
	@Transactional(readOnly = true)
	public KitchenerResponseDto findKitchenerById(Long id) {
		
		Kitchener okitchener=this.findKitchenerByIdGeneral(id);
		
		
		return KitchenerMapper.mapToKitchenerResponsenDto(okitchener);
	}

	@Override
	@Transactional(readOnly = true)
	public List<KitchenerResponseDto> findKitchenerByNameAndDniAndEmbarcation(FilterKitchenerDto request) {
		
		List<Kitchener> kitcheners=kitchenerRepository.findKitchenerByNameAndDniAndEmbarcacion(request.getName(), request.getDni(),request.getCodEmbarkation());
		
		return kitcheners.stream()
				.map(KitchenerMapper::mapToKitchenerResponsenDto).toList();
	}

	@Override
	 @Transactional
	public KitchenerResponseDto saveKitchener(KitchenerRequestDto kitchener) {
		
		Optional<Kitchener> optionalKitchener=kitchenerRepository.findByDni(kitchener.getDni())
				.stream()
				.findFirst();
		
		if(optionalKitchener.isPresent()) {
			throw new BadRequestException("Name already exists for Kitchener");
		}
		
		Kitchener kitchenerBd=new Kitchener();
		
		kitchenerBd.setDni(kitchener.getDni());
		kitchenerBd.setName(kitchener.getName().toUpperCase());
		kitchenerBd.setLastname(kitchener.getLastname().toUpperCase());
		kitchenerBd.setPhone(kitchener.getPhone());
		
		kitchenerBd.setEmbarkation(embarkationService.findEmbarkationByIdGeneral(kitchener.getCod_embarkation()));
			
		Kitchener kitchenernDB=kitchenerRepository.save(kitchenerBd);
			
		return KitchenerMapper.mapToKitchenerResponsenDto(kitchenernDB);
	}

	@Override
	 @Transactional
	public KitchenerResponseDto updateKitchener(KitchenerRequestDto kitchener, Long id) {
		
		Kitchener kitchenerBd=this.findKitchenerByIdGeneral(id);
		
		Optional<Kitchener> optionalKitchener=kitchenerRepository.findByDni(kitchener.getDni())
				.stream()
				.filter(p-> p.getId()!=id).findFirst();
		
		if(optionalKitchener.isPresent()) {
			throw new BadRequestException("Name already exists for Kitchener");
		}
		
		
		kitchenerBd.setDni(kitchener.getDni());
		kitchenerBd.setName(kitchener.getName().toUpperCase());
		kitchenerBd.setLastname(kitchener.getLastname().toUpperCase());
		kitchenerBd.setPhone(kitchener.getPhone());		
		kitchenerBd.setEmbarkation(embarkationService.findEmbarkationByIdGeneral(kitchener.getCod_embarkation()));
		
		kitchenerBd.setModifiedBy("SHP-WEB");
		kitchenerBd.setModificationDate(LocalDate.now());	
			
		Kitchener kitchenernDB=kitchenerRepository.save(kitchenerBd);
			
		return KitchenerMapper.mapToKitchenerResponsenDto(kitchenernDB);
	}

	@Override
	 @Transactional
	public void deleteKitchener(Long id) {
		
		Kitchener kitchener=this.findKitchenerByIdGeneral(id);
		kitchener.setActivated(false);
		kitchener.setModifiedBy("SHP-WEB");
		kitchener.setModificationDate(LocalDate.now());
		
		kitchenerRepository.save(kitchener);	
		
	}

	@Override
	@Transactional(readOnly = true)
	public Kitchener findKitchenerByIdGeneral(Long id) {
		
		return  kitchenerRepository.findById(id)
				.filter(p->p.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Kitchener not found with id: " + id));
	}

	@Override
	public List<KitchenerResponseDto> findKitchenerByAll() {
		// TODO Auto-generated method stub
		return kitchenerRepository.findAll()
				.stream()
				.filter(p->p.getActivated().equals(true))
				.map(KitchenerMapper::mapToKitchenerResponsenDto).toList();
	}

}
