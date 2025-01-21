package com.example.Client_Lms.Repoisitory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Client_Lms.Entity.EducationalDetails;

@Repository
public interface EducationalDetailsRepository extends JpaRepository<EducationalDetails, Long> {
	EducationalDetails findByEmployeeEmployeeId(String employeeId);
}
