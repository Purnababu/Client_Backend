package com.example.Client_Lms.Entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentEnrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Student Details
	private String fullName;
	private LocalDate dateOfBirth;
	private String contactNumber;
	private String emailAddress;

	// Course Details
	private String courseName;
	private String gradeBatch;
	private LocalDate enrollmentDate;

	// Admission Details
	private String admissionNumber;

	// Fee Details
	private double totalFee;
	private double amountPaid;
	private LocalDate paymentDate;
	private String paymentMode; // e.g., Cash, Bank Transfer, UPI

	// Balance Due
	private double balanceAmount;
	private String feeStatus; // e.g., Paid in Full, Partially Paid, Overdue
	
	
	 private LocalDate localDate;  // This field should match the query
}
