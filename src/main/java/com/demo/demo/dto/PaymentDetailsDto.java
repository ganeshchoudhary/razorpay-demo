package com.demo.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PaymentDetailsDto implements Serializable {
	
	@NotEmpty(message = "Please enter user name")
	@Length(min = 2)
	private String userName;
	
	@Email(message = "Please enter valid email id")
	private String email;
	
	@Length(max = 10, min = 10, message = "Please enter valid phone number")
	private String phoneNumber;
	
	@NotEmpty
	@Length(min = 1, message = "Please enter valid amount")
	private String amount;
}
