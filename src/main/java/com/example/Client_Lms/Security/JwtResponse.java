package com.example.Client_Lms.Security;


import com.example.Client_Lms.Entity.Employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponse {
	
	private Employee employee;

	private String jwtToken;
	
	private String employeeId;

}
