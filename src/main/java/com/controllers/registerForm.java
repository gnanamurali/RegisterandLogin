package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.print.DocFlavor.STRING;

import com.db.dbConnection;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/regform")
public class registerForm extends HttpServlet {
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		resp.setContentType("text/html");
		PrintWriter out = resp.getWriter();
		String username=req.getParameter("userName");
		String email=req.getParameter("userEmail");
		String pwd=req.getParameter("userPwd");
		long ph=Long.parseLong(req.getParameter("userPh"));
		String gender=req.getParameter("userGender");
		String city=req.getParameter("userCity");
		
		try {
			Connection con=dbConnection.getConnection();
			String ins_query="INSERT INTO users VALUES(?,?,?,?,?,?);";
			PreparedStatement ps=con.prepareStatement(ins_query);
			ps.setString(1, username);
			ps.setString(2, email);
			ps.setString(3, pwd);
			ps.setLong(4, ph);
			ps.setString(5, gender);
			ps.setString(6, city);
			int count=ps.executeUpdate();
			if(count>0)
			{
				resp.setContentType("text/html");
				out.print("<h3 style='color:green'>Account Registered Successfully</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/login.html");
				rd.include(req, resp);
			}
			else {
				
				out.print("<h3 style='color:green'>Account Registration failed</h3>");
				RequestDispatcher rd = req.getRequestDispatcher("/register.html");
				rd.include(req, resp);
				
			}
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
			
			out.print("<h3 style='color:green'>Some error occured:"+e.getMessage()+"</h3>");
			RequestDispatcher rd = req.getRequestDispatcher("/register.html");
			rd.include(req, resp);
		}
	}

	
}