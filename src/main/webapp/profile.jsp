<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.db.dbConnection"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.model.Users"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="styleProfile.css">
<title>Your Profile</title>
</head>
<body>
	<header>
		<div class="logButton"><a href="logout">Logout</a></div>
	</header>
	<% 
		 String date=null;
		 String time=null;
		 String day=null;
		 Users user = (Users) session.getAttribute("current_user");
		 Connection con=dbConnection.getConnection();
		 String date_query="SELECT CURDATE() AS DATE ,CURTIME() AS TIME , DAYNAME(CURDATE()) AS DAY;";
		 PreparedStatement ps=con.prepareStatement(date_query);
		 ResultSet rs=ps.executeQuery();
		 if(rs.next())
		 {
			 time=rs.getString("TIME");
			 date=rs.getString("DATE");
			 day=rs.getString("DAY");
			 
		 }
	%>
	<div class="proBody">
		<div class="boxTop">
			<h1> Hello <%= user.getUsername() %> </h1><br>
	 		<p>Hope you are doing well</p>
		</div>
		<h2>How's the weather today in <%= user.getCity() %></h2><br>
		<div class="boxBottom">
			<h3>Current Time : <%= time %></h3>
			<h3>Current Date : <%= date %></h3>
			<h3>Current Day : <%= day %></h3>
		</div>
	</div>
	
	
</body>
</html>