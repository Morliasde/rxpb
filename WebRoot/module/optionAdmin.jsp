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
			<a href="admin/getUserList">用户列表</a> 
			<a href="admin/getDisableUserList">注册审核</a> 
			<a href="admin/getCompanyList">参选单位 </a> 
			<a href="admin/getCompetitionConfig">大赛管理</a> 	
			 <a href="user/getSelfDetail">个人资料编辑</a>		

		</div>
		
</body>
</html>
