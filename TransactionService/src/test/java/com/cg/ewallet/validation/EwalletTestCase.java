package com.cg.ewallet.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EwalletTestCase {
		
	@Autowired
		Validation validation;

		@Test
		public void testIsValidIncomeForFloat() 
		{
			boolean flag=validation.isFloat("884.88");
			assertTrue(flag);	
		}
		@Test
		public void testIsValidIncomeForString() 
		{
			boolean flag=validation.isFloat("mikku");
			assertFalse(flag);	
		}
		@Test
		public void testIsValidIncomeForCombinationStartWithAlphabet() 
		{
			boolean flag=validation.isFloat("abc123.98");
			assertFalse(flag);	
		}
		@Test
		public void testIsValidIncomeForCombinationStartsWithInt() 
		{
			boolean flag=validation.isFloat("1808manu");
			assertFalse(flag);	
		}
		
		@Test
		public void testIsValidPassWord() 
		{
			boolean flag=validation.checkPassword("Soham@1222");
			assertTrue(flag);	
		}
		@Test
		public void testIsValidPassWordContainsOnlyDigits() 
		{
			boolean flag=validation.checkPassword("825365");
			assertFalse(flag);	
		}
		@Test
		public void testIsValidPassWordContainsOnlyAlphabets() 
		{
			boolean flag=validation.checkPassword("gudia");
			assertFalse(flag);	
		}
		@Test
		public void testIsValidPassWordContainsOnlyAlphabetsAndCharacters() 
		{
			boolean flag=validation.checkPassword("smspyh123");
			assertFalse(flag);	
		}
		@Test
		public void testIsContainsOnlyDigitForRequestId()
		{
			boolean flag=validation.isDigit("123456");
			assertTrue(flag);
		}
		@Test
		public void testIsContainsOnlyCharcterForRequestId()
		{
			boolean flag=validation.isDigit("jungli");
			assertFalse(flag);
		}


	}

