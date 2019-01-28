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
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark fixed-top">
		<a class="navbar-brand" href="#">演示系统</a>
		<ul class="navbar-nav">
			<li class="nav-item"><a class="nav-link" href="#">添加用户</a></li>
			<li class="nav-item" >
			<a class="nav-link" href="#" id="txt"></a>
			</li>
		</ul>
	</nav>
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