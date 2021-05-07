package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc.com.model.Model;


@WebServlet("/Login")
public class Login extends HttpServlet {

	private static final long serialVersionUID = 1L;
private HttpSession session;

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
         String custid=request.getParameter("custid");
		 String pass=request.getParameter("pwd");
		 System.out.println(custid+" "+pass);
		 session= request.getSession(true);
		 try {
			Model m= new Model();
			 boolean b= m.login(custid, pass);
			 if(b==true)
			 {
//				 session.setAttribute("accno", m.getAccno());
				 response.sendRedirect("Home.html");
			 }
			 else {
				 response.sendRedirect("Error.html");
			 }
			 
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
		
	}

}
