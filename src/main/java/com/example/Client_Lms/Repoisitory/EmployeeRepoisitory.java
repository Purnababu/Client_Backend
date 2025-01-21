package com.example.Client_Lms.Repoisitory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Client_Lms.Entity.Employee;

@Repository
public interface EmployeeRepoisitory extends JpaRepository<Employee, String> {

	Employee findByEmployeeId(String employeeId);

	List<Employee> findByLocalDate(LocalDate now);

	@Query("SELECT e FROM Employee e WHERE e.deadlineDate <= :currentDate")
	List<Employee> findEmployeesWithExpiredDeadlines(@Param("currentDate") LocalDate currentDate);
	
	
	
	


}
