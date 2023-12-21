package com.supply.catagregate.models.entity;

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
@Table(name="attention_port" , schema="dbo")
public class AttentionPort {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_aport", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	@Column(name="name",columnDefinition = "VARCHAR(75)",nullable = false)
	@NotEmpty
	private String name;

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
		AttentionPort other = (AttentionPort) obj;
		return Objects.equals(id, other.id);
	}

}
