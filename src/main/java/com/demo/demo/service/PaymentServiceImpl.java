package com.demo.demo.service;

import com.demo.demo.dto.PaymentDetailsDto;
import com.demo.demo.dto.SuccessPaymentDetailsDto;
import com.demo.demo.entity.PaymentDetailEntity;
import com.demo.demo.exceptions.PaymentFailureException;
import com.demo.demo.repository.PaymentDetailRepository;
import com.razorpay.Order;
import com.razorpay.RazorpayClient;
import com.razorpay.RazorpayException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements Payment {
	
	
	private RazorpayClient client;
	private PaymentDetailRepository paymentDetailRepository;
	private Logger logger = LoggerFactory.getLogger(PaymentServiceImpl.class);
	
	@Autowired
	public PaymentServiceImpl(PaymentDetailRepository paymentDetailRepository, RazorpayClient razorpayClient) throws RazorpayException {
		this.paymentDetailRepository = paymentDetailRepository;
		this.client = razorpayClient;
	}
	
	/*
	Creating payment request to razorpay
	 */
	@Override
	public SuccessPaymentDetailsDto createPayment(PaymentDetailsDto paymentDetailsDto) {
		logger.info("Making new payment.");
		try {
			Order order = makeRazorPayPayment(paymentDetailsDto.getAmount());
			SuccessPaymentDetailsDto PaymentDetails = getRazorPay(order.get("id").toString(), paymentDetailsDto);
			PaymentDetailEntity paymentDetailEntity = new PaymentDetailEntity();
			paymentDetailEntity.setAmount(PaymentDetails.getAmount());
			paymentDetailEntity.setEmail(PaymentDetails.getCustomerEmail());
			paymentDetailEntity.setPhoneNumber(paymentDetailsDto.getPhoneNumber());
			paymentDetailEntity.setUserName(PaymentDetails.getCustomerName());
			paymentDetailEntity.setTransactionId(PaymentDetails.getPaymentId());
			this.paymentDetailRepository.save(paymentDetailEntity);
			return PaymentDetails;
		} catch (RazorpayException e) {
			logger.error(e.toString());
			throw new PaymentFailureException("Payment failed. try after sometime", e.getMessage());
		}
	}
	
	
	private SuccessPaymentDetailsDto getRazorPay(String orderId, PaymentDetailsDto user) {
		SuccessPaymentDetailsDto successPaymentDetailsDto = new SuccessPaymentDetailsDto();
		successPaymentDetailsDto.setAmount(user.getAmount());
		successPaymentDetailsDto.setCustomerName(user.getUserName());
		successPaymentDetailsDto.setCustomerEmail(user.getEmail());
		successPaymentDetailsDto.setCustomerContact(user.getPhoneNumber());
		successPaymentDetailsDto.setPaymentId(orderId);
		return successPaymentDetailsDto;
	}
	
	private Order makeRazorPayPayment(String amount) throws RazorpayException {
		JSONObject options = new JSONObject();
		options.put("amount", amount);
		options.put("currency", "INR");
		options.put("receipt", "txn_123456");
		options.put("payment_capture", 1);
		return client.Orders.create(options);
	}
	
}
