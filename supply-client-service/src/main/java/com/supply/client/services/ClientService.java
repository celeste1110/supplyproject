package com.supply.client.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.supply.client.models.dto.ClientDto;
import com.supply.client.models.dto.filter.FilterClientDto;
import com.supply.client.models.entity.Client;


public interface ClientService {
	
	public Page<ClientDto> findClientByAll(Pageable pageable);
	public ClientDto findClientById(String id);
	public List<ClientDto> findClientByRucAndSocialReason(FilterClientDto request);
	public ClientDto saveClient(ClientDto client) ;
	public ClientDto updateClient(ClientDto client,String id);
	public void deleteClient(String id);
	public Client findClientByIdGeneral(String id);
	
	public List<ClientDto> findClientByAllDefault();

}
