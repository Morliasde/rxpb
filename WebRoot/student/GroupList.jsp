<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>参选单位（组）列表</title>
    
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
				组列表
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
				<table border="1">
    	<tr>
    		<th>组号</th>
    		<th>操作</th>   
    	</tr>
		<c:forEach var="group" items="${groups}" varStatus="order">
						<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
							<td>第${order.count}组</td>
							<td><a href="student/getGroupData?groupId=${group.groupId}&groupQueue=${order.count}">数据录入</a>
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
