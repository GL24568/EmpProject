<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<link href="css/signin.css" rel="stylesheet">
<link href="css/canvas.css" rel="stylesheet">
<title>Insert title here</title>
</head>
<body onload="startTime()">
	<canvas id="canvas"></canvas>
	<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">演示系统</a>
			</div>
			<div>
				<p class="navbar-text">用户注册</p>
			</div>
			<p class="navbar-text navbar-right" id="txt"></p>
			<p class="navbar-text navbar-right">
				Time:
				<%
				Date date = new Date();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
				out.println(fmt.format(date));
			%>
			</p>
		</div>
	</nav>
	<div style="height: 60px"></div>
	<div class="container">

		<form class="form-signin" action="regist.do" method="post">
			<h2 class="form-signin-heading">Please register</h2>

			<input type="text" name="username" class="form-control"
				placeholder="User Name" required autofocus> <input
				type="text" name="name" class="form-control" placeholder="Realname"
				required> <input type="password" name="pwd"
				class="form-control" placeholder="Password" required> <label
				class="radio-inline"> <input type="radio" name="gender"
				placeholder="Gender" required value="m"> male
			</label> <label class="radio-inline"> <input type="radio"
				name="gender" placeholder="Gender" required value="f">
				female
			</label> <br> <a href="javascript:;"
				onclick="document.getElementById('num').src = 'CheckcodeServlet1?'+(new Date()).getTime()">换一张</a>
			<img id="num" src="CheckcodeServlet1" /> <input type="text"
				class="form-control inputgri" name="number" placeholder="Captcha"
				required /> &nbsp &nbsp &nbsp &nbsp &nbsp
			<button class="btn btn-lg btn-primary " type="submit">Submit</button>
		</form>
	</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/canvas.js"></script>
	<script type="text/javascript" src="js/time.js"></script>
</body>
</html>