package com.supply.client.models.dto.mapper;

import com.supply.client.models.dto.ClientDto;
import com.supply.client.models.entity.Client;

public class ClientMapper {
	
public static ClientDto mapToClientDto(Client client) {
		
		ClientDto clientDto=new ClientDto(
				client.getId(),
				client.getRuc(),
				client.getSocialReason(),
				client.getAbbreviation()
				);
		
		return clientDto;
	}


}
