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
			<a href="student/getGroupList">数据录入</a>
			<a href="student/SearchLabel.jsp">查找标识</a>
    		 <a href="user/getSelfDetail">个人资料编辑</a>
			
		</div>
		
</body>
</html>
