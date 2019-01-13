<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Signin</title>
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
	        <p class="navbar-text"> 用户登录</p>
	    </div>
	    <p class="navbar-text navbar-right" id="txt">
		</p>
	    <p class="navbar-text navbar-right">Time:
	    <%
				Date date = new Date();
				SimpleDateFormat fmt = new SimpleDateFormat("yyyy/MM/dd");
				out.println(fmt.format(date));
		%>
		</p>
		</div>
	</nav>
    <center>
	<div style="height: 60px"> </div>
	<div class="container">
	    <div style="height: 8px; width:120px;text-align: center;" >
	    <span class="text-danger" style="font-size: 20px">
	     <strong>
	     ${result}
		 ${checkcode_msg}
		 </strong></span>  
	    </div>
	   
		<form class="form-signin" action="login.do" method="post">
			<h2 class="form-signin-heading">Please sign in</h2>
			
			<input type="text" name="username" class="form-control" placeholder="User Name" required autofocus>
			<input type="password" name="pwd" class="form-control" placeholder="Password" required>
			<div>
			</div>
			<a href="javascript:;" onclick="document.getElementById('num').src = 'CheckcodeServlet1?'+(new Date()).getTime()">换一张</a>
			<img id="num" src="CheckcodeServlet1" />
			<input type="text" class="form-control inputgri" name="number" placeholder="Captcha" required/>
		    &nbsp &nbsp &nbsp &nbsp
			<button class="btn btn-lg btn-primary " type="submit">Sign in</button>
			&nbsp &nbsp &nbsp &nbsp
			&nbsp&nbsp&nbsp<a href="regist.jsp"  class="button btn btn-lg btn-primary">Regist</a>
		</form>
	</div>
	</center>
		<script type="text/javascript" src="js/time.js"></script>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script type="text/javascript" src="js/canvas.js"></script>
</body>
</html>