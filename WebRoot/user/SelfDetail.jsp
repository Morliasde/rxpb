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

<title>编辑个人信息</title>

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
		
			<c:if test="${loginRoleId==1}">
				<jsp:include page="/module/optionAdmin.jsp" />
			</c:if>
			<c:if test="${loginRoleId==2}">
				<jsp:include page="/module/optionStudent.jsp" />
			</c:if>
			<c:if test="${loginRoleId==3}">
				<jsp:include page="/module/optionJudge.jsp" />
			</c:if>
		

			<div class="title">
				编辑个人信息
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>


				<div>
					<form action="user/editSelf" method="post">
					
					<div>
							<input type="hidden" name="user.userId" value="${user.userId }">
						</div>
						<div>
							用户名:${user.userName }
						</div>
						<div>
							旧密码(若修改密码必填):<br><input type="password" name="user.password" placeholder="密码"
								value="">
						</div>
						<div>
							新密码:<br><input type="password" name="newPassword" placeholder="密码"
								value="">
						</div>
						<div>
							再次输入新密码:<br><input type="password" name="newPassworda" placeholder="密码"
								value="">
						</div>
						<div>
							显示名:<br><input type="text" name="user.displayName" placeholder="姓名/企业名"
								value="${user.displayName }">
						</div>
						<div>
							邮箱:<br><input type="text" name="user.email" placeholder="邮箱"
								value="${user.email }">
						</div>
						
						<input type="submit" value="保存"><a href="admin/admin.jsp">返回</a>
					</form>
				</div>


			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />

</body>
</html>
