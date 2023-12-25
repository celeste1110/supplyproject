package com.supply.orders.models.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.supply.orders.models.AttentionPort;
import com.supply.orders.models.Embarkation;
import com.supply.orders.models.Kitchener;
import com.supply.orders.models.Supplier;
import com.supply.orders.models.entity.State;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {

	private Long id;
	
	private Integer quantifyDays;
	
	private Integer numberCrew;
	
	private String nroSolped;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime orderDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate sailDate;
	
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 	
	private State state;
	
	private Kitchener kitchener;
	
	private Embarkation embarkation;
	
	private Supplier supplier;
	
	private AttentionPort attentionPort;

	private String comment;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime confirmationDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime purchaseDate;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deliveyDate;
	
	private String commentDelivery;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime receivedDate;
	
	private String ratingReceived;
	
	private String commentReceived;
	
	private Integer spendAmount;

	private List<OrderDetailDto> orderDetail;
	
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrderDto other = (OrderDto) obj;
		return Objects.equals(id, other.id);
	}
}
