package com.supply.client.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.supply.client.models.dto.KitchenerRequestDto;
import com.supply.client.models.dto.KitchenerResponseDto;
import com.supply.client.models.dto.filter.FilterKitchenerDto;
import com.supply.client.models.entity.Kitchener;

public interface KitchenerService {
	
	public Page<KitchenerResponseDto> findKitchenerPageableByAll(Pageable pageable);
	public KitchenerResponseDto findKitchenerById(Long id);
	public List<KitchenerResponseDto> findKitchenerByNameAndDniAndEmbarcation(FilterKitchenerDto request);
	public KitchenerResponseDto saveKitchener(KitchenerRequestDto kitchener) ;
	public KitchenerResponseDto updateKitchener(KitchenerRequestDto kitchenern,Long id);
	public void deleteKitchener(Long id);
	public Kitchener findKitchenerByIdGeneral(Long id);
	public List<KitchenerResponseDto> findKitchenerByAll();

}
