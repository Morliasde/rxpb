<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>登录</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<link rel="stylesheet" href="css/login.css" type="text/css"></link>
</head>

<body>
	<jsp:include page="/module/top.jsp" />
	<div class="main">
		<div class="loginForm">
			<form action="login" method="post">
			<div class="title">登录</div>			
				<div class="article">
				<div>
					用户名:<br><input type="text" name="user.userName" placeholder="username"
						value="" />
				</div>
				<div>
					密码:<br><input type="password" name="user.password" placeholder="password"
						value="" />
				</div>
				<div>
					<input type="submit" value="登录">&nbsp;<input type="reset"
						value="重置">&nbsp;<a href="Regist.jsp">注册</a>
				</div>

				<div class="warning">${msg}</div>
				</div>
			</form>
		</div>

	</div>


	<jsp:include page="/module/footer.jsp" />
</body>
</html>
