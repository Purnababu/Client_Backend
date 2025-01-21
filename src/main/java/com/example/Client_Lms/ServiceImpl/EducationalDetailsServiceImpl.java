package com.example.Client_Lms.ServiceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Client_Lms.Entity.EducationalDetails;
import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Repoisitory.EducationalDetailsRepository;
import com.example.Client_Lms.Repoisitory.EmployeeRepoisitory;
import com.example.Client_Lms.Service.EducationalDetailsService;

@Service
public class EducationalDetailsServiceImpl implements EducationalDetailsService {

	@Autowired
	private EducationalDetailsRepository educationalDetailsRepository;

	@Autowired
	private EmployeeRepoisitory employeeRepository;

	@Override
	public EducationalDetails addOrUpdateEducationalDetails(String employeeId, EducationalDetails educationalDetails) {
		Employee employee = employeeRepository.findById(employeeId)
				.orElseThrow(() -> new RuntimeException("Employee not found with id: " + employeeId));

		educationalDetails.setEmployee(employee);
		return educationalDetailsRepository.save(educationalDetails);
	}

	@Override
	public Optional<EducationalDetails> getEducationalDetailsByEmployeeId(String employeeId) {
		return Optional.ofNullable(educationalDetailsRepository.findByEmployeeEmployeeId(employeeId));
	}

}
