package com.supply.orders.models.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="orders",schema="dbo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="cod_order", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	
	@Column(name="quantify_days", columnDefinition = "INT", nullable = false)
	private Integer quantifyDays;
	
	@Column(name="number_crew", columnDefinition = "INT", nullable = false)
	private Integer numberCrew;
	
	@Column(name="nro_solped", columnDefinition = "VARCHAR(60)")
	private String nroSolped;
	
	@Column(name="order_date",columnDefinition ="datetime2(7)")
	private LocalDateTime orderDate;
	
	@Column(name="sail_date",columnDefinition ="datetime2(7)")
	private LocalDate sailDate;
	
	//@Column(name="cod_state", columnDefinition = "NUMERIC(18,0)")
	//private Long codState;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="cod_state",columnDefinition = "NUMERIC(18,0)")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 	
	private State state;
	
	@Column(name="cod_kitchener", columnDefinition = "NUMERIC(18,0)")
	private Long codKitchener;
	
	@Column(name="name_kitchener", columnDefinition = "VARCHAR(200)")
	private String nameKitchener;
	
	@Column(name="cod_embarkation", columnDefinition = "NUMERIC(18,0)")
	private Long codEmbarkation;
	
	@Column(name="cod_supplier", columnDefinition = "VARCHAR(10)")
	private String codSupplier;
	
	@Column(name="cod_aport", columnDefinition = "VARCHAR(10)")
	private String codAport;
	
	@Column(name="comment", columnDefinition = "VARCHAR(MAX)")
	private String comment;
	
	@Column(name="confirmation_date",columnDefinition ="datetime2(7)")
	private LocalDateTime confirmationDate;
	
	@Column(name="purchase_date",columnDefinition ="datetime2(7)")
	private LocalDateTime purchaseDate;
	
	@Column(name="delivey_date",columnDefinition ="datetime2(7)")
	private LocalDateTime deliveyDate;
	
	@Column(name="comment_delivery", columnDefinition = "VARCHAR(MAX)")
	private String commentDelivery;
	
	@Column(name="received_date",columnDefinition ="datetime2(7)")
	private LocalDateTime receivedDate;
	
	@Column(name="rating_received", columnDefinition = "VARCHAR(50)")
	private String ratingReceived;
	
	@Column(name="comment_received", columnDefinition = "VARCHAR(MAX)")
	private String commentReceived;
	
	@Column(name="spend_amount", columnDefinition = "NUMERIC(12,2)")
	private Integer spendAmount;

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderEntity other = (OrderEntity) obj;
		return Objects.equals(id, other.id);
	}

	private static final long serialVersionUID = 1L;

	
}
