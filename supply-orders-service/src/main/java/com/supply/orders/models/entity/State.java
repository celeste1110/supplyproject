package com.supply.orders.models.entity;

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
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="state",schema="dbo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class State implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_state", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	
	@NotEmpty
	@Column(name="description", length=75,nullable = false)
	private String description;
	
	@JsonIgnore
	@Column(name="created_by", length=50)
	private String createdBy;
	
	@JsonIgnore
	@Column(name="creation_date",columnDefinition ="datetime2(6) default CURRENT_TIMESTAMP")
	private LocalDate creationDate;
	
	@JsonIgnore
	@Column(name="modified_by", length=50)
	private String modifiedBy;
	
	@Column(name="modification_date",columnDefinition ="datetime2(6)")
	@JsonIgnore
	private LocalDate modificationDate;

	@PrePersist
	public void prePersist() {
		this.creationDate=LocalDate.now();
		this.createdBy="SHP-WEB";

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
		State other = (State) obj;
		return Objects.equals(id, other.id);
	}

	
	
	private static final long serialVersionUID = 1L;
	
	

}
