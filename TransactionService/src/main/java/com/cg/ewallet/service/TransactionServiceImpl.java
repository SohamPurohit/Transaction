package com.cg.ewallet.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.cg.ewallet.dao.AccountRepository;
import com.cg.ewallet.dao.TransactionRepository;
import com.cg.ewallet.entity.Account;
import com.cg.ewallet.entity.Transaction;
import com.cg.ewallet.exception.UserNotFoundException;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired(required=true)
	TransactionRepository transDao;

	@Autowired(required=true)
	AccountRepository accDao;
	
	@Autowired
	RestTemplate restTemplate;


	//to get the the list of all transactions done by users
	@Override
	public List<Transaction> getAllTransaction() {

		return transDao.findAll();
	}


	
	//finding the transaction details of user by the registered mobile number 
	@Override 
	public Transaction getTransactionByPhnNumber(long phnNumber) throws UserNotFoundException
	{

		Transaction transaction=null;
		List<Transaction> allTranstList = transDao.findAll();

		for(Transaction transDto:allTranstList) {
			if(transDto.getPhnNumber()== phnNumber) {
				transaction = transDto; 
				break;
			}}
		if(transaction==null) {
			throw new UserNotFoundException("User Not Found");
		}

		return transaction;  
	}


	
	//transfer money from one E-wallet to another 
	//handled the cases such as: same user can"t to his/her account
	//both the users have the E-wallet account on the application
	
	@Override
	public String transferAmt(long phnNumber, long receiverPhnNumber, float amount) throws UserNotFoundException{

		List<Account> allAccountList = accDao.findAll();
		StringBuilder bld = new StringBuilder();
		String transferResult="";
		int flag =0;
		boolean accountExist=false;
		boolean sameAccount=false;
		for(Account accDto:allAccountList)
		{
			if(accDto.getPhnNumber()== phnNumber) {
				flag=1;
			}
			if(flag==0) {
				throw new UserNotFoundException("Sender Not Found");
			}
			if(accDto.getPhnNumber() == phnNumber && accDto.getPhnNumber()==receiverPhnNumber)
			{
				sameAccount=true;
			}
		}
		for(Account accDto:allAccountList)
		{
			if(accDto.getPhnNumber() == receiverPhnNumber)
			{
				accountExist=true;
			}
		}
		if(sameAccount)
		{
			bld.append("Oops can't Transfer as sender and receiver Number is same ");
			transferResult = bld.toString();
		}
		else
		{
			if(accountExist)
			{

				long transactionId=(long)(Math.floor(Math.random()*100000)+1);
				for(Account accDto:allAccountList)
				{
					if(accDto.getPhnNumber()==phnNumber)
					{
						if(accDto.getBalance()>=amount) {

							//amount debited from the sender's E-wallet

							Transaction tranDto=new Transaction();
							float amountLeft=(accDto.getBalance()-amount);
							accDto.setBalance(amountLeft);
							tranDto.setAmtTransfer(amount);
							tranDto.setBalance(amountLeft);
							tranDto.setPhnNumber(accDto.getPhnNumber());
							tranDto.setDateoftransfer(LocalDate.now());					
							tranDto.setTransactionId(transactionId);

							accDao.saveAndFlush(accDto);
							transDao.saveAndFlush(tranDto);

							bld.append(" Amount "+amount+" 	Transfered to "+receiverPhnNumber);
							transferResult = bld.toString();

							for(Account accDto1:allAccountList)
							{
							//amount credited to the receiver's E-wallet
								if(accDto1.getPhnNumber()==receiverPhnNumber)
								{
									Transaction tranDto1=new Transaction();
									float totalamount=(accDto1.getBalance()+amount);
									accDto1.setBalance(totalamount);
									tranDto1.setAmtReceived(amount);
									tranDto1.setBalance(totalamount);
									tranDto1.setPhnNumber(accDto1.getPhnNumber());								
									tranDto1.setTransactionId((transactionId+1));	
									tranDto1.setDateoftransfer(LocalDate.now());									
									accDao.saveAndFlush(accDto1);
									transDao.saveAndFlush(tranDto1);							
								}
							}
						}
						else
						{
							bld.append("Oops can't Transfer as amount is not sufficient ");
							transferResult = bld.toString();
						}
					}
				}
			}
			else 
			{
				throw new UserNotFoundException("Receiver Mobile Number  Not Found");
			}
		}
		return transferResult;		
	}

	
	
	
	//to check the balance of E-wallet by registered mobile number
	@Override
	public String getCustomerBalance(long phnNumber)  throws UserNotFoundException{
		String balance="";
		int flag=0;

		List<Transaction> allTranstList = transDao.findAll();

		for(Transaction transDto:allTranstList) {
			if(transDto.getPhnNumber()== phnNumber) {
				flag=1;
				balance = "Balane Of phn Number is : "+transDto.getBalance();
			}}
		if(flag==0){
			throw new UserNotFoundException("User  Not Found");
		}

		return balance; 

	}



	

	

	@Override
	public Account newUser(Account account) {
		
		return accDao.saveAndFlush(account);
	}




}
