package com.example.Client_Lms.Entity;

import java.time.LocalDate;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "transaction_date", nullable = false)
	private LocalDate transactionDate;

	@Column(nullable = false)
	private String transactionType; // "Income" or "Expense"

	@Column(length = 255, nullable = false)
	private String description;

	@Column(nullable = false)
	private double amount;

	@Column(nullable = false)
	private String paymentMode; // "Cash", "Bank Transfer", or "UPI"

}
