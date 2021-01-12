package com.demo.demo.service;

import com.demo.demo.dto.PaymentDetailsDto;
import com.demo.demo.dto.SuccessPaymentDetailsDto;

public interface Payment {
	
	SuccessPaymentDetailsDto createPayment(PaymentDetailsDto paymentDetailsDto);

}
