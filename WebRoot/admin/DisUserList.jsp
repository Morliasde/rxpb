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

<title>用户列表</title>

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

			<div class="title">
				用户列表
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>

				<table border="1px">
					<tr>
						<th>用户名</th>
						<th>显示名</th>
						<th>用户组</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
					<c:forEach var="user" items="${users}" varStatus="order">
						<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
							<td>${user.userName}</td>
							<td>${user.displayName }</td>
							<td>${user.roleId }</td>
							<td><c:if test="${user.status==1 }">启用
    		</c:if> <c:if test="${user.status==0 }">禁用</c:if>
							<td><a href="admin/getUserDetail?userId=${user.userId }">编辑</a>
								<a href="admin/enableUser?userId=${user.userId }">启用</a>
								<a href="admin/deleteUser?userId=${user.userId }">删除</a>
								</td>
						</tr>
					</c:forEach>
				</table>
			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />




</body>
</html>
