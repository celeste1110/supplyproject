package com.supply.products.models.dto.mapper;

import com.supply.products.models.dto.CategoryDto;
import com.supply.products.models.entity.Category;

public class CategoryMapper {
	
	public static CategoryDto mapToCategoryDto(Category category) {
		
		CategoryDto categoriaDto=new CategoryDto(
				category.getCodCategory(),
				category.getNameCategory()
				);
		
		return categoriaDto;
	}

}
