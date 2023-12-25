package com.supply.catagregate.models.documents;

import java.time.LocalDate;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Document(collection ="attention_port")
@Getter
@Setter
public class AttentionPort {
	
	@Id
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
		AttentionPort other = (AttentionPort) obj;
		return Objects.equals(id, other.id);
	}

}
