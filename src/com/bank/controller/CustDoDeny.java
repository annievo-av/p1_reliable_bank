package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.CustBo;
import com.bank.bo.CustBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Card;
import com.bank.to.Transaction;
import com.google.gson.Gson;

public class CustDoDeny extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public CustDoDeny() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CustBo custBo = new CustBoImpl();
		Gson gson = new Gson();

		Card c = gson.fromJson(request.getReader(), Card.class);
		Transaction t = new Transaction();
		t.setAmount(c.getBalance());
		
		try {
			custBo.removePendingAmount(t);
			out.print(gson.toJson(c));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
