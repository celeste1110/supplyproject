package com.supply.client.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.supply.client.models.dto.EmbarkationRequestDto;
import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.dto.filter.FilterEmbarkationDto;
import com.supply.client.models.entity.Embarkation;

public interface EmbarkationService {
	public Page<EmbarkationResponseDto> findEmbarkationPageableByAll(Pageable pageable);
	public EmbarkationResponseDto findEmbarkationById(Long id);
	public List<EmbarkationResponseDto> findEmbarkationByNameAndClientId(FilterEmbarkationDto request);
	public EmbarkationResponseDto saveEmbarkation(EmbarkationRequestDto embarkation) ;
	public EmbarkationResponseDto updateEmbarkation(EmbarkationRequestDto embarkation,Long id);
	public void deleteEmbarkation(Long id);
	public Embarkation findEmbarkationByIdGeneral(Long id);
	public List<EmbarkationResponseDto> findEmbarkationByAll();

}
