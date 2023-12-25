package com.supply.catagregate.models.documents.dto.mapper;

import com.supply.catagregate.models.documents.AttentionPort;
import com.supply.catagregate.models.documents.dto.AttentionPortDto;

public class AttentionPortMapper {
	
public static AttentionPortDto mapToAttentionPortDto(AttentionPort attentionPort) {
		
		return AttentionPortDto.builder()
				.id(attentionPort.getId())
				.name(attentionPort.getName())
				
				.build();
	}

}
