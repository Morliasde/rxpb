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
    
    <title>种质排名</title>
    
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
			<jsp:include page="/module/optionResult.jsp" />


			<div class="title">
			  种质排名
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
    <table <c:if test="${empty sflist}">style="display:none"</c:if> border="1">
    	<tr>
    		<th>名次</th>
    		<th>组号</th>
    		<th>参选单位</th>
    		<th>专家分</th> 
    	</tr>
    	<tr<c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
    	<c:forEach var="sf" items="${sflist}" varStatus="order">
    		<td>${order.count}</td>
    		<td>第${sf.groupId}组</td>
    		<td>${sf.companyName}</td>
    		<td>${sf.qualityScore }</td>    		
		</tr>
    	</c:forEach>
    </table>
			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" /> 
  
  
  

  </body>
</html>
