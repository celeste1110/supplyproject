package com.supply.orders.models.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderRequestDto {
	
	private Long id;
	
	private Integer quantifyDays;
	
	private Integer numberCrew;
	
	private String nroSolped;
	
	private LocalDate sailDate;
	
	private Long codState;
	
	private Long codKitchener;
	
	private Long codEmbarkation;
	
	private String codSupplier;
	
	private String codAport;
	
	private String comment;
	
	private String commentDelivery;
	
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
		OrderRequestDto other = (OrderRequestDto) obj;
		return Objects.equals(id, other.id);
	}

}
