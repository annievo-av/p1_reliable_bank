package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.CustBo;
import com.bank.bo.CustBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.bank.to.Card;
import com.google.gson.Gson;

public class CustDoApprove extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public CustDoApprove() {
        super();
    }

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		CustBo custBo = new CustBoImpl();
		HttpSession session = request.getSession(false);
		Account a = (Account) session.getAttribute("account");
		Gson gson = new Gson();

		Card c = gson.fromJson(request.getReader(), Card.class);

		try {
			custBo.approveMoney(a, c);
			out.print(gson.toJson(c));
		} catch (BusinessException e) {
			response.sendRedirect("customer.jsp");
		}
	}

}
