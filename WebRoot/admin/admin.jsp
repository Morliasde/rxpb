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

<title>中华绒螯蟹评比</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>

	<jsp:include page="/module/top.jsp" />

	<div class="main">
		<div class="container">
			<jsp:include page="/module/optionAdmin.jsp" />

			<!-- 
	
	<div class="option">
			<a href="admin/user">用户列表</a> <a href="admin/user!checkReg">注册审核
			</a> <a href="admin/crab!getCompanyList">蟹信息录入 </a> <a
				href="admin/crab!getFatnessVarDetail">参数编辑</a> <a
				href="admin/crab!createScore">成绩生成</a>			

		</div>
	
	 -->

			<div class="title">
				管理员
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />
</body>
</html>
