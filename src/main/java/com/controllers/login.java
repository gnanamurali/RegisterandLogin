package com.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;



import com.db.dbConnection;
import com.model.Users;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class login extends HttpServlet{
	
@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
	String usermail=req.getParameter("userEmail");
	String userpass=req.getParameter("userPwd");
	resp.setContentType("text/html");
	PrintWriter out=resp.getWriter();
	
	try {
		
		Connection con=dbConnection.getConnection();
		String sel_query="SELECT * FROM USERS WHERE EMAIL=? AND PASSWORD=? ;";
		PreparedStatement ps=con.prepareStatement(sel_query);
		ps.setString(1, usermail);
		ps.setString(2, userpass);
		ResultSet rs=ps.executeQuery();
		if(rs.next())
			{
				Users user =new Users();
				user.setUsername(rs.getString("name"));
				user.setEmail(rs.getString("EMAIL"));
				user.setGender(rs.getString("gender"));
				user.setCity(rs.getString("city"));
				
				HttpSession hs=req.getSession();
				hs.setAttribute("current_user",user);
				RequestDispatcher rd=req.getRequestDispatcher("/profile.jsp");
				rd.include(req, resp);
				
			}
		else
		{
			out.print("<h3 style=color:red>Check email and password</h3>");
			RequestDispatcher rd=req.getRequestDispatcher("/login.html");
			rd.include(req, resp);
		}
		
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		out.print("<h3 style=color:red>Error occured:"+e.getMessage()+"</h3>");
		RequestDispatcher rd=req.getRequestDispatcher("/login.html");
		rd.include(req, resp);
		
	}
}
	
	
	
	

}

