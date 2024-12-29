package com.example.Client_Lms.ServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path; // For file operations
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.Client_Lms.Email.Util.EmailUtil;
import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.Role;
import com.example.Client_Lms.Repoisitory.EmployeeRepoisitory;
import com.example.Client_Lms.Repoisitory.RoleRepoisitory;
import com.example.Client_Lms.Service.EmployeeService;

import jakarta.transaction.Transactional;

@Service
public class EmployeeImpl implements EmployeeService {

	@Autowired
	private RoleRepoisitory roleRepoisitory;

	@Autowired
	private EmployeeRepoisitory employeeRepoisitory;

	@Autowired
	private EmailUtil emailUtil;

	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	@Override
	public String initRoleAndAdmin() {
		Role adminRole = new Role();
		adminRole.setRoleName("Admin");
		roleRepoisitory.save(adminRole);

		Role staffRole = new Role();
		staffRole.setRoleName("Staff");
		roleRepoisitory.save(staffRole);

		Role employeeRole = new Role();
		employeeRole.setRoleName("Employee");
		roleRepoisitory.save(employeeRole);

		return "Success";
	}

	@Override
	public Employee addAdmin(Employee admin) {

		Role role = roleRepoisitory.findById("Admin").get();
		Set<Role> adminRole = new HashSet<>();
		adminRole.add(role);
		admin.setRoles(adminRole);
		String encodedPassword = encoder.encode(admin.getPassword());
		admin.setPassword(encodedPassword);
		return employeeRepoisitory.save(admin);
	}

	@Override
	public Employee addEmployee(Employee employee, String roleName) throws Exception {
		Role role = roleRepoisitory.findById(roleName).get();
		Set<Role> employeeRole = new HashSet<>();
		employeeRole.add(role);
		employee.setRoles(employeeRole);

		// Store the original password before encoding
		String originalPassword = employee.getPassword();

		// Encode the password and set it to the employee object
		String encodedPassword = encoder.encode(originalPassword);
		employee.setPassword(encodedPassword);
		if ((employee.getCashAmount() == null) && (employee.getTransactionAmount() == 0)) {
			employee.setStatus("Pending");
		} else if (employee.getRemainingAmount() != 0) {
			employee.setStatus("Advance Paid");
		} else {
			employee.setStatus("Total Paid");
		}

		employee.setLoginStatus("Active");
		employee.setLocalDate(LocalDate.now());

		emailUtil.sendMail(employee.getEmployeeId(), employee.getFullName(), employee.getEnrollmentNumber(),
				employee.getDateOfEnrollment(), employee.getCourse(), employee.getFeeOption(),
				employee.getDeadlineDate(), employee.getMobileNumber(), employee.getAddress(),
				employee.getBatchTiming(), employee.getEmployeeId());

		return employeeRepoisitory.save(employee);
	}

	@Override
	public List<Employee> getEmployeesByDate() throws Exception {

		List<Employee> employees = employeeRepoisitory.findByLocalDate(LocalDate.now());
		emailUtil.sendAdminEmail(employees);
		return employees;
	}

	@Override
	@Transactional
	public void updateUserImagePathAndStoreInDatabase(String employeeId, MultipartFile file) throws IOException {

		// Check if the file is empty
		if (file.isEmpty()) {
			throw new IllegalArgumentException("File is empty");
		}

		// Base directory for saving images
		String baseDir = "D:\\Client Project\\Transaction ss";

		// Create a subdirectory (optional, if grouping files is required)
		Path directoryPath = Paths.get(baseDir);
		Files.createDirectories(directoryPath);

		// Use employee ID as the filename with the original file extension
		String fileExtension = getFileExtension(file.getOriginalFilename());
		String fileName = employeeId + fileExtension;

		// Full path to save the file
		String filePath = Paths.get(directoryPath.toString(), fileName).toString();

		// Save the file to the directory
		Files.copy(file.getInputStream(), Paths.get(filePath), StandardCopyOption.REPLACE_EXISTING);

		// Store the image path in the database using employeeId
		Employee user = employeeRepoisitory.findByEmployeeId(employeeId);
		if (user != null) {
			user.setImagePath(filePath); // Assuming Employee has an `imagePath` field
			employeeRepoisitory.save(user);
		} else {
			throw new IllegalArgumentException("User with employee ID " + employeeId + " does not exist.");
		}
	}

	/**
	 * Helper method to extract the file extension from a filename.
	 */
	private String getFileExtension(String fileName) {
		int index = fileName.lastIndexOf('.');
		if (index > 0) {
			return fileName.substring(index);
		}
		return ""; // Default to no extension if not found
	}

	@Override
	public byte[] getPhotoBytesByEmployeeId(String employeeId) throws IOException {
		// Fetch the user entity by employee ID
		Employee user = employeeRepoisitory.findByEmployeeId(employeeId);
		if (user == null) {
			throw new IllegalArgumentException("User with employee ID " + employeeId + " does not exist.");
		}

		// Get the image path from the user object
		String imagePath = user.getImagePath();
		if (imagePath == null || imagePath.isEmpty()) {
			throw new IllegalArgumentException("User with employee ID " + employeeId + " does not have a photo.");
		}

		// Read the photo bytes from the file
		Path photoPath = Paths.get(imagePath);
		return Files.readAllBytes(photoPath);
	}

	@Override
	public List<Employee> getEmployeesWithExpiredDeadlines() {
		LocalDate currentDate = LocalDate.now(); // Get the current local date
		return employeeRepoisitory.findEmployeesWithExpiredDeadlines(currentDate);
	}

	@Override
	public int updateLoginStatusForAllEmployees() {
		LocalDate currentDate = LocalDate.now(); // Get the current date

		// Fetch all employees
		List<Employee> allEmployees = employeeRepoisitory.findAll();

		// Update loginStatus based on deadline comparison
		allEmployees.forEach(employee -> {
			if (employee.getDeadlineDate() != null && employee.getDeadlineDate().isBefore(currentDate)) {
				employee.setLoginStatus("Deactive");
			} else {
				employee.setLoginStatus("Active");
			}
		});

		// Save all updated employees back to the database
		employeeRepoisitory.saveAll(allEmployees);

		// Return the number of employees updated
		return allEmployees.size();
	}

}
