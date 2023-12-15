package com.supply.client.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="embarkation",schema="dbo")
public class Embarkation implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_embarkation", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	@Column(name="name", length=75,nullable = false)
	@NotEmpty
	private String name;
	@Column(name="daily_factor",columnDefinition = "DECIMAL(14,2)",nullable = false)
	@NotNull
	private double dailyFactor;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_client",columnDefinition = "VARCHAR(5)")
	private Client client;
	@JsonIgnore
	@Column(name="created_by", length=50)
	private String createdBy;
	@JsonIgnore
	@Column(name="creation_date",columnDefinition ="datetime2(6) default CURRENT_TIMESTAMP")
	private LocalDate creationDate;
	@JsonIgnore
	@Column(name="modified_by", length=50)
	private String modifiedBy;
	@JsonIgnore
	@Column(name="modification_date",columnDefinition ="datetime2(6)")
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
		Embarkation other = (Embarkation) obj;
		return Objects.equals(id, other.id);
	}
	
	
	private static final long serialVersionUID = 1L;
	
	

}
