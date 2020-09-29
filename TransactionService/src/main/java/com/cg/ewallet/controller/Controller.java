 package com.cg.ewallet.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.ewallet.entity.Account;
import com.cg.ewallet.entity.Transaction;
import com.cg.ewallet.exception.UserNotFoundException;
import com.cg.ewallet.service.TransactionService;


@RestController
@RequestMapping(value="/")
public class Controller {
	
	@Autowired(required=true)
	TransactionService transService;
	
	Logger log=LoggerFactory.getLogger(Controller.class);
	
	
	//the Default home page for the E-wallet application
	@GetMapping("/")
	public String home()
	{
		return  "Home Page For Transaction Service";
	}
	
	
	
	//This method return transaction detail of a specific customer 
	//If specific mobile number is not present then it throws a User define Exception
	@GetMapping("/view-customer-transaction/{phnNumber}")
	public ResponseEntity<Transaction> getTransactionByPhnNumber(@PathVariable long phnNumber)throws UserNotFoundException{
		
		Transaction transaction=null;
		
		try {
			transaction = transService.getTransactionByPhnNumber(phnNumber);
		}catch(UserNotFoundException e){
			 throw new UserNotFoundException("User with "+phnNumber+" Does not Exist");
		}
		
		log.info("Getting information of transaction specific cutomer by MobileNumber ");
		if(HttpStatus.BAD_REQUEST==null) {
			log.warn("Bad Request");
		}
		
		return new ResponseEntity(transaction,HttpStatus.OK);
	}
	
	
	
	
	//This method will return all the transactions done by the users
	// List of all the transaction fetched by this
	@GetMapping("/transaction-list")
	public ResponseEntity<List<Transaction>> getAllTransaction(){
		log.info("Transaction List");
		List<Transaction> tranList=transService.getAllTransaction();
		return new ResponseEntity(tranList,HttpStatus.OK);
	}
	
	
	
	
	
	//By this method user can transfer money from his wallet to the other one 
	//for transfer money required that receiver also the user of E-wallet Application
	@GetMapping("/transfer-money/{phnNumber}/{receiverPhnNumber}/{amount}")
	public String transferAmt(@PathVariable long phnNumber,@PathVariable long receiverPhnNumber,@PathVariable float amount) throws UserNotFoundException{
		log.info("Transfer money Customer function");
		return transService.transferAmt(phnNumber, receiverPhnNumber, amount);
	}
	
	
	
	
	//This method will return a string in which a specific user get his balance via Mobile number
	@GetMapping("/view-customer-balance/{phnNumber}")
	public String getCustomerBalance(@PathVariable long phnNumber) throws UserNotFoundException {
		log.info("View Balance OF Customer");
		return transService.getCustomerBalance(phnNumber);
		
	}
	
	
	@PostMapping(value="/newUser")
	public ResponseEntity<Account> newUser(@RequestBody Account account)
	{
		Account toBeReturned= transService.newUser(account);
		log.info("Keeping table in sync");
		if(HttpStatus.BAD_REQUEST==null)
		{
			log.warn("Bad request");	
		}
		return new ResponseEntity<>(toBeReturned,HttpStatus.OK);
	}
	
	
	
	

	
	

}
