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
import com.bank.to.Transaction;
import com.google.gson.Gson;

public class EmpLog extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmpLog() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		EmpBo empBo = new EmpBoImpl();
		PrintWriter out = response.getWriter();

		try {
			List<Transaction> logList = empBo.logList();
			out.print(gson.toJson(logList));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
