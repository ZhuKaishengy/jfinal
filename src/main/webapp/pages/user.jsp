<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'user.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table border="1px solid black" cellpadding="10px" cellspacing="0">
    	<tr>
    		<th>id</th>
    		<th>no</th>
    		<th>name</th>
    		<th>password</th>
    	</tr>
    	<tr>
    	<c:forEach items="${users }" var="user">
    		<td>${user.id }</td>
    		<td>${user.no }</td>
    		<td>${user.name }</td>
    		<td>${user.password }</td>
    	</c:forEach>
    	</tr>
    </table>
  </body>
</html>
