package com.cg.ewallet.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;


import com.cg.ewallet.dao.TransactionRepository;

import com.cg.ewallet.entity.Transaction;


@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
	
	@Autowired
	private TransactionService transService;
	
	
	@MockBean
	private TransactionRepository transDao;
	

	@Test
	public void getAllTransactionTest() {
		List<Transaction> list=new ArrayList<Transaction>();
		Transaction one=new Transaction(1L,8288053865L,1000,50000,0,8288053865L,8955194111L);
		Transaction two=new Transaction(2L,8955194111L,0,50000,1000,8288053865L,8955194111L);
		Transaction three=new Transaction(3L,9782410575L,0,50000,1000,9982828585L,9782410575L);
		Transaction four=new Transaction(4L,9782410575L,1000,50000,0,9782410575L,9982828585L);

		list.add(one);
		list.add(two);
		list.add(three);
		list.add(four);
		 
		 when(transDao.findAll()).thenReturn(list);
		 
		 assertEquals(4, transService.getAllTransaction().size());
	
		}
	

	

	 

}
