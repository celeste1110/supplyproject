package com.supply.users.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.supply.users.models.Client;

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
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="s_user",schema="dbo")
public class User implements Serializable{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_user", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	@Column(name="username", length=50,nullable = false)
	@NotEmpty
	private String username;
	@Column(name="password",  columnDefinition = "VARCHAR(MAX)",nullable = false)
	@NotEmpty
	private String password;
	
	@Column(name="name", length=75,nullable = false)
	@NotEmpty
	private String name;
	
	@Column(name="lastname", length=75,nullable = false)
	@NotEmpty
	private String lastname;
	
	@Column(name="dni", length=15,nullable = false)
	@NotEmpty
	private String dni;
	
	
	@Column(name="cod_client", columnDefinition = "VARCHAR(5)",nullable = false)
	@NotNull
	private String codClient;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_role",columnDefinition = "NUMERIC(18,0)")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
	private Role role;
	
	@Transient
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
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	private static final long serialVersionUID = 1L;


}
