package com.supply.catagregate.models.documents;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Document(collection ="supplier")
@Getter
@Setter
public class Supplier implements Serializable{
	
	@Id
	@JsonSerialize(using = ToStringSerializer.class)
	private String id;
	@NotEmpty
	private String name;
	
	@JsonIgnore
	private String createdBy;
	
	@JsonIgnore
	private LocalDate creationDate;
	
	@JsonIgnore
	private String modifiedBy;
	
	@JsonIgnore
	private LocalDate modificationDate;
	
		
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Supplier other = (Supplier) obj;
		return Objects.equals(id, other.id);
	}


	private static final long serialVersionUID = 1L;
	
	

}
