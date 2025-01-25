package com.example.Client_Lms.Email.Util;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.StudentEnrollment;
import com.example.Client_Lms.Entity.Transaction;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtil {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMail(String employeeId, String fullName, String enrollmentNumber, LocalDate dateOfEnrollment,
			String course, String feeOption, LocalDate deadlineDate,
			String mobileNumber, String address, String batchTiming, String employeeEmail) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mn = new MimeMessageHelper(mimeMessage);
		mn.setFrom("purnapuri14@gmail.com", "LMS");
		mn.setTo(employeeEmail);
		mn.setSubject("Enrollment Details");

		String body = String.format(
				"<html>" + "<body>" + "<h1>Welcome to the Course</h1>" + "<p>Below are your enrollment details:</p>"
						+ "<p><strong>Employee ID: %s</strong></p>" + "<p><strong>Full Name: %s</strong></p>"
						+ "<p><strong>Enrollment Number: %s</strong></p>"
						+ "<p><strong>Date of Enrollment: %s</strong></p>" + "<p><strong>Course: %s</strong></p>"
						+ "<p><strong>Fee Option: %s</strong></p>"
						+ "<p><strong>Fee Deadline Date: %s</strong></p>"
						+ "<p><strong>Mobile Number: %s</strong></p>" + "<p><strong>Address: %s</strong></p>"
						+ "<p><strong>Batch Timing: %s</strong></p>"
						+ "<p>Please keep this information secure and do not share it with anyone.</p>"
						+ "<p>Best regards,</p>" + "<p>The LMS Team</p>" + "</body>" + "</html>",
				employeeId, fullName, enrollmentNumber, dateOfEnrollment, course, feeOption,
				deadlineDate, mobileNumber, address, batchTiming);

		mn.setText(body, true);
		javaMailSender.send(mimeMessage);
	}

	public void sendAdminEmail(List<Employee> employees) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mn = new MimeMessageHelper(mimeMessage);

//	        mn.setFrom("purnapuri14@gmail.com", "Admin");
		mn.setFrom("purnapuri14@gmail.com", "Admin");
//	        mn.setTo("sricharan824@gmail.com");
//		mn.setTo("prashanthkulkarani066@gmail.com");
		mn.setSubject("Daily Employee Enrollment Report - " + LocalDate.now());

		String body = createEmailBody(employees);
		mn.setText(body, true);

		javaMailSender.send(mimeMessage);
	}

	
	private String createEmailBody(List<Employee> employees) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>").append("<body>").append("<h1>Daily Employee Enrollment Report</h1>")
				.append("<table border='1'>").append("<tr>").append("<th>Name</th>").append("<th>Employee Id</th>")
				.append("<th>Course</th>").append("</tr>");

		for (Employee employee : employees) {
			sb.append("<tr>").append("<td>").append(employee.getFullName()).append("</td>").append("<td>")
					.append(employee.getEmployeeId()).append("</td>").append("<td>").append(employee.getCourse())
					.append("</td>").append("</tr>");
		}

		sb.append("</table>").append("</body>").append("</html>");

		return sb.toString();
	}
	
	

	// Method to send email with enrollment details
	public String sendMailToDeletedEmployees(String employeeId, String fullName, String enrollmentNumber,
			LocalDate dateOfEnrollment, String course, Integer duration, String feeOption, Double feeAdvance,
			LocalDate deadlineDate, String mobileNumber, String address, String batchTiming) {

		try {
			// Create a MimeMessage
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mn = new MimeMessageHelper(mimeMessage, true);

			// Set email details
			mn.setFrom("purnapuri14@gmail.com", "LMS");
			mn.setTo(employeeId);
			mn.setSubject("Enrollment Cancellation Notification");

			// Create email body
			String body = String.format("<html>" + "<body>" + "<h2>Enrollment Cancellation Notice</h2>"
					+ "<p>Dear %s,</p>"
					+ "<p>We regret to inform you that your enrollment has been cancelled due to the deadline date of <strong>%s</strong> being reached.</p>"
					+ "<h3>Enrollment Details:</h3>" + "<ul>" + "<li><strong>Employee ID:</strong> %s</li>"
					+ "<li><strong>Enrollment Number:</strong> %s</li>" + "<li><strong>Course:</strong> %s</li>"
					+ "<li><strong>Duration:</strong> %d months</li>" + "<li><strong>Fee Option:</strong> %s</li>"
					+ "<li><strong>Fee Advance:</strong> %.2f</li>" + "<li><strong>Mobile Number:</strong> %s</li>"
					+ "<li><strong>Address:</strong> %s</li>" + "<li><strong>Batch Timing:</strong> %s</li>" + "</ul>"
					+ "<p>If you have any questions, please contact our support team.</p>"
					+ "<p>Thank you,<br/>LMS Team</p>" + "</body>" + "</html>", fullName, deadlineDate, employeeId,
					enrollmentNumber, course, duration, feeOption, feeAdvance, mobileNumber, address, batchTiming);

			// Set email content as HTML
			mn.setText(body, true);

			// Send the email
			javaMailSender.send(mimeMessage);

			// Return success message
			return "Email sent successfully to " + employeeId;

		} catch (Exception e) {
			// Log the exception (optional)
			e.printStackTrace();

			// Return failure message with error details
			return "Failed to send email to " + employeeId + ". Error: " + e.getMessage();
		}
	}
	
	
	
//	----------------------------------------------------------------------------------------------------------------
	
	public void sendStudentEnrollmentMail(StudentEnrollment studentEnrollment) throws Exception {
	    MimeMessage mimeMessage = javaMailSender.createMimeMessage();
	    MimeMessageHelper mn = new MimeMessageHelper(mimeMessage);
	    mn.setFrom("purnapuri14@gmail.com", "LMS");
	    mn.setTo(studentEnrollment.getEmailAddress());
	    mn.setSubject("Enrollment Details");

	    String body = String.format(
	            "<html>" + 
	            "<body>" + 
	            "<h1>Welcome to the Course</h1>" + 
	            "<p>Below are your enrollment details:</p>" +
	            "<p><strong>Full Name: %s</strong></p>" +
	            "<p><strong>Date of Birth: %s</strong></p>" +
	            "<p><strong>Contact Number: %s</strong></p>" +
	            "<p><strong>Email Address: %s</strong></p>" +
	            "<p><strong>Course Name: %s</strong></p>" +
	            "<p><strong>Grade Batch: %s</strong></p>" +
	            "<p><strong>Enrollment Date: %s</strong></p>" +
	            "<p><strong>Admission Number: %s</strong></p>" +
	            "<p><strong>Total Fee: %.2f</strong></p>" +
	            "<p><strong>Amount Paid: %.2f</strong></p>" +
	            "<p><strong>Payment Date: %s</strong></p>" +
	            "<p><strong>Payment Mode: %s</strong></p>" +
	            "<p><strong>Balance Amount: %.2f</strong></p>" +
	            "<p><strong>Fee Status: %s</strong></p>" +
	            "<p>Please keep this information secure and do not share it with anyone.</p>" +
	            "<p>Best regards,</p>" + 
	            "<p>The LMS Team</p>" + 
	            "</body>" + 
	            "</html>",
	            studentEnrollment.getFullName(), studentEnrollment.getDateOfBirth(), studentEnrollment.getContactNumber(),
	            studentEnrollment.getEmailAddress(), studentEnrollment.getCourseName(), studentEnrollment.getGradeBatch(),
	            studentEnrollment.getEnrollmentDate(), studentEnrollment.getAdmissionNumber(),
	            studentEnrollment.getTotalFee(), studentEnrollment.getAmountPaid(), studentEnrollment.getPaymentDate(),
	            studentEnrollment.getPaymentMode(), studentEnrollment.getBalanceAmount(), studentEnrollment.getFeeStatus()
	    );

	    mn.setText(body, true);
	    javaMailSender.send(mimeMessage);
	}


	
	
	public void sendAdminEmailOfStudents(List<StudentEnrollment> employees) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mn = new MimeMessageHelper(mimeMessage);

//	        mn.setFrom("purnapuri14@gmail.com", "Admin");
		mn.setFrom("purnapuri14@gmail.com", "Admin");
//	        mn.setTo("sricharan824@gmail.com");
//		mn.setTo("prashanthkulkarani066@gmail.com");
//		mn.setTo("anushamantripragada41@gmail.com");
		mn.setTo("Jai.aluri555@gmail.com");
		mn.setSubject("Daily Students Enrollment Report - " + LocalDate.now());

		String body = createEmailBodyyy(employees);
		mn.setText(body, true);

		javaMailSender.send(mimeMessage);
	}

	private String createEmailBodyyy(List<StudentEnrollment> employees) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>").append("<body>").append("<h1>Daily Students Enrollment Report</h1>")
				.append("<table border='1'>").append("<tr>").append("<th>Name</th>").append("<th>Employee Id</th>")
				.append("<th>Course</th>").append("</tr>");

		for (StudentEnrollment employee : employees) {
			sb.append("<tr>").append("<td>").append(employee.getFullName()).append("</td>").append("<td>")
					.append(employee.getEmailAddress()).append("</td>").append("<td>").append(employee.getCourseName())
					.append("</td>").append("</tr>");
		}

		sb.append("</table>").append("</body>").append("</html>");

		return sb.toString();
	}
	
	
	

	
	public void sendTrasactionEmailDailyTOAdmin(List<Transaction> employees) throws Exception {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mn = new MimeMessageHelper(mimeMessage);

//	        mn.setFrom("purnapuri14@gmail.com", "Admin");
		mn.setFrom("purnapuri14@gmail.com", "Admin");
//	        mn.setTo("sricharan824@gmail.com");
//		mn.setTo("prashanthkulkarani066@gmail.com");
		mn.setTo("Jai.aluri555@gmail.com");
		mn.setSubject("Daily Payment Report - " + LocalDate.now());

		String body = createEmailBodyhuy(employees);
		mn.setText(body, true);

		javaMailSender.send(mimeMessage);
	}

	
	private String createEmailBodyhuy(List<Transaction> employees) {
		StringBuilder sb = new StringBuilder();
		sb.append("<html>").append("<body>").append("<h1>Daily Payment Report</h1>")
				.append("<table border='1'>").append("<tr>").append("<th>Mode</th>").append("<th>Date</th>")
				.append("<th>Discription</th>").append("<th>Amount</th>").append("</tr>");

		for (Transaction employee : employees) {
			sb.append("<tr>").append("<td>").append(employee.getPaymentMode()).append("</td>").append("<td>")
					.append(employee.getTransactionDate()).append("</td>").append("<td>").append(employee.getDescription()).append("</td>").append("<td>").append(employee.getAmount())
					.append("</td>").append("</tr>");
		}

		sb.append("</table>").append("</body>").append("</html>");

		return sb.toString();
	}
	


	
	
	
	
}
