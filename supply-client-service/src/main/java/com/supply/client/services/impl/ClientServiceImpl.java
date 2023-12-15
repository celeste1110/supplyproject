package com.supply.client.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.supply.client.exceptions.*;
import com.supply.client.models.dto.ClientDto;
import com.supply.client.models.dto.filter.FilterClientDto;
import com.supply.client.models.dto.mapper.ClientMapper;
import com.supply.client.models.entity.Client;
import com.supply.client.repository.ClientRepository;
import com.supply.client.services.ClientService;



@Service
public class ClientServiceImpl implements ClientService{
	
	@Autowired
	private ClientRepository clientRepository;

	@Override
	@Transactional(readOnly = true)
	public Page<ClientDto> findClientByAll(Pageable pageable) {
		
		List<ClientDto> list= clientRepository.findAll( pageable)
				.filter(p->p.getActivated().equals(true))
				.map(ClientMapper::mapToClientDto).toList();
		
		if(list.isEmpty()) {
			throw new ResourceNotFoundException("Page not found " + pageable );
		}
		return new PageImpl<>(list,pageable,list.size());
		
	}

	@Override
	@Transactional(readOnly = true)
	public ClientDto findClientById(String id) {
		
		Client oclient=this.findClientByIdGeneral(id);
		
		
		return ClientMapper.mapToClientDto(oclient);

	}

	@Override
	@Transactional(readOnly = true)
	public List<ClientDto> findClientByRucAndSocialReason(FilterClientDto request) {
		
		List<Client> clients=clientRepository.findClientByRucAndSocialReason(request.getType(), request.getData());
		
		return clients.stream()
				.map(ClientMapper::mapToClientDto).toList();
	}

	@Override
	 @Transactional
	public ClientDto saveClient(ClientDto client) {
		Optional<Client> optionalClient=clientRepository.findByRuc(client.getRuc())
				.stream()
				.findFirst();
		
		if(optionalClient.isPresent()) {
			throw new BadRequestException("Name already exists for Product");
		}
		
		Client clientBd=new Client();
		clientBd.setId(client.getId());
		clientBd.setRuc(client.getRuc());
		clientBd.setSocialReason(client.getSocialReason().toUpperCase());
		clientBd.setAbbreviation(null!=client.getAbbreviation() && !client.getAbbreviation().isEmpty()?client.getAbbreviation().toUpperCase():client.getAbbreviation());
		
		
		Client clientDB=clientRepository.save(clientBd);
			
		return ClientMapper.mapToClientDto(clientDB);
	}

	@Override
	 @Transactional
	public ClientDto updateClient(ClientDto client, String id) {
		
		Client clientBd=this.findClientByIdGeneral(id);
		
		Optional<Client> optionalClient=clientRepository.findByRuc(client.getRuc())
				.stream()
				.filter(p->p.getId() !=id ).findFirst();
		
		if(optionalClient.isPresent()) {
			throw new BadRequestException("Ruc already exists for Client");
		}
		
			
		clientBd.setRuc(client.getRuc());
		clientBd.setSocialReason(client.getSocialReason().toUpperCase());
		clientBd.setAbbreviation(null!=client.getAbbreviation() && !client.getAbbreviation().isEmpty()?client.getAbbreviation().toUpperCase():client.getAbbreviation());
		clientBd.setModifiedBy("SHP-WEB");
		clientBd.setModificationDate(LocalDate.now());		
		
		Client clientDB=clientRepository.save(clientBd);
			
		return ClientMapper.mapToClientDto(clientDB);
	}

	@Override
	 @Transactional
	public void deleteClient(String id) {
		
		Client client=this.findClientByIdGeneral(id);
		client.setActivated(false);
		client.setModifiedBy("SHP-WEB");
		client.setModificationDate(LocalDate.now());
		
		clientRepository.save(client);	
		
	}

	@Override
	@Transactional(readOnly = true)
	public Client findClientByIdGeneral(String id) {
		return clientRepository.findById(id)
				.filter(p->p.getActivated().equals(true))
				.orElseThrow(()->new ResourceNotFoundException("Client not found with id: " + id));
	}

	@Override
	public List<ClientDto> findClientByAllDefault() {
		
		return clientRepository.findAll().stream()
				.filter(p->p.getActivated().equals(true))
				.map(ClientMapper::mapToClientDto).toList();
	}

}
