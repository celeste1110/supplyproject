package com.supply.orders.models.entity;

import java.io.Serializable;
import java.util.Objects;



import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="order_detail",schema="dbo")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class OrderDetailEntity implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", columnDefinition = "NUMERIC(18,0)")
	private Long id;
	@Column(name="cod_order", columnDefinition = "NUMERIC(18,0)")
	private Long codOrder;
	@Column(name="cod_product", columnDefinition = "NUMERIC(18,0)")
	
	private Long codProduct;
	@Column(name="quantify", columnDefinition = "INT", nullable = false)
	private Integer quantify;
	
	@Column(name="comment", columnDefinition = "VARCHAR(MAX)")
	private String comment;

	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDetailEntity other = (OrderDetailEntity) obj;
		return Objects.equals(id, other.id);
	}

	
	
	private static final long serialVersionUID = 1L;
	
	

}
