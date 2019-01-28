<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<link href="css/canvas.css" rel="stylesheet">
</head>
<body onload="startTime()">
	<canvas id="canvas"></canvas>
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<a class="navbar-brand" href="#">演示系统</a>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#">员工列表</a></li>
			<li class="nav-item" >
			<a class="nav-link" href="#" id="txt"></a>
			</li>
		</ul>
	</nav>
	<center>
		<div class="container">
			<div class="jumbotron">
				<h1 style="font-size: 40px">员工管理系统</h1>
				<p>这是一个关于 JavaEE Crud的实例。</p>
			</div>
		</div>
		<div style="">
			<table class="table" style="width: 70%">
				<caption>员工列表</caption>
				<thead>
					<tr>
						<th>ID</th>
						<th>姓名</th>
						<th>薪水</th>
						<th>年龄</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="e" items="${employees}" varStatus="status">
						<tr class="active">
							<td>${e.id}</td>
							<td>${e.name}</td>
							<td>￥${e.salary}</td>
							<td>${e.age}</td>
							<td><a href="delete.do?id=${e.id}"
								onclick="return confirm('是否确定删除')">删除</a>&nbsp;<a
								href="load.do?id=${e.id}">修改</a></td>
						</tr>

					</c:forEach>
				</tbody>
			</table>
			<p>
				<input type="button" class="button" value="添加员工"
					onclick="location='<%=request.getContextPath()%>/addEmp.jsp'" />
			</p>
		</div>
	</center>
	<script type="text/javascript" src="js/time.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/canvas.js"></script>
</body>
</html>