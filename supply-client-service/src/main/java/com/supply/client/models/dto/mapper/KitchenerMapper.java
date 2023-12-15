package com.supply.client.models.dto.mapper;

import com.supply.client.models.dto.KitchenerResponseDto;
import com.supply.client.models.entity.Kitchener;

public class KitchenerMapper {

	public static KitchenerResponseDto mapToKitchenerResponsenDto(Kitchener kitchener) {
		
		KitchenerResponseDto kitchenerResponseDto=new KitchenerResponseDto(
				kitchener.getId(),
				kitchener.getDni(),
				kitchener.getName(),
				kitchener.getLastname(),
				kitchener.getName()+ " " + kitchener.getLastname(),
				kitchener.getPhone(),
				EmbarkationMapper.mapToEmbarkatioResponsenDto(kitchener.getEmbarkation())
					);
			
			return kitchenerResponseDto;
		}
}
