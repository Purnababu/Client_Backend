package com.example.Client_Lms.ServiceImpl;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Client_Lms.Email.Util.EmailUtil;
import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.Transaction;
import com.example.Client_Lms.Repoisitory.TransactionRepository;
import com.example.Client_Lms.Service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;
	@Autowired
	private EmailUtil emailUtil;

	@Override
	public Transaction saveTransaction(Transaction transaction) {
		return transactionRepository.save(transaction);
	}

	@Override
	public List<Transaction> getTrasactionByDate() throws Exception {
		List<Transaction> trasacttt = transactionRepository.findByTransactionDate(LocalDate.now());
		emailUtil.sendTrasactionEmailDailyTOAdmin(trasacttt);
		return trasacttt;
	}

	

//	---------------------------------------------------------------------------------------------------
	
	@Override
	public double getTotalAmount() throws Exception {
	    LocalDate today = LocalDate.now();

	    // Execute logic only on the 20th of each month //a day test chestha mo ad day ivvali 
	    if (today.getDayOfMonth() == 12) {
	        YearMonth month = YearMonth.from(today);
	        LocalDate startOfMonth = month.atDay(1);
	        LocalDate endOfMonth = month.atEndOfMonth();

	        // Fetch transactions for the entire month
	        List<Transaction> transactions = transactionRepository.findByTransactionDateBetween(startOfMonth, endOfMonth);
	        
	        // Calculate total amount
	        double totalAmount = transactions.stream().mapToDouble(Transaction::getAmount).sum();

	        // Send email with transactions & total amount
	        emailUtil.sendTrasactionEmailForMonthTOAdmin(transactions, totalAmount);

	        return totalAmount;
	    }

	    return 0.0; // If today is not the 20th, return 0 or handle accordingly
	}

	
	 
	
}
