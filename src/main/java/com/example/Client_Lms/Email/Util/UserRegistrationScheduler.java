package com.example.Client_Lms.Email.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Client_Lms.ServiceImpl.EmployeeImpl;
import com.example.Client_Lms.ServiceImpl.TransactionServiceImpl;

@Component
public class UserRegistrationScheduler {

	@Autowired
	private EmployeeImpl employeeImpl;

	@Autowired
	private TransactionServiceImpl trp;

//    @Scheduled(cron = "0 02 14 * * *")
// 2 PM

//	@Scheduled(cron = "0 56 17 * * *")
//	public void scheduleRegisteredUsersEmail() throws Exception {
//		employeeImpl.getEmployeesByDate();
//	}

//    - 57: Seconds (57)
//    - 13: Hours (1 PM, 24-hour format)
//    - *: Minutes (any)
//    - *: Day of month (any)
//    - *: Month (any)
//    - *: Day of week (any)

	@Scheduled(cron = "0 41 10 * * *")
	public void updateLoginstatuses() throws Exception {
		employeeImpl.updateLoginStatusForAllEmployees();
	}

//	--------------------------------------------------------------------------------------------
//     working id manadi
	//idi maan main di deeni chek cheyi
//	Explanation of cron expression:
//		0 → Executes at second 0
//		0 → Executes at minute 0
//		15,18,21 → Runs at 3 PM (15:00), 6 PM (18:00), and 9 PM (21:00)
//	@Scheduled(cron = "0 0 15,18,21 * * *")


//	@Scheduled(cron = "0 41 23 * * *")
//	public void scheduleRegisteredStudentsTOadmin() throws Exception {
//		employeeImpl.getEmployeesByDatetoSendAdmin();
//	}

//	@Scheduled(cron = "0 41 23 * * *")
//	public void scduleDailtTransactionTOAdmin() throws Exception {
//		trp.getTrasactionByDate();
//	}
	
	
//	-----------------------------------------------------------------------------------------------


	
	
//	--------------enrollmenmailss--------------
	
//                     s   m  h
//	@Scheduled(cron = "0 0 15 * * *") // 3:00 PM
	@Scheduled(cron = "0 35 15 * * *", zone = "Asia/Kolkata") // 3:00 PM IST
	public void scheduleAt3PM() throws Exception {
	    employeeImpl.getEmployeesByDatetoSendAdmin();
	}

	@Scheduled(cron = "0 0 18 * * *", zone = "Asia/Kolkata") // 6:00 PM IST
	public void scheduleAt6PM() throws Exception {
	    employeeImpl.getEmployeesByDatetoSendAdmin();
	}

	@Scheduled(cron = "0 0 21 * * *", zone = "Asia/Kolkata") // 9:00 PM IST
	public void scheduleAt9PM() throws Exception {
	    employeeImpl.getEmployeesByDatetoSendAdmin();
	}

	
	
	
	
//	----------------paymetmails-------------------------
	
//                   s   m  h
	@Scheduled(cron = "0 35 15 * * *", zone = "Asia/Kolkata") // 3:00 PM IST
	public void scduleDailtTransactionTOAdminAt3PM() throws Exception {
	    trp.getTrasactionByDate();
	}

	@Scheduled(cron = "0 0 18 * * *", zone = "Asia/Kolkata") // 6:00 PM IST
	public void scduleDailtTransactionTOAdminAt6PM() throws Exception {
	    trp.getTrasactionByDate();
	}

	@Scheduled(cron = "0 0 21 * * *", zone = "Asia/Kolkata") // 9:00 PM IST
	public void scduleDailtTransactionTOAdminAt9PM() throws Exception {
	    trp.getTrasactionByDate();
	}
	
	
	
	
	

}
