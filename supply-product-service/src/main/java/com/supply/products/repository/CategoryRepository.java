package com.supply.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.supply.products.models.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
