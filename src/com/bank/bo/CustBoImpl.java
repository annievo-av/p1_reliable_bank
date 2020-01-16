package com.bank.bo;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.bank.dao.CustDao;
import com.bank.dao.CustDaoImpl;
import com.bank.dao.EmpDao;
import com.bank.dao.EmpDaoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.bank.to.Card;
import com.bank.to.Transaction;

public class CustBoImpl implements CustBo {

	private CustDao custDao;
	private EmpDao empDao;
	
	public CustDao getCustDao() {
		if (custDao == null) {
			custDao = new CustDaoImpl();
		}
		return custDao;
	}
	private EmpDao getEmpDao() {
		if (empDao == null) {
			empDao = new EmpDaoImpl();
		}
		return empDao;
	}

	@Override
	public List<Card> cardInfoList(Account a) throws BusinessException {
		List<Card> cardInfoList = getCustDao().cardInfoList(a);
		return cardInfoList;
	}

	@Override
	public void deposit(Account a, Card c) throws BusinessException {
		List<Card> cList = cardInfoList(a);
		
		double curr = 0;
		for (Card card : cList) {
			if(card.getCardNumber() == c.getCardNumber()) {
				curr = card.getBalance();
			}
		}
		
		c.setBalance(curr + c.getBalance());
		getCustDao().updateBalance(c);
		
		Transaction t = new Transaction();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		t.setPerson_1(a.getUsername());
		t.setAction("Deposit $" + c.getBalance());
		t.setPerson_2(Integer.toString(c.getCardNumber()));
		t.setTime(formatter.format(date));
		insertLogProc(t);
	}
	
	@Override
	public void withdraw(Account a, Card c) throws BusinessException {
		List<Card> cList = cardInfoList(a);
		
		double curr = 0;
		for (Card card : cList) {
			if(card.getCardNumber() == c.getCardNumber()) {
				curr = card.getBalance();
			}
		}
		
		c.setBalance(curr - c.getBalance());
		getCustDao().updateBalance(c);
		
		Transaction t = new Transaction();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		t.setPerson_1(a.getUsername());
		t.setAction("Withdraw $" + c.getBalance());
		t.setPerson_2(Integer.toString(c.getCardNumber()));
		t.setTime(formatter.format(date));
		insertLogProc(t);
	}

	@Override
	public void insertLogProc(Transaction t) throws BusinessException {
		getCustDao().insertLogProc(t);
	}

	@Override
	public void transferProc(Account a, Transaction t) throws BusinessException {
		List<Card> myCardList = cardInfoList(a);
		List<Account> systemCardList = getEmpDao().accountInfoList();
		
		int mySelectedCardNumber = Integer.parseInt(t.getSender());
		double transferAmount = t.getAmount();
		int cardNumberTransferTo = Integer.parseInt(t.getReceiver());
		Card myCard = new Card();
		Card receiverCard = new Card();
		double myCurrentBalance, myNewBalance;
		
		for (Card card : myCardList) {
			if(card.getCardNumber() == mySelectedCardNumber) {
				myCard = card;
			}
		}
		myCurrentBalance = myCard.getBalance();
		myNewBalance = myCurrentBalance - transferAmount;
		myCard.setBalance(myNewBalance);
		getCustDao().updateBalance(myCard);
		
		for (Account account : systemCardList) {
			if(account.getCard().getCardNumber() == cardNumberTransferTo) {
				receiverCard = account.getCard();
			}
		}
		
		t.setSender(myCard.getUsername());
		t.setAmount(transferAmount);
		t.setReceiver(receiverCard.getUsername());
		getCustDao().transferProc(t);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		t.setPerson_1(a.getUsername());
		t.setAction("Transfer $" + transferAmount);
		t.setPerson_2(receiverCard.getUsername());
		t.setTime(formatter.format(date));
		insertLogProc(t);
	}

	@Override
	public void applyNewCard(Card c) throws BusinessException {
		getCustDao().applyNewCard(c);
	}

	@Override
	public List<Transaction> pendingMoneyList(Account a) throws BusinessException {
		List<Transaction> pendingMoneyList = getCustDao().pendingMoneyList(a);
		return pendingMoneyList;
	}
	
	@Override
	public void approveMoney(Account a, Card c) throws BusinessException {
		List<Card> cList = cardInfoList(a);
		
		double curr = 0;
		double acceptedAmount = c.getBalance();
		for (Card card : cList) {
			if(card.getCardNumber() == c.getCardNumber()) {
				curr = card.getBalance();
			}
		}
		
		c.setBalance(curr + acceptedAmount);
		getCustDao().updateBalance(c);
		
		Transaction t = new Transaction();
		t.setAmount(acceptedAmount);
		removePendingAmount(t);
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
		Date date = new Date(System.currentTimeMillis());
		t.setPerson_1(a.getUsername());
		t.setAction("Accept $" + c.getBalance());
		t.setPerson_2(Integer.toString(c.getCardNumber()));
		t.setTime(formatter.format(date));
		insertLogProc(t);
	}

	@Override
	public void removePendingAmount(Transaction t) throws BusinessException {
		getCustDao().removePendingAmount(t);
	}

}
