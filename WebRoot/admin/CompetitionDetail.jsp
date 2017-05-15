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

<title>大赛管理</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

</head>

<body>&nbsp; 
 
	<jsp:include page="/module/top.jsp" />

	<div class="main">
		<div class="container">
			<jsp:include page="/module/optionAdmin.jsp" />

			<div class="title">
				大赛管理
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg}</div>
				操作<hr>
				<div><input type=button onclick="javascript:window.location.href='<%=basePath%>admin/createScore'" value="成绩生成">
				<input type=button onclick="javascript:window.location.href='<%=basePath%>admin/createExcel'" value="excel生成">
				<input type=button onclick="javascript:window.location.href='<%=basePath%>admin/getCompetitionList'" value="大赛切换">
			</div>
			<hr>
				<div>
					<form action="admin/editCompetition" method="post">
					<input type="hidden" name="competition.competitionId" value="${competition.competitionId}">
						<br>参数<hr>
						<div>
							年份:<input type="text" name="competition.competitionYear"
								value="${competition.competitionYear}">
						</div>
						<div>
							注备:<input type="text" name="competition.note"
								value="${competition.note}">
						</div>
						<div>
							雄蟹肥满度参数:<input type="text" name="competition.varFatnessM"
								value="${competition.varFatnessM}">
						</div>
						<div>
							雄蟹体重参数:<input type="text" name="competition.varWeightM"
								value="${competition.varWeightM}">
						</div>
						<div>
							雄蟹肥满度标准差参数:<input type="text" name="competition.varMfatnessSd"
								value="${competition.varMfatnessSd}">
						</div>
						<div>
							雄蟹体重标准差参数:<input type="text" name="competition.varMweightSd"
								value="${competition.varMweightSd}">
						</div>						
						
						<br>
						<div>
							雄蟹肥满度参数:<input type="text" name="competition.varFatnessF"
								value="${competition.varFatnessF}">
						</div>						
						<div>
							雌蟹体重参数:<input type="text" name="competition.varWeightF"
								value="${competition.varWeightF}">
						</div>
						<div>
							雌蟹肥满度标准差参数:<input type="text" name="competition.varFfatnessSd"
								value="${competition.varFfatnessSd}">
						</div>
						<div>
							雌蟹体重标准差参数:<input type="text" name="competition.varFweightSd"
								value="${competition.varFweightSd}">
						</div>
						
						<br>设置<hr>
						<div>
							肥满度及蟹王蟹后结果:<input type="radio" name="competition.resultFatness" value="0"
								<c:if test="${competition.resultFatness==0 }" >checked</c:if> />不可见
						 	<input type="radio" name="competition.resultFatness" value="1"
								<c:if test="${competition.resultFatness==1 }">checked</c:if> />可见
						</div>
						<div>
							种质评比排名:<input type="radio" name="competition.resultQuality" value="0"
								<c:if test="${competition.resultQuality==0 }" >checked</c:if> />不可见
						 	<input type="radio" name="competition.resultQuality" value="1"
								<c:if test="${competition.resultQuality==1 }">checked</c:if> />可见
						</div>
						<div>
							口感评比排名:<input type="radio" name="competition.resultTaste" value="0"
								<c:if test="${competition.resultTaste==0 }" >checked</c:if> />不可见
						 	<input type="radio" name="competition.resultTaste" value="1"
								<c:if test="${competition.resultTaste==1 }">checked</c:if> />可见
						</div>
						
						<div>
							<input type="submit" value="保存"><a href="admin/admin.jsp">返回</a>
						</div>

					</form>
				</div>

			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />


</body>
</html>
