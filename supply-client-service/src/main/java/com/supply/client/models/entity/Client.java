package com.supply.client.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="client",schema="dbo")
public class Client implements Serializable{
	
	@Id
	@Column(name="cod_client", length=5,nullable = false)
	@NotEmpty
	private String id;
	@Column(name="ruc", length=15,nullable = false)
	@NotEmpty
	private String ruc;
	
	@Column(name="social_reason", length=100,nullable = false)
	@NotEmpty
	private String socialReason;
	
	@Column(name="abbreviation", length=75)
	private String abbreviation;
	
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
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
		
	}
	
	
	private static final long serialVersionUID = 1L;
	
	
	
	
	

}
