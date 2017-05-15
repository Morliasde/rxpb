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

<title>得分详情</title>

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
			第${groupQueue}组,${companyName}分数详情
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
				<a href="admin/company!getCompanyList">返回 </a><br>
				<strong>金蟹奖、优质蟹奖评分</strong>
				<hr>
				
				
		
		<table border="1">
			<tr>
				<th>雌</th>
				<th>标识</th>
				<th>体重（g）</th>
				<th>壳长（cm）</th>
				<th>肥满度</th>
			</tr>
			<c:forEach var="crabInfo" items="${clistF}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<td>雌${order.count}</td>
					<td>${crabInfo.crabLabel}</td>
					<td>${crabInfo.crabWeight}</td>
					<td>${crabInfo.crabLength}</td>
					<td>${crabInfo.crabFatness}</td>					
				</tr>			
			</c:forEach>			
			</table>
			<div>雌蟹得分:${finScoreF} </div>
			<div>雌蟹数达标:<c:if test="${clistF.size()>=10 }">达标</c:if> <c:if test="${clistF.size()<10 }">不达标</c:if></div>
			
			<table border="1">
			<tr>
				<th>雄</th>
				<th>标识</th>
				<th>体重（g）</th>
				<th>壳长（cm）</th>
				<th>肥满度</th>
			</tr>
			<c:forEach var="crabInfo" items="${clistM}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<!-- <td>${crabInfoF.crabId}</td> -->
					<td>雄${order.count}</td>
					<td>${crabInfo.crabLabel}</td>
					<td>${crabInfo.crabWeight}</td>
					<td>${crabInfo.crabLength}</td>
					<td>${crabInfo.crabFatness}</td>	
				</tr>
			</c:forEach>			
			</table>
			<div>雄蟹得分:${finScoreM} </div>
			<div>雄蟹数达标:<c:if test="${clistM.size()>=10 }">达标</c:if> <c:if test="${clistM.size()<10 }">不达标</c:if></div>
			<div>雌雄平均:
			<script type="text/javascript">
			var a=${finScoreM};
			var b=${finScoreF};
			document.write((a+b)/2);			
			</script></div>
			<hr>
	
	
	<strong>种质得分</strong>
				<hr>
	
		
		<table border="1">
			<tr>
				<th>雌</th>
				<th>体色(背)</th>
				<th>体色(腹)</th>
				<th>额齿</th>
				<th>第4侧齿</th>
				<th>北部疣状突</th>
				<th>得分</th>
			</tr>
			<c:forEach var="qualityScore" items="${cqListF}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<td>专家${order.count}</td>
					<td>${qualityScore.scoreBts}</td>
					<td>${qualityScore.scoreFts}</td>
					<td>${qualityScore.scoreEc}</td>
					<td>${qualityScore.scoreDscc}</td>
					<td>${qualityScore.scoreBbyzt}</td>
					<td>${qualityScore.scoreFin}</td>
				</tr>
			</c:forEach>			
			</table>
			<div>去掉一个最低分，去掉一个最高分平均:${finQScoreF} </div>
			
			<table border="1">
			<tr>
				<th>雄</th>
				<th>体色(背)</th>
				<th>体色(腹)</th>
				<th>额齿</th>
				<th>第4侧齿</th>
				<th>北部疣状突</th>
				<th>得分</th>
			</tr>
			<c:forEach var="qualityScore" items="${cqListM}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<!-- <td>${crabInfoF.crabId}</td> -->
					<td>专家${order.count}</td>
					<td>${qualityScore.scoreBts}</td>
					<td>${qualityScore.scoreFts}</td>
					<td>${qualityScore.scoreEc}</td>
					<td>${qualityScore.scoreDscc}</td>
					<td>${qualityScore.scoreBbyzt}</td>
					<td>${qualityScore.scoreFin}</td>
				</tr>
			</c:forEach>			
			</table>
			<tr>去掉一个最低分，去掉一个最高分平均:${finQScoreM} </tr>
			<hr>
	
	
	
	<strong>种质得分</strong>
				<hr>
		
		<table border="1">
			<tr>
				<th>雌</th>
				<th>蟹盖颜色</th>
				<th>腮颜色</th>
				<th>膏、黄颜色</th>
				<th>腥味、香味</th>
				<th>膏、黄</th>
				<th>腹部肌肉</th>
				<th>第二，三步足肌肉</th>
				<th>得分</th>
			</tr>
			<c:forEach var="tasteScore" items="${ctListF}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<td>专家${order.count}</td>
					<td>${tasteScore.scoreYgys}</td>
					<td>${tasteScore.scoreSys}</td>
					<td>${tasteScore.scoreGhys}</td>
					<td>${tasteScore.scoreXwxw}</td>
					<td>${tasteScore.scoreGh}</td>
					<td>${tasteScore.scoreFbjr}</td>
					<td>${tasteScore.scoreBzjr}</td>
					<td>${tasteScore.scoreFin}</td>
				</tr>


				
			</c:forEach>			
			</table>
			<div>去掉一个最低分，去掉一个最高分平均:${finTScoreF} </div>
			
			<table border="1">
			<tr>
				<th>雄</th>
				<th>蟹盖颜色</th>
				<th>腮颜色</th>
				<th>膏、黄颜色</th>
				<th>腥味、香味</th>
				<th>膏、黄</th>
				<th>腹部肌肉</th>
				<th>第二，三步足肌肉</th>
				<th>得分</th>
			</tr>
			<c:forEach var="tasteScore" items="${ctListM}"
				varStatus="order">
				<tr <c:if test="${order.count%2==0 }">bgcolor="cacaca"</c:if>>
					<!-- <td>${crabInfoF.crabId}</td> -->
					<td>专家${order.count}</td>
					<td>${tasteScore.scoreYgys}</td>
					<td>${tasteScore.scoreSys}</td>
					<td>${tasteScore.scoreGhys}</td>
					<td>${tasteScore.scoreXwxw}</td>
					<td>${tasteScore.scoreGh}</td>
					<td>${tasteScore.scoreFbjr}</td>
					<td>${tasteScore.scoreBzjr}</td>
					<td>${tasteScore.scoreFin}</td>
				</tr>
			</c:forEach>			
			</table>
			<div>去掉一个最低分，去掉一个最高分平均:${finTScoreM} </div>
			<hr>
	
			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />


	
	



</body>
</html>
