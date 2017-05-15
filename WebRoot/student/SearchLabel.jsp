<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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

<title>查找标示所在组</title>

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

			<jsp:include page="/module/optionStudent.jsp" />


			<div class="title">
				查找标识所在组
				<hr>
			</div>

			<div class="article">
				<form action="student/getGroupIdByLabel" method="post">
						<div>
							<input type="text" name="crab.crabLabel" value="${crab.crabLabel}">
							<input type="submit" value="查询">
						</div>
				</form>
			
				<div class="warning">标识所属组号:${msg }</div>
				
			</div>


		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />



</body>
</html>
