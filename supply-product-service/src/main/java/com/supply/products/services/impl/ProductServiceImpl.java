package com.supply.products.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.products.exceptions.BadRequestException;
import com.supply.products.exceptions.ResourceNotFoundException;
import com.supply.products.models.dto.FilterProductDto;
import com.supply.products.models.dto.ProductRequestDto;
import com.supply.products.models.dto.ProductResponseDto;
import com.supply.products.models.dto.mapper.ProductMapper;
import com.supply.products.models.entity.Product;
import com.supply.products.repository.ProductRepository;
import com.supply.products.services.CategoryService;
import com.supply.products.services.MeasuringUnitService;
import com.supply.products.services.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private MeasuringUnitService measuringUnitService;

	@Override
	@Transactional(readOnly = true)
	public Page<ProductResponseDto> findProductByAll(Pageable pageable) {
		
	
			
			List<ProductResponseDto> list= productRepository.findAll( pageable)
					.filter(p->p.getActivated().equals(true))
					.map(ProductMapper::mapToProductDto).toList();
			
			if(list.isEmpty()) {
				throw new ResourceNotFoundException("Page not found " + pageable );
			}
			return new PageImpl<>(list,pageable,list.size());
			

		
	}
	
	@Override
	@Transactional(readOnly = true)
	public ProductResponseDto findProductById(Long id) {
	
		Product oproduct=this.findProductByIdGeneral(id);
		
		
			return ProductMapper.mapToProductDto(oproduct);
	}


	@Override
	@Transactional(readOnly = true)
	public List<ProductResponseDto> findProductByName(FilterProductDto request) {
		
		List<Product> products=productRepository.findAll();
		
		return products.stream().filter(p->p.getActivated().equals(true) &&
				p.getName().toLowerCase().contains(request.getName().toLowerCase()))
				.map(ProductMapper::mapToProductDto).toList();
	}

	@Override
	 @Transactional
	public ProductResponseDto saveProduct(ProductRequestDto productDto) {
		
		Optional<Product> optionalProduct=productRepository.findByName(productDto.getName().toUpperCase())
				.stream()
				.findFirst();
		
		if(optionalProduct.isPresent()) {
			throw new BadRequestException("Name already exists for Product");
		}
		
		Product product=new Product();
		
		product.setName(productDto.getName().toUpperCase());
		product.setPrice(productDto.getPrice());
		
		product.setCategory(categoryService.findCategoryByIdGeneral(productDto.getCodCategory()));
		product.setMeasuringUnit(measuringUnitService.finddMeasuringUnitByIdGeneral(productDto.getCodMUnit()));
		Product productDB=productRepository.save(product);
			
		return ProductMapper.mapToProductDto(productDB);
	}

	@Override
	 @Transactional
	public ProductResponseDto updateProduct(ProductRequestDto productDto,Long id) {
		
		Product product=this.findProductByIdGeneral(id);
		
		Optional<Product> optionalProduct=productRepository.findByName(productDto.getName().toUpperCase())
				.stream()
				.filter(p->p.getId()!=id).findFirst();
		
		if(optionalProduct.isPresent() ) {
			throw new BadRequestException("Name already exists for Product");
		}
		
		product.setName(productDto.getName().toUpperCase());
		product.setPrice(productDto.getPrice());
		
		product.setCategory(categoryService.findCategoryByIdGeneral(productDto.getCodCategory()));
		product.setMeasuringUnit(measuringUnitService.finddMeasuringUnitByIdGeneral(productDto.getCodMUnit()));
		
		product.setModifiedBy("SHP-WEB");
		product.setModificationDate(LocalDate.now());
		
		Product productDB=productRepository.save(product);
		
		return ProductMapper.mapToProductDto(productDB);
		
		
	}

	@Override
	 @Transactional
	public void deleteProduct(Long id) {
		Product product=this.findProductByIdGeneral(id);
		product.setActivated(false);
		product.setModifiedBy("SHP-WEB");
		product.setModificationDate(LocalDate.now());
		
		productRepository.save(product);		
		
	}

	@Override
	@Transactional(readOnly = true)
	public Product findProductByIdGeneral(Long id) {
		
		return productRepository.findById(id)
				.filter(p->p.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Product not found with id: " + id));
	}

	@Override
	public List<ProductResponseDto> findProductByAllDefault() {
		
		return productRepository.findAll().stream()
				.filter(p->p.getActivated().equals(true))
				.map(ProductMapper::mapToProductDto).toList();
	}





}
