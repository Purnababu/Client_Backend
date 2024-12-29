package com.example.Client_Lms.Controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Service.EmployeeService;

import jakarta.annotation.PostConstruct;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@PostConstruct
	public void initRoleAndAdmin() {
		employeeService.initRoleAndAdmin();
	}

	@PostMapping("/addAdmin")
	public ResponseEntity<Employee> addAdmin(@RequestBody Employee admin) {

		Employee addAdmin = employeeService.addAdmin(admin);
		return new ResponseEntity<Employee>(addAdmin, HttpStatus.OK);

	}

	@PreAuthorize("hasRole('Admin')")
	@PostMapping("/addEmployee/{roleName}")
	public ResponseEntity<Employee> addEmployee(@RequestBody Employee employee, @PathVariable String roleName)
			throws Exception {

		Employee addEmployee = employeeService.addEmployee(employee, roleName);
		return new ResponseEntity<Employee>(addEmployee, HttpStatus.OK);
	}

	@GetMapping("/getEmployeesByDate")
	@PreAuthorize("hasRole('Admin')")
	public ResponseEntity<List<Employee>> getEmployeesByDate() throws Exception {
		List<Employee> employeesByDate = employeeService.getEmployeesByDate();
		return new ResponseEntity<List<Employee>>(employeesByDate, HttpStatus.OK);
	}

	@PostMapping("/upload/{email}")
	public ResponseEntity<?> uploadImage(@PathVariable String email, @RequestParam("file") MultipartFile file) {
		try {
			employeeService.updateUserImagePathAndStoreInDatabase(email, file);
			return ResponseEntity.ok("Image uploaded successfully");
		} catch (IOException e) {
			e.printStackTrace();
			return ResponseEntity.badRequest().body("Failed to upload image");
		}
	}

	@PreAuthorize("hasRole('Admin')")
	@GetMapping("/getEmployeesWithExpiredDeadlines")
	public ResponseEntity<List<Employee>> getEmployeesWithExpiredDeadlines() {
		try {
			List<Employee> employees = employeeService.getEmployeesWithExpiredDeadlines();
			return new ResponseEntity<>(employees, HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PreAuthorize("hasRole('Admin')")
	@PutMapping("/updateLoginStatusForAllEmployees")
	public ResponseEntity<String> updateLoginStatusForAllEmployees() {
		try {
			int updatedCount = employeeService.updateLoginStatusForAllEmployees();
			return new ResponseEntity<>(updatedCount + " employees' loginStatus updated.", HttpStatus.OK);
		} catch (Exception ex) {
			return new ResponseEntity<>("Failed to update employees' loginStatus.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
