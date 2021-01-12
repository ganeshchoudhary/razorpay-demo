package com.demo.demo.controller;

import com.demo.demo.dto.PaymentDetailsDto;
import com.demo.demo.exceptions.PaymentFailureException;
import com.demo.demo.service.Payment;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin
public class PaymentRestController {
	
	
	private Payment paymentService;
	
	@Autowired
	public PaymentRestController(Payment paymentService) {
		this.paymentService = paymentService;
	}
	
	@GetMapping("/health-check")
	public ResponseEntity checkHealth() {
		return new ResponseEntity<>("Ok", HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "To make new payments")
	@ApiResponses(value = {@ApiResponse(code = 200, message = "Makes new payment"),})
	@RequestMapping(method = RequestMethod.POST, path = "/api/v1/create-payment", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity updateDeploymentDetails(@RequestBody @Valid PaymentDetailsDto paymentDetailsDto) {
		try {
			return new ResponseEntity<>(this.paymentService.createPayment(paymentDetailsDto), HttpStatus.OK);
			
		} catch (PaymentFailureException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.FAILED_DEPENDENCY);
			
		}
	}
	
	
}
