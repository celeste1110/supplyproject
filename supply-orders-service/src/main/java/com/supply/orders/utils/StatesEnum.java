package com.supply.orders.utils;

public enum StatesEnum {

	SOLICITADO(1L),
	PENDIENTE(2L),
	COMPRADO(3L),
	ANULADO(4L),
	ENTREGADO(5L),
	RECIBIDO(6L);
	
	private Long codState;
	
	private StatesEnum(Long codState) {
		this.codState=codState;
	}

	public Long getCodState() {
		return codState;
	}

	
}
