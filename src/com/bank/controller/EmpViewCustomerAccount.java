package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.EmpBo;
import com.bank.bo.EmpBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.google.gson.Gson;

public class EmpViewCustomerAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmpViewCustomerAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		EmpBo empBo = new EmpBoImpl();
		PrintWriter out = response.getWriter();
		
		try {
			List<Account> accountInfoList = empBo.accountInfoList();
			out.print(gson.toJson(accountInfoList));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
