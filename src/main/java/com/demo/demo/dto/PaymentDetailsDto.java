package com.demo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetailsDto {
	
	@NotEmpty(message = "Please enter user name")
	@Min(value = 3)
	private String userName;
	
	@Email(message = "Please enter valid email id")
	private String email;
	
	@Min(value = 10)
	@Max(value = 10, message = "Please enter valid phone number")
	private String phoneNumber;
	
	@NotEmpty
	@Min(value = 1, message = "Please enter valid amount")
	private String amount;
}
