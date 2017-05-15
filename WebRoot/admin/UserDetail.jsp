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

<title>编辑用户</title>

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
				编辑用户
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>


				<div>
					<form action="admin/editUser" method="post">
					
					<div>
							<input type="hidden" name="user.userId" value="${user.userId }">
						</div>
						<div>
							用户名:${user.userName }
						</div>
						<div>
							显示名:<br><input type="text" name="user.displayName" placeholder="姓名/企业名"
								value="${user.displayName }">
						</div>
						<div>
							邮箱:<br><input type="text" name="user.email" placeholder="邮箱"
								value="${user.email }">
						</div>
						<div>
							<input type="radio" name="user.roleId" value="3"
								onfocus="showSelect();"
								<c:if test="${user.roleId==3 }" >checked</c:if> />评委 <input
								type="radio" name="user.roleId" value="2"
								onfocus="hiddenSelect();"
								<c:if test="${user.roleId==2 }">checked</c:if> />工作人员 <input
								type="radio" name="user.roleId" value="1"
								onfocus="hiddenSelect();"
								<c:if test="${user.roleId==1 }">checked</c:if> />管理员
						</div>
						<input type="submit" value="保存"><a href="admin/getUserList">返回</a>
					</form>
				</div>


			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />

</body>
</html>
