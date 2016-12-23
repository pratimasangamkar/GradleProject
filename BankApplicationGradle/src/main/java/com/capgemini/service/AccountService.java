package com.capgemini.service;


import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;

public interface AccountService {
	
	Account createAccount(int accountNumber, int amount)throws InsufficientInitialBalanceException;
	int showBalance(int accountNumber) throws InvalidAccountNumberException;
	int withDrawAmount(int accountNumber,int withdrawAmount) throws InsufficientBalanceException, InvalidAccountNumberException;
	int depositAmount(int accountNumber,int depositAmount) throws InvalidAccountNumberException, InsufficientBalanceException;
	String fundTransfer(int sourceAccountNumber,int destinationAccountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException;
	
}
