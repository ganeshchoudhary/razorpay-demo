package com.demo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SuccessPaymentDetailsDto {
	
	private String amount;
	
	private String paymentId;
	
	private String customerName;
	
	private String customerEmail;
	
	private String customerContact;
}
