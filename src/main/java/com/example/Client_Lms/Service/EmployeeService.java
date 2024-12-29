package com.example.Client_Lms.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Client_Lms.Entity.Employee;

public interface EmployeeService {

	String initRoleAndAdmin();

	public Employee addEmployee(Employee employee,String roleName) throws Exception;

	public Employee addAdmin(Employee admin);

	public List<Employee> getEmployeesByDate() throws Exception;
	
	
	void updateUserImagePathAndStoreInDatabase(String email, MultipartFile file) throws IOException;

	

	byte[] getPhotoBytesByEmployeeId(String employeeId) throws IOException;
	
	
	
	
	 List<Employee> getEmployeesWithExpiredDeadlines();
	 
	 int updateLoginStatusForAllEmployees();

}
