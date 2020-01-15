package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.UserBo;
import com.bank.bo.UserBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;

public class SignUpController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public SignUpController() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account a = new Account();
		UserBo userBo = new UserBoImpl();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;

		a.setPd_username(request.getParameter("usernameSignup"));
		a.setPd_password(request.getParameter("password1Signup"));
		a.setPd_fullname(request.getParameter("fullname"));
		a.setPd_usertype("Customer");
		a.setPd_email(request.getParameter("email"));

		try {
			userBo.signup(a);
			rd = request.getRequestDispatcher("index.jsp");
			rd.include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Account Created Successfully!');");
			out.println("</script>");
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
