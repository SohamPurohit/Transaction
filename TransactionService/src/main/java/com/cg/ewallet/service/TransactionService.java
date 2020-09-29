package com.cg.ewallet.service;

import java.util.List;

import com.cg.ewallet.dto.TransactionDto;
import com.cg.ewallet.entity.Account;
import com.cg.ewallet.entity.Transaction;
import com.cg.ewallet.exception.UserNotFoundException;


public interface TransactionService {
	
	public List<Transaction> getAllTransaction();
	
	public Transaction getTransactionByPhnNumber(long phnNumber) throws UserNotFoundException;
	
	public String transferAmt(long phnNumber, long receiverPhnNumber, float amount) throws UserNotFoundException;
	
	
	
	
	public String getCustomerBalance(long phnNumber) throws UserNotFoundException;
	
	public Account newUser(Account account);
	

}
