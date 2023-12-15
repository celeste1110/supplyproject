package com.supply.client.models.dto.mapper;

import com.supply.client.models.dto.EmbarkationResponseDto;
import com.supply.client.models.entity.Embarkation;

public class EmbarkationMapper {

public static EmbarkationResponseDto mapToEmbarkatioResponsenDto(Embarkation embarkation) {
		
	EmbarkationResponseDto embarkationResponseDto=new EmbarkationResponseDto(
			embarkation.getId(),
			embarkation.getName(),
			embarkation.getDailyFactor(),
			ClientMapper.mapToClientDto(embarkation.getClient())
				);
		
		return embarkationResponseDto;
	}
}
