package com.cg.ewallet.dto;




public class AccountDto{
	
	
	private long phnNumber;
	private long accNo;
	private float balance;
	
	
	
	//Getter and Setter Methods
	
	
	public long getAccNo() {
		return accNo;
	}
	public void setAccNo(long accNo) {
		this.accNo = accNo;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	
	
	public long getPhnNumber() {
		return phnNumber;
	}
	public void setPhnNumber(long phnNumber) {
		this.phnNumber = phnNumber;
	}
	//Constructor
	public AccountDto() {
		super();
	}
	
	
	
	public AccountDto(long phnNumber, long accNo, float balance) {
		super();
		
		this.accNo = accNo;
		this.balance = balance;
		this.phnNumber = phnNumber;
	}
	
	
	//toString 
	@Override
	public String toString() {
		return "Account [phnNumber=" + phnNumber + ", accNo=" + accNo + ", balance=" + balance +  "]";
	}
	
	
	

}
