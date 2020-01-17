package com.bank.bo;

import java.util.List;

import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.bank.to.Card;
import com.bank.to.Transaction;

public interface CustBo {
	
	public List<Card> cardInfoList(Account a) throws BusinessException;

	public void deposit(Account a, Card c) throws BusinessException;
	
	public void withdraw(Account a, Card c) throws BusinessException;
	
	public void insertLogProc(Transaction t) throws BusinessException;

	public void transferProc(Account a, Transaction t) throws BusinessException;

	public void applyNewCard(Account a, Card c) throws BusinessException;

	public List<Transaction> pendingMoneyList(Account a) throws BusinessException;
	
	public void approveMoney(Account a, Card c) throws BusinessException;
	
	public void removePendingAmount(Card c, Transaction t) throws BusinessException;
	
}
