package com.demo.demo.converter;

import com.demo.demo.dto.PaymentDetailsDto;
import com.demo.demo.entity.PaymentDetailEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class PaymentDetailsConverter {
	
	private final ModelMapper modelMapper = new ModelMapper();
	
	public PaymentDetailEntity getServiceDetailEntity(PaymentDetailsDto paymentDetailsDto){
		return modelMapper.map(paymentDetailsDto, PaymentDetailEntity.class);
	}
	
	public PaymentDetailsDto getDeploymentDetailsDto(PaymentDetailEntity paymentDetailEntity){
		return modelMapper.map(paymentDetailEntity == null ? new PaymentDetailEntity() : paymentDetailEntity, PaymentDetailsDto.class);
	}
	
}
