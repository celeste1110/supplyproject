package com.supply.products.models.dto.mapper;

import com.supply.products.models.dto.MeasuringUnitDto;
import com.supply.products.models.entity.MeasuringUnit;

public class MeasuringUnitMapper {
	
public static MeasuringUnitDto mapToMeasuringUnitDto(MeasuringUnit munit) {
		
	MeasuringUnitDto umedidaDto=new MeasuringUnitDto(
			munit.getId(),
			munit.getNameMUnit(),
			munit.getSymbol()
				);
		
		return umedidaDto;
	}

}
