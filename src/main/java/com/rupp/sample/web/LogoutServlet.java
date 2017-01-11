package com.rupp.sample.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutServlet extends HttpServlet {
	@Override
    public void init() throws ServletException {
		System.out.println("---init method call ---");
        
    }
	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(String.format("=====%s method is called ====", request.getMethod()));
        HttpSession session = request.getSession();
        if (session != null) {
            session.invalidate();
        }
        
        response.sendRedirect("formLogin.jsp");
    }
}
