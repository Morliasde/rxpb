<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
<link rel="stylesheet" href="css/global_fy.css" type="text/css"></link>
</head>

<body>
	<div class="top">

		<div class="topcontainer">
			<div class="logo">中华绒螯蟹评比</div>
			<div class="menu">
				<div class="menulist">
					<a href="">首页</a>
				</div>
				<div class="menulist">
					<a href="getFatnessSort">查看成绩</a>
				</div>
				<!-- 
				
				<div class="menulist">
					<a href="Disable.jsp">历届回顾</a>
				</div>
				
				 -->
				
			</div>
			<div class="login">
				<c:if test="${empty loginDisplayName.toString()}">
					<a href="Login.jsp">登录</a>|<a href="Regist.jsp">注册</a>
				</c:if>
				<c:if test="${not empty loginDisplayName.toString()}">
					<a
						href="
						<c:if test="${loginRoleId==1}">
						admin/admin
						</c:if>
						<c:if test="${loginRoleId==2}">
						student/student
						</c:if>
						<c:if test="${loginRoleId==3}">
						judge/judge
						</c:if>
						<c:if test="${loginRoleId==4}">
						company/company
						</c:if>
						.jsp">你好,${loginDisplayName}
					</a>
					|
					<a href="logout">注销</a>
				</c:if>

			</div>
		</div>
	</div>

</body>
</html>
