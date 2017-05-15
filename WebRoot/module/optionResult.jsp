<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
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

</head>

<body>
		<div class="option">
		<a href="getFatnessSort">金蟹奖、优质奖</a>
   		<a href="getQualitySort">最佳种质奖</a>
    	<a href="getTasteSort">口感奖 </a>

		</div>
		
</body>
</html>
