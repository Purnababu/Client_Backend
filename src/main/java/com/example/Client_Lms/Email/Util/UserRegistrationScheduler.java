package com.example.Client_Lms.Email.Util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.example.Client_Lms.ServiceImpl.EmployeeImpl;

@Component
public class UserRegistrationScheduler {
    
    @Autowired
    private EmployeeImpl employeeImpl;
    
//    @Scheduled(cron = "0 02 14 * * *")
// 2 PM
    
    
    
    @Scheduled(cron = "0 56 17 * * *")
    public void scheduleRegisteredUsersEmail() throws Exception {
        employeeImpl.getEmployeesByDate();
    }
    
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
    
}


