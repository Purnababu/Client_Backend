package com.example.Client_Lms.Service;

import java.util.Optional;

import com.example.Client_Lms.Entity.EducationalDetails;


public interface EducationalDetailsService {
	EducationalDetails addOrUpdateEducationalDetails(String employeeId, EducationalDetails educationalDetails);

	Optional<EducationalDetails> getEducationalDetailsByEmployeeId(String employeeId);
}