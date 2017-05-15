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

<title>编辑参选单位</title>

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
				编辑参选单位
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>


				<div>
					<form action="admin/editCompany" method="post">
						<div>
							<input type="hidden" name="company.companyId" value="${company.companyId }">
						</div>
						<div>
							参选单位全称:<input type="text" name="company.companyName" placeholder="参选单位"
								value="${company.companyName }">
						</div>
						<input type="submit" value="保存"><a href="admin/getCompanyList">返回</a>
					</form>
				</div>


			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />

</body>
</html>
