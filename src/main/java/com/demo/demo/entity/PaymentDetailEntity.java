package com.demo.demo.entity;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class PaymentDetailEntity {
	
	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "user_emailId", nullable = false)
	private String email;
	
	@Column(name = "user_contact_number", nullable = false)
	private String phoneNumber;
	
	@Column(name = "amount", nullable = false)
	private String amount;
	
	@Column(name = "transaction_id", nullable = false)
	private String transactionId;
}
