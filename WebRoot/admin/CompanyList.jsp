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
				参选单位列表
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>

				<a href="admin/InsertCompany.jsp">添加参选单位</a>

				<table border="1px">
					<tr>
						<th>组号</th>
						<th>参选单位</th>
						<th>操作</th>
					</tr>
					<c:forEach var="company" items="${companies}" varStatus="order">
						<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
							<td>${order.count}</td>
							<td>${company.companyName}</td>
							<td>
							<a href="admin/getCompanyScore?companyId=${company.companyId}&companyName=${company.companyName}&groupId=${company.groupId}&groupQueue=${order.count}">查看得分</a>
							<a href="admin/getCompanyDetail?companyId=${company.companyId}">编辑</a>
							<a href="admin/deleteCompany?companyId=${company.companyId}">删除</a>
							</td>
							
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
