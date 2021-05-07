package com.abc.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import abc.com.model.Model;


@WebServlet("/Chackbalance")
public class Chackbalance extends HttpServlet {
	
private HttpSession session;

protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 String accno=request.getParameter("accno");
	 String pass=request.getParameter("pwd");
	 
	 HttpSession session= request.getSession(true);
	 
	 
	 try {
		 Model m=new Model();
	    
		 
	 }catch(Exception e) {
		 e.printStackTrace();
	 }
	
	}

}
