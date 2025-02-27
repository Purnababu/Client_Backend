package com.example.Client_Lms.Repoisitory;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.Client_Lms.Entity.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

//	List<Transaction> findByLocalDate(LocalDate now);
	
	List<Transaction> findByTransactionDate(LocalDate transactionDate);
	
	
	
//	----------------------------------------------------------------------------------------------------
	
	
	 List<Transaction> findByTransactionDateBetween(LocalDate startDate, LocalDate endDate);


	
	

   
}
