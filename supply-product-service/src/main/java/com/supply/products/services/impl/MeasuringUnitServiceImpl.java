package com.supply.products.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.products.exceptions.ResourceNotFoundException;
import com.supply.products.models.dto.MeasuringUnitDto;
import com.supply.products.models.dto.mapper.MeasuringUnitMapper;
import com.supply.products.models.entity.MeasuringUnit;
import com.supply.products.repository.MeasuringUnitRepository;
import com.supply.products.services.MeasuringUnitService;


@Service
public class MeasuringUnitServiceImpl implements MeasuringUnitService{
	
	@Autowired
	private MeasuringUnitRepository repository;

	@Override
	@Transactional(readOnly = true)
	public List<MeasuringUnitDto> findMeasuringUnitAll() {

		 List<MeasuringUnit> munit=repository.findAll();
		 
		return munit.stream().filter(u->u.getActivated().equals(true))
				.map(MeasuringUnitMapper::mapToMeasuringUnitDto).toList();
	}

	@Override
	@Transactional(readOnly = true)
	public MeasuringUnitDto finddMeasuringUnitById(Long id) {
		
		MeasuringUnit measuringUnit=this.finddMeasuringUnitByIdGeneral(id);
		
		return MeasuringUnitMapper.mapToMeasuringUnitDto(measuringUnit);
	}

	@Override
	public MeasuringUnit finddMeasuringUnitByIdGeneral(Long id) {
		
		return repository.findById(id).filter(u->u.getActivated().equals(true))
				.orElseThrow(()-> new ResourceNotFoundException("MeasuringUnit not found with id: " + id));
	}



}
