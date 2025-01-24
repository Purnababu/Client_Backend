package com.example.Client_Lms.Service;

import java.util.List;

import com.example.Client_Lms.Entity.Employee;
import com.example.Client_Lms.Entity.Transaction;

public interface TransactionService {

	// Method to save a transaction
	Transaction saveTransaction(Transaction transaction);

	public List<Transaction> getTrasactionByDate() throws Exception;
}
