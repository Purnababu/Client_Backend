package com.example.Client_Lms.ServiceImpl;

import java.time.LocalDate;
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
}
