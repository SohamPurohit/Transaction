package com.cg.ewallet.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="transaction")
public class Transaction {
	
	@Id
	@Column(name="transactionid")
	private long transactionId;
	@Column(name="phnnumber")
	private long phnNumber;
	@Column(name="amttransfer")
	private float amtTransfer;
	@Column(name="balance")
	private float balance;
	@Column(name="dateoftransfer")
	private LocalDate dateoftransfer;
	@Column(name="amtreceived")
	private float amtReceived;
	@Column(name="senderphnnumber")
	private long senderPhnNumber;
	@Column(name="receiverphnnumber")
	private long receiverPhnNumber;
	
	
	//Constructor
	public Transaction() {
		super();
	}
	
	
	public Transaction(long transactionId, long phnNumber, float amtTransfer, float balance, 
			float amtReceived,  long senderPhnNumber,
			long receiverPhnNumber) {
		super();
		this.transactionId = transactionId;
		this.phnNumber = phnNumber;
		this.amtTransfer = amtTransfer;
		this.balance = balance;
		this.amtReceived = amtReceived;
		this.senderPhnNumber = senderPhnNumber;
		this.receiverPhnNumber = receiverPhnNumber;
	}


	//getter and setter methods


	public long getTransactionId() {
		return transactionId;
	}


	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}


	public long getPhnNumber() {
		return phnNumber;
	}


	public void setPhnNumber(long phnNumber) {
		this.phnNumber = phnNumber;
	}


	public float getAmtTransfer() {
		return amtTransfer;
	}


	public void setAmtTransfer(float amtTransfer) {
		this.amtTransfer = amtTransfer;
	}


	public float getAmtReceived() {
		return amtReceived;
	}


	public void setAmtReceived(float amtReceived) {
		this.amtReceived = amtReceived;
	}


	public LocalDate getDateoftransfer() {
		return dateoftransfer;
	}


	public void setDateoftransfer(LocalDate dateoftransfer) {
		this.dateoftransfer = dateoftransfer;
	}


	


	public long getSenderPhnNumber() {
		return senderPhnNumber;
	}


	public void setSenderPhnNumber(long senderPhnNumber) {
		this.senderPhnNumber = senderPhnNumber;
	}


	public long getReceiverPhnNumber() {
		return receiverPhnNumber;
	}


	public void setReceiverPhnNumber(long receiverPhnNumber) {
		this.receiverPhnNumber = receiverPhnNumber;
	}
	public float getBalance() {
		return balance;
	}


	public void setBalance(float balance) {
		this.balance = balance;
	}


	

	//toString
	@Override
	public String toString() {
		return "Transaction [transactionId=" + transactionId + ", phnNumber=" + phnNumber + ", amtTransfer=" + amtTransfer
				+ ", balance=" + balance + ", dateoftransfer=" + dateoftransfer + ", amtReceived=" + amtReceived
				+  ", senderPhnNumber=" + senderPhnNumber + ", receiverPhnNumber=" + receiverPhnNumber + "]";
	}

	
	
	
	
	
	
	

}
