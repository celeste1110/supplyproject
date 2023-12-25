package com.supply.catagregate.models.documents.dto.mapper;



import com.supply.catagregate.models.documents.Supplier;
import com.supply.catagregate.models.documents.dto.SupplierDto;

public class SupplierDtoMapper {
	
public static SupplierDto mapToSupplierDto(Supplier supplier) {
		
		return SupplierDto.builder()
				.id(supplier.getId())
				.name(supplier.getName())
				
				.build();
	}


}
