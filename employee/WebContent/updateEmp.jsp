<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<link href="css/canvas.css" rel="stylesheet">
</head>
<body onload="startTime()">
<canvas id="canvas"></canvas>
	<center>
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">演示系统</a>
				</div>
				<div>
					<p class="navbar-text">员工列表</p>
				</div>
				  <p class="navbar-text navbar-right" id="txt"></p>
		    <p class="navbar-text navbar-right">Time:
		    <%
					Date date = new Date();
					SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
					out.println(fmt.format(date));
			%>
			</p>
			</div>
		</nav>
		<div class="container">
			<div class="jumbotron">
				<h1 style="font-size: 40px">员工管理系统</h1>
				<p>这是一个关于 JavaEE Crud的实例。</p>
			</div>
		</div>
		<form action="modify.do?id=${e.id}" method="post">
			<table class="table" style="width: 30%">
				<tr>
					<td valign="middle" align="right">ID:</td>
					<td valign="middle" align="left">${e.id}</td>
				</tr>
				<tr>
					<td valign="middle" align="right">姓名:</td>
					<td valign="middle" align="left">
						<%-- 					 <input type="text" class="inputgri" name="name" value="${e.name}" /> --%>
						<input type="text" name="name" class="form-control"
						placeholder="Realname" value="${e.name}">
					</td>
				</tr>
				<tr>
					<td valign="middle" align="right">薪水:</td>
					<td valign="middle" align="left"><input type="text"
						name="salary" class="form-control" placeholder="Realname"
						value="${e.salary}"> <%-- 					<input type="text" class="inputgri" name="salary" value="${e.salary}" /></td> --%>
				</tr>
				<tr>
					<td valign="middle" align="right">年龄:</td>
					<td valign="middle" align="left"><input type="text" name="age"
						class="form-control" placeholder="Realname" value="${e.age}">
						<%-- 					<input type="text" class="inputgri" name="age" value="${e.age}" /></td> --%>
				</tr>
			</table>
			<p>
				<input type="submit" class="button" value="确认" />
			</p>
		</form>
	</center>
	    <script type="text/javascript" src="js/time.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/canvas.js"></script>
</body>
</html>