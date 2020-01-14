package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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

public class EmpPendingUserAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmpPendingUserAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Gson gson = new Gson();
		EmpBo empBo = new EmpBoImpl();
		PrintWriter out = response.getWriter();

		try {
			List<Account> pendingAccountList = empBo.pendingAccountList();
			List<Account> list = new ArrayList<>();
			for (Account a : pendingAccountList) {
				Account account = new Account();
				if (a.getPd_username() != null) {
					account.setPd_username(a.getPd_username());
					account.setPd_password(a.getPd_password());
					account.setPd_usertype(a.getPd_usertype());
					account.setPd_fullname(a.getPd_fullname());
					account.setPd_email(a.getPd_email());
					list.add(account);
				}
			}
			out.print(gson.toJson(list));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
