package com.supply.products.services;

import java.util.List;

import com.supply.products.models.dto.MeasuringUnitDto;
import com.supply.products.models.entity.MeasuringUnit;

public interface MeasuringUnitService {
	
	public List<MeasuringUnitDto> findMeasuringUnitAll();
	public MeasuringUnitDto finddMeasuringUnitById(Long id);
	public MeasuringUnit finddMeasuringUnitByIdGeneral(Long id);

}
