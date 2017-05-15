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

<title>数据录入</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function onDelete(crabId,groupId,groupQueue){
			if(window.confirm('注意，此操作不可逆，确认删除?'))
			window.location.href='<%=basePath%>student/deleteCrab?crabId='+crabId+'&groupId='+groupId +'&groupQueue='+groupQueue ;
		}
		
		function checkInput(){
			var Text=document.getElementsByTagName("input");
			for(var i=0;i<Text.length;i++){
				if(Text[i].type=="text"&&Text[i].placeholder!="标识"){
					if(Text[i].value==""||Text[i].value=="0.0"){
						Text[i].style.border="1px solid red";
					}else{
						Text[i].style.border="";
					}
				}
			}
		}
	</script>
</head>

<body>
	<jsp:include page="/module/top.jsp" />

	<div class="main">
		<div class="container">

			<jsp:include page="/module/optionStudent.jsp" />


			<div class="title">
				数据录入
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
				<strong>注意:执行添加和删除操作前,请先提交未保存的数据!</strong><br>
				
				<a href="student/getGroupList">返回</a>	
						
					<form action="student/addCrab" method="post">
						<div>
							<div>
								<input type="hidden" name="groupId"
									value="${groupId}"> 
									<input type="hidden" name="groupQueue"
									value="${groupQueue}">
									组号：${groupQueue}	
							</div>
							<div>
								<div>
									雌雄：<input type="radio" name="crab.crabSex" value="3" checked="checked" />同时添加
									<input type="radio" name="crab.crabSex" value="2"/>雌
									<input type="radio" name="crab.crabSex" value="1"/>雄
								</div>
								<div>
									数量：<input type="text" name="number" value="10"> <input
										type="submit" value="添加">
								</div>
							</div>
						</div>
					</form>
				<hr>

				<div>
					<form action="student/saveCrabData" method="post">
						<input type="hidden" name="groupId" value="${groupId}">
						<input type="hidden" name="groupQueue" value="${groupQueue}">
						<div>

							<table border="1">
								<tr>雌
								</tr>
								<tr>
									<th>编号</th>
									<th>标识</th>
									<th>体重(g)</th>
									<th>壳长(cm)</th>
									<th>操作</th>
								</tr>
								<c:forEach var="crabF" items="${crabFs}"
									varStatus="order">
									<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
										<td>${order.count}<input type="hidden"
											name="crabF.crabId${order.count}"
											value="${crabF.crabId }">
										</td>
										<td><input type="text"
											name="crabF.crabLabel${order.count}" placeholder="标识"
											value="${crabF.crabLabel}">
										</td>
										<td><input type="text"
											name="crabF.crabWeight${order.count}"
											onfocus="if (value =='0.0'||value =='0'){value =''}"
											onblur="checkInput()" placeholder="体重(g)"
											value="${crabF.crabWeight}">
										</td>
										<td><input type="text"
											name="crabF.crabLength${order.count}"
											onfocus="if (value =='0.0'||value =='0'){value =''}"
											onblur="checkInput()" placeholder="壳长(cm)"
											value="${crabF.crabLength}">
										</td>
										<td><a href="javascript:onDelete(${crabF.crabId },${groupId},${groupQueue})">删除</a></td>

									</tr>
								</c:forEach>
							</table>

						</div>

						<div>

							<table border="1">
								<tr>雄
								</tr>
								<tr>
									<th>编号</th>
									<th>标识</th>
									<th>体重(g)</th>
									<th>壳长(cm)</th>
									<th>操作</th>
								</tr>
								<c:forEach var="crabM" items="${crabMs}"
									varStatus="order">
									<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
										<td>${order.count}<input type="hidden"
											name="crabM.crabId${order.count}"
											value="${crabM.crabId }">
										</td>
										<td><input type="text"
											name="crabM.crabLabel${order.count}" placeholder="标识"
											value="${crabM.crabLabel}">
										</td>
										<td><input type="text"
											name="crabM.crabWeight${order.count}"
											onfocus="if (value =='0.0'||value =='0'){value =''}"
											onblur="checkInput()" placeholder="体重(g)"
											value="${crabM.crabWeight}">
										</td>
										<td><input type="text"
											name="crabM.crabLength${order.count}"
											onfocus="if (value =='0.0'||value =='0'){value =''}"
											onblur="checkInput()" placeholder="壳长(cm)"
											value="${crabM.crabLength}">
										</td>
										<td><a href="javascript:onDelete(${crabM.crabId },${groupId},${groupQueue})">删除</a></td>


									</tr>
								</c:forEach>
							</table>
						</div>
						<input type="submit" value="提交">
					</form>
				</div>
			</div>


		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />



</body>
</html>
