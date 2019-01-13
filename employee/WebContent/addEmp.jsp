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
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">演示系统</a>
			</div>
			<div>
				<p class="navbar-text">添加用户</p>
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
	<%-- 	    ${result} --%>
	<%-- 		${checkcode_msg} --%>
	<div style="height: 60px"></div>
	<div class="container">

		<form class="form-signin" action="add.do" method="post">
			<h2 class="form-signin-heading">添加员工</h2>

			<input type="text" name="name" class="form-control"
				placeholder="Realname" required> <input type="text"
				name="salary" class="form-control" placeholder="salary" required>
			<input type="text" name="age" class="form-control" placeholder="age"
				required> <br>
			<button class="btn btn-lg btn-primary " type="submit">Submit</button>
		</form>
	</div>
	    <script type="text/javascript" src="js/time.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/canvas.js"></script>
</body>
</html>