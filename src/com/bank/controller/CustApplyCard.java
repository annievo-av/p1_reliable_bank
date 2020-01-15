package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.CustBo;
import com.bank.bo.CustBoImpl;
import com.bank.bo.Validator;
import com.bank.exception.BusinessException;
import com.bank.to.Account;
import com.bank.to.Card;

public class CustApplyCard extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public CustApplyCard() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	Card card = new Card();
    	Validator v = new Validator();
		CustBo custBo = new CustBoImpl();
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession(false);
		Account a = (Account) session.getAttribute("account");
		RequestDispatcher rd = null;
		
		String cardNumber = request.getParameter("cardNumber");
		String amount = request.getParameter("amount");
		
		int validCardNumber = 0;
		try {
			validCardNumber = v.validCardNumber(cardNumber);
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
		
		double validAmount = 0;
		try {
			validAmount = v.validAmount(amount);
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
		
		card.setPd_cardNumber(validCardNumber);
		card.setPd_cardType("Credit Card");
		card.setPd_balance(validAmount);
		card.setApplicator(a.getUsername());

		try {
			custBo.applyNewCard(card);
			rd = request.getRequestDispatcher("employee.jsp");
			rd.include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Account Created Successfully!');");
			out.println("</script>");
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
    }

}
