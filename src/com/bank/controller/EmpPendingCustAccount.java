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
import com.bank.to.Card;
import com.google.gson.Gson;

public class EmpPendingCustAccount extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public EmpPendingCustAccount() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		Gson gson = new Gson();
		EmpBo empBo = new EmpBoImpl();
		PrintWriter out = response.getWriter();

		try {
			List<Card> pendingCardList = empBo.pendingCardList();
			List<Card> list = new ArrayList<>();
			for (Card c : pendingCardList) {
				Card card = new Card();
				if (c.getApplicator() != null) {
					card.setApplicator(c.getApplicator());
					card.setPd_cardNumber(c.getPd_cardNumber());
					card.setPd_cardType(c.getPd_cardType());
					card.setPd_balance(c.getPd_balance());
					list.add(card);
				}
			}
			
			out.print(gson.toJson(list));
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
