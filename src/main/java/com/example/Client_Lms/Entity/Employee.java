package com.example.Client_Lms.Entity;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Employee implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	private String employeeId;
	private String password;
	private String fullName;
	private String enrollmentNumber;
	private LocalDate dateOfEnrollment;
	private String course;
	private int courseAmount;
	private Integer duration;
	private String feeOption;
	private LocalDate deadlineDate;
	private String status;
	private String mobileNumber;
	private String address;
	private String batchTiming;
	private LocalDate localDate;
	
	 // New fields
    private String paymentMethod; // e.g., "cash" or "phonepay"
    private Double cashAmount;
    private String transactionId;
    private int transactionAmount;
    private int remainingAmount;
    private String paymentScreenshotPath;
    private String loginStatus;
    
	private byte[] imageBytes;
    
    private String imagePath;
    
    
    // This field will not be stored in the database
    @Transient
    private MultipartFile paymentScreenshot;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "Employee_Roles", joinColumns = @JoinColumn(name = "employee_id"), // Ensure this matches
																							// 'employeeId' in Employee
																							// entity
			inverseJoinColumns = @JoinColumn(name = "role_name") // Ensure this matches 'roleName' in Role entity
	)
	private Set<Role> roles = new HashSet<>();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> authorities = new HashSet<>();
		this.roles.forEach(userRole -> {
			authorities.add(new Authority("ROLE_" + userRole.getRoleName()));
		});
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.employeeId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
