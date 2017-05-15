<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>注册</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" href="css/login.css" type="text/css"></link>
	
<script type="text/javascript">
function showSelect()
{
document.getElementById("displayName").style.display="none";
document.getElementById("companyList").style.display="block";
}
function showInput(){
document.getElementById("companyList").style.display="none";
document.getElementById("displayName").style.display="block";
}

function setCompany(){
	var objS = document.getElementById("companySelect");	
	var index = objS.selectedIndex;
	
	var id=objS.options[index].value;
	var name=objS.options[index].text;
	
	
	document.getElementById("companyId").value=id;
	document.getElementById("companyName").value=name;
}



</script>


  </head>
  
  <body>
  
  <jsp:include page="/module/top.jsp" />
	<div class="main">
		<div class="loginForm">
			
    		<form action="regist" method="post">
    		
		<div class="title">注册</div>	
		<div class="article">
    			<div>用户名:<br><input type="text" name="user.userName" placeholder="UserName" value="${user.userName}"></div>    			
    			<div>密码:<br><input type="password" name="user.password" placeholder="Password" value="${user.password}"></div>    			
    			<div>邮箱:<br><input type="text" name="user.email" placeholder="Email" value="${user.email}"></div>
    			<div>
    			<!-- 
    			<input type="radio" name="user.roleId" value="4" <c:if test="${user.roleId==4}">checked</c:if> onfocus="showSelect();" >参评单位
    			 -->
    			<input type="radio" name="user.roleId" value="3" <c:if test="${user.roleId==3 || user.roleId==null}">checked</c:if> onfocus="showInput();">评委
    			<input type="radio" name="user.roleId" value="2" <c:if test="${user.roleId==2}">checked</c:if> onfocus="showInput();">工作人员
    			</div>
    			<div id="companyList" <c:if test="${user.roleId==3|| user.roleId==2 || user.roleId==null }">style="display:none"</c:if>>
    			选择参选单位：<br>
    			<select id="companySelect" onchange="setCompany();">								
							<option value="0">未选择参选单位</option>
						<c:forEach items="${companys}" var="company">
							<option <c:if test="${user.companyId==company.companyId }">selected="selected"</c:if>value="${company.companyId}">${company.companyName}</option>
						</c:forEach>									
				</select>
				<div><input id="companyName" type="hidden" name="company.companyName" value="${user.displayName}"></div>	    			
    			</div>
    			<div id="displayName" <c:if test="${user.roleId==4}">style="display:none"</c:if>>姓名:<br><input type="text" name="user.displayName" placeholder="姓名" value=""></div>
    			<div><input type="submit" value="注册">&nbsp;<input type="reset" value="重置"></div>
    		<div class="warning">${msg}</div>
    		</div>
    		</form>
		</div>

	</div>


	<jsp:include page="/module/footer.jsp" />
  
  
    	
  </body>
</html>
