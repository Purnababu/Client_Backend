package com.example.Client_Lms.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Repoisitory.EmployeeRepoisitory;



@Service
public class JwtServiceImpl implements UserDetailsService {

	@Autowired
	private EmployeeRepoisitory employeeRepoisitory;

	@Autowired
	@Lazy
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtService jwtService;

	public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
		String email = jwtRequest.getEmployeeId();
		String password = jwtRequest.getPassword();
		authenticate(email, password);

		loadUserByUsername(email);
		Employee employee = employeeRepoisitory.findByEmployeeId(email);
		String generatedToken = jwtService.generateToken(employee.getEmployeeId());
		String employeeId = employee.getEmployeeId();
		return new JwtResponse(employee, generatedToken, employeeId);
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Employee user = employeeRepoisitory.findByEmployeeId(email);
		if (user == null) {
			System.out.println("User Not Found");
			throw new UsernameNotFoundException("user not found");
		}

		return user;
	}

	private void authenticate(String email, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}

}
