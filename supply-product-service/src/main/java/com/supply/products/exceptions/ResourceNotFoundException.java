package com.supply.products.exceptions;

public class ResourceNotFoundException extends RuntimeException{
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException() {
	        super("Recurso no encontrado en el servidor");
	    }

	    public ResourceNotFoundException(String message) {
	        super(message);
	    }

}
