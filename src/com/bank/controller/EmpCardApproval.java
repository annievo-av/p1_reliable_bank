package com.bank.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.bo.EmpBo;
import com.bank.bo.EmpBoImpl;
import com.bank.exception.BusinessException;

public class EmpCardApproval extends HttpServlet {

	private static final long serialVersionUID = 1L;

    public EmpCardApproval() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		EmpBo empBo = new EmpBoImpl();
		PrintWriter out = response.getWriter();
		RequestDispatcher rd = null;

		String pendingApplicator = request.getParameter("cardOption");
		try {
			empBo.approveCard(pendingApplicator);
			rd = request.getRequestDispatcher("employee.jsp");
			rd.include(request, response);
			out.println("<script type=\"text/javascript\">");
			out.println("alert('Successfully Approved Account!');");
			out.println("</script>");
		} catch (BusinessException e) {
			out.print(e.getMessage());
		}
	}

}
