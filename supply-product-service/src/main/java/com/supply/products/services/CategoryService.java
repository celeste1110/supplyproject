package com.supply.products.services;

import java.util.List;


import com.supply.products.models.dto.CategoryDto;
import com.supply.products.models.entity.Category;

public interface CategoryService {
	
	public List<CategoryDto> findCategoryAll();
	
	public CategoryDto findCategoryById(Long id);
	
	public Category findCategoryByIdGeneral(Long id);
	

}
