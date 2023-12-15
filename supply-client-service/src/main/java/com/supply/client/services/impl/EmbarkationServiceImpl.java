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
import com.supply.client.models.dto.EmbarkationRequestDto;
import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.dto.filter.FilterEmbarkationDto;
import com.supply.client.models.dto.mapper.EmbarkationMapper;
import com.supply.client.models.entity.Embarkation;
import com.supply.client.repository.EmbarkationRepository;
import com.supply.client.services.ClientService;
import com.supply.client.services.EmbarkationService;

@Service
public class EmbarkationServiceImpl implements EmbarkationService{
	
	@Autowired
	private EmbarkationRepository embarkationRepository;
	
	@Autowired
	private ClientService clientService;

	@Override
	@Transactional(readOnly = true)
	public Page<EmbarkationResponseDto> findEmbarkationPageableByAll(Pageable pageable) {
		
		List<EmbarkationResponseDto> list= embarkationRepository.findAll( pageable)
				.filter(p->p.getActivated().equals(true))
				.map(EmbarkationMapper::mapToEmbarkatioResponsenDto).toList();
		
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Page not found " + pageable );
		}
		return new PageImpl<>(list,pageable,list.size());
	}

	@Override
	@Transactional(readOnly = true)
	public EmbarkationResponseDto findEmbarkationById(Long id) {
		
		Embarkation oembarkation=this.findEmbarkationByIdGeneral(id);
		
		
		return EmbarkationMapper.mapToEmbarkatioResponsenDto(oembarkation);
	}

	@Override
	@Transactional(readOnly = true)
	public List<EmbarkationResponseDto> findEmbarkationByNameAndClientId(FilterEmbarkationDto request) {
		
		List<Embarkation> embarkations=embarkationRepository.findEmbarkationByNameAndClientId(request.getCodClient(), request.getName());
		
		return embarkations.stream()
				.map(EmbarkationMapper::mapToEmbarkatioResponsenDto).toList();
	}

	@Override
	 @Transactional
	public EmbarkationResponseDto saveEmbarkation(EmbarkationRequestDto embarkation) {
		
		Optional<Embarkation> optionalEmbarkation=embarkationRepository.findByName(embarkation.getName())
				.stream()
				.filter(p->p.getClient().getId()==embarkation.getCod_client())
				.findFirst();
		
		if(optionalEmbarkation.isPresent()) {
			throw new BadRequestException("Name already exists for Embarkation");
		}
		
		Embarkation embarkationBd=new Embarkation();
		
		embarkationBd.setName(embarkation.getName().toUpperCase());
		embarkationBd.setDailyFactor(embarkation.getDailyFactor());
		embarkationBd.setClient(clientService.findClientByIdGeneral(embarkation.getCod_client()));
			
		Embarkation embarkationDB=embarkationRepository.save(embarkationBd);
			
		return EmbarkationMapper.mapToEmbarkatioResponsenDto(embarkationDB);
	}

	@Override
	 @Transactional
	public EmbarkationResponseDto updateEmbarkation(EmbarkationRequestDto embarkation, Long id) {
		
		Embarkation embarkationBd=this.findEmbarkationByIdGeneral(id);
		
		Optional<Embarkation> optionalEmbarkation=embarkationRepository.findByName(embarkation.getName())
				.stream()
				.filter(p->p.getClient().getId()==embarkation.getCod_client()
				&& p.getId()!=id).findFirst();
		
		if(optionalEmbarkation.isPresent()) {
			throw new BadRequestException("Name already exists for Embarkation");
		}
		
		
		
		embarkationBd.setName(embarkation.getName().toUpperCase());
		embarkationBd.setDailyFactor(embarkation.getDailyFactor());
		embarkationBd.setClient(clientService.findClientByIdGeneral(embarkation.getCod_client()));
		embarkationBd.setModifiedBy("SHP-WEB");
		embarkationBd.setModificationDate(LocalDate.now());	
		
		Embarkation embarkationDB=embarkationRepository.save(embarkationBd);
			
		return EmbarkationMapper.mapToEmbarkatioResponsenDto(embarkationDB);
		
	}

	@Override
	 @Transactional
	public void deleteEmbarkation(Long id) {
		
		Embarkation embarkation=this.findEmbarkationByIdGeneral(id);
		embarkation.setActivated(false);
		embarkation.setModifiedBy("SHP-WEB");
		embarkation.setModificationDate(LocalDate.now());
		
		embarkationRepository.save(embarkation);	
		
	}

	@Override
	@Transactional(readOnly = true)
	public Embarkation findEmbarkationByIdGeneral(Long id) {
		return  embarkationRepository.findById(id)
				.filter(p->p.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Embarkation not found with id: " + id));
	}

	@Override
	public List<EmbarkationResponseDto> findEmbarkationByAll() {
		// TODO Auto-generated method stub
		return embarkationRepository.findAll()
				.stream()
				.filter(p->p.getActivated().equals(true))
				.map(EmbarkationMapper::mapToEmbarkatioResponsenDto).toList();
	}

}
