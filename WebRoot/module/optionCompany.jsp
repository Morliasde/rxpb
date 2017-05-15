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
			<a href="company/result!getFatness">查看金蟹奖、优质蟹奖评分评分</a>
    <a href="company/result!getQuality">查看种质评分</a>
    <a href="company/result!getTaste">查看口感评分</a>
    <a href="user/getSelfDetail">个人资料编辑</a>

		</div>
		
</body>
</html>
