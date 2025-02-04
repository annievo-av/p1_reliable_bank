package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.bo.UserBo;
import com.bank.bo.UserBoImpl;
import com.bank.exception.BusinessException;
import com.bank.to.Account;

public class LoginController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public LoginController() {
		super();
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Account a = new Account();
		UserBo userBo = new UserBoImpl();
		PrintWriter out=response.getWriter();
		HttpSession session = request.getSession();
		RequestDispatcher rd = null;
		
		String usernameLogin = request.getParameter("usernameLogin");
		String passwordLogin = request.getParameter("passwordLogin");
		
		try {
			a = userBo.validLogin(usernameLogin, passwordLogin);
			session.setAttribute("usernameLogin", a.getUsername());
			session.setAttribute("account", a);
			
			if(a.getUsername() == null) {
				rd = request.getRequestDispatcher("index.jsp");
				rd.include(request, response);
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Invalid input.');");
				out.println("</script>");
			}
			else {
				if(userBo.isEmployee(a)) {
					response.sendRedirect("employee.jsp");
				}
				else {
					response.sendRedirect("customer.jsp");
				}
			}
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
