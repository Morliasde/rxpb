<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>中华绒蟹评比</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link rel="stylesheet" type="text/css" href="css/global_fy.css">

</head>

<body>
	 <jsp:include  page="/module/top.jsp"/> 
  
	<div class="container">
		<div class="midtitle">
			欢迎
			<hr>
		</div>

		<div class="midarticle">
		<p><br><br><br><br><br><br><br><br><br><br><br>
			</p>
			</div>

	</div>
	
	<jsp:include  page="/module/footer.jsp"/> 

</body>
</html>
