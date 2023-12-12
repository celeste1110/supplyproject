package com.supply.products.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="measuring_unit")

public class MeasuringUnit implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_munit")
	private Long id;
	@Column(name="name",columnDefinition = "VARCHAR(75)",nullable = false)
	@NotEmpty
	private String nameMUnit;
	@Column(name="symbol",columnDefinition = "VARCHAR(10)",nullable = false)
	private String symbol;
	@JsonIgnore
	@Column(name="created_by", length=50)
	private String createdBy;
	@JsonIgnore
	@Column(name="creation_date",columnDefinition = "TIMESTAMP")
	private LocalDate creationDate;
	@JsonIgnore
	@Column(name="modified_by", length=50)
	private String  modifiedBy;
	@JsonIgnore
	@Column(name="modification_date",columnDefinition = "TIMESTAMP")
	private LocalDate modificationDate;
	@JsonIgnore
	@Column(name="activated")
	private Boolean activated;
	
	@PrePersist
	public void prePersist() {
		this.creationDate=LocalDate.now();
		this.createdBy="SHP-WEB";
		this.activated=true;
	}
	
	
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MeasuringUnit other = (MeasuringUnit) obj;
		return Objects.equals(id, other.id);
	}
	
	
	
	private static final long serialVersionUID = 1L;
	
	

}
