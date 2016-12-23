package com.capgemini.service;

import java.util.StringJoiner;

import com.capgemini.exceptions.InsufficientBalanceException;
import com.capgemini.exceptions.InsufficientInitialBalanceException;
import com.capgemini.exceptions.InvalidAccountNumberException;
import com.capgemini.model.Account;
import com.capgemini.repository.AccountRepository;

public class AccountServiceImpl implements AccountService {

	AccountRepository accountRepository;
	int amount;
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		// TODO Auto-generated constructor stub
		this.accountRepository = accountRepository;
	}
	
	@Override
	public Account createAccount(int accountNumber, int amount) throws InsufficientInitialBalanceException {
		// TODO Auto-generated method stub
		if(amount < 500){
			throw new InsufficientInitialBalanceException("Balance Not Sufficient");
		}
		
		Account account = new Account();
		account.setAccountNumber(101);
		account.setAmount(5000);
		
		if(accountRepository.save(account)){
			return account;
		}
		
		return null;
	}

	@Override
	public int showBalance(int accountNumber) throws InvalidAccountNumberException {
		// TODO Auto-generated method stub
		
		if(accountRepository.searchAccount(accountNumber) != null && accountRepository.searchAccount(accountNumber).getAccountNumber() == accountNumber){
			return accountRepository.searchAccount(accountNumber).getAmount();
		}
		else{
			throw new InvalidAccountNumberException("Account Number is not Valid");
		}
	}

	@Override
	public int withDrawAmount(int accountNumber, int withdrawAmount) throws InsufficientBalanceException,InvalidAccountNumberException {
		// TODO Auto-generated method stub
		amount = 0;
		if(accountRepository.searchAccount(accountNumber) != null && accountRepository.searchAccount(accountNumber).getAccountNumber() == accountNumber){
			if(accountRepository.searchAccount(accountNumber).getAmount() < withdrawAmount){
				throw new InsufficientBalanceException("Larger Withdraw Amount");
			}
			
			else{
				accountRepository.searchAccount(accountNumber).setAmount(accountRepository.searchAccount(accountNumber).getAmount()-withdrawAmount);
				amount = accountRepository.searchAccount(accountNumber).getAmount();
				return amount; 
			}
		}
		else{
			throw new InvalidAccountNumberException("Account Number is not valid");
		}
	}
	

	@Override
	public int depositAmount(int accountNumber, int depositAmount) throws InvalidAccountNumberException,InsufficientBalanceException{
		// TODO Auto-generated method stub
		amount = 0;
		if(accountRepository.searchAccount(accountNumber) != null && (accountRepository.searchAccount(accountNumber).getAccountNumber() == accountNumber)){
			if(depositAmount > 0){
				accountRepository.searchAccount(accountNumber).setAmount(accountRepository.searchAccount(accountNumber).getAmount()+depositAmount);
				amount = accountRepository.searchAccount(accountNumber).getAmount();
				return amount;
			}
			else
				throw new InsufficientBalanceException("Balance not sufficient to deposit");
		}else{
			throw new InvalidAccountNumberException("Account Number is not valid");
		}
	}

	@Override
	public String fundTransfer(int sourceAccountNumber, int destinationAccountNumber, int amount) throws InsufficientBalanceException, InvalidAccountNumberException {
		// TODO Auto-generated method stub
		StringJoiner str = new StringJoiner(",");
		if((accountRepository.searchAccount(sourceAccountNumber) != null && accountRepository.searchAccount(destinationAccountNumber) != null) && 
				((accountRepository.searchAccount(sourceAccountNumber).getAccountNumber() == sourceAccountNumber) && (accountRepository.searchAccount(destinationAccountNumber).getAccountNumber() == destinationAccountNumber))){
			if(amount > 0 && accountRepository.searchAccount(sourceAccountNumber).getAmount() > amount){
				
				accountRepository.searchAccount(destinationAccountNumber).setAmount(accountRepository.searchAccount(destinationAccountNumber).getAmount() + amount);
				accountRepository.searchAccount(sourceAccountNumber).setAmount(accountRepository.searchAccount(sourceAccountNumber).getAmount() - amount);
				
				int srcBal =  accountRepository.searchAccount(sourceAccountNumber).getAmount();
				int destBal = accountRepository.searchAccount(destinationAccountNumber).getAmount();
				
				str.add("'"+srcBal+"'");
				str.add("'"+destBal+"'");
				str.add("'"+amount+"'");
			}
			else
				throw new InsufficientBalanceException("Balance not sufficient to deposit");
		}else{
			throw new InvalidAccountNumberException("Account Number is not valid");
		}
		
		return str.toString();
	}

}
