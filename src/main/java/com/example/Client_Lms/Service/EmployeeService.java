package com.example.Client_Lms.Service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.StudentEnrollment;


public interface EmployeeService {

	String initRoleAndAdmin();

	public Employee addEmployee(Employee employee, String roleName) throws Exception;

	public Employee addAdmin(Employee admin);

	public List<Employee> getEmployeesByDate() throws Exception;

	void updateUserImagePathAndStoreInDatabase(String email, MultipartFile file) throws IOException;

	byte[] getPhotoBytesByEmployeeId(String employeeId) throws IOException;

	List<Employee> getEmployeesWithExpiredDeadlines();

	int updateLoginStatusForAllEmployees();

	List<Employee> gellAllEmployess();
	
	
	
//	-----------------------------------------------------------------------

	StudentEnrollment registerStudent(StudentEnrollment student) throws Exception;
	public List<StudentEnrollment> getEmployeesByDatetoSendAdmin() throws Exception;
	 
}
