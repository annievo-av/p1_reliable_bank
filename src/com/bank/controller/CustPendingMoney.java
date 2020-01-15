package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.CustBo;
import com.bank.bo.CustBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.bank.to.Transaction;
import com.google.gson.Gson;

public class CustPendingMoney extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public CustPendingMoney() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		CustBo custBo = new CustBoImpl();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		Account a = (Account) session.getAttribute("account");
		try {
			List<Transaction> pendingMoneyList = custBo.pendingMoneyList(a);
			out.print(gson.toJson(pendingMoneyList));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
