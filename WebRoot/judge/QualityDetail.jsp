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

<title>种质评分</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		function changeType(type){
			if(type==1){
				document.getElementById("scoreQualityF.scoreBts").readOnly=true;	
				document.getElementById("scoreQualityF.scoreFts").readOnly=true;
				document.getElementById("scoreQualityF.scoreEc").readOnly=true;
				document.getElementById("scoreQualityF.scoreDscc").readOnly=true;
				document.getElementById("scoreQualityF.scoreBbyzt").readOnly=true;				
				
				document.getElementById("scoreQualityF.scoreBts").style.background="#e2e2e2";
				document.getElementById("scoreQualityF.scoreFts").style.background="#e2e2e2";
				document.getElementById("scoreQualityF.scoreEc").style.background="#e2e2e2";
				document.getElementById("scoreQualityF.scoreDscc").style.background="#e2e2e2";
				document.getElementById("scoreQualityF.scoreBbyzt").style.background="#e2e2e2";
				
				document.getElementById("scoreQualityF.scoreFin").readOnly=false;
				document.getElementById("scoreQualityF.scoreFin").style.background="#FFFFFF";
				
				document.getElementById("scoreQualityM.scoreBts").readOnly=true;
				document.getElementById("scoreQualityM.scoreFts").readOnly=true;
				document.getElementById("scoreQualityM.scoreEc").readOnly=true;
				document.getElementById("scoreQualityM.scoreDscc").readOnly=true;
				document.getElementById("scoreQualityM.scoreBbyzt").readOnly=true;
				
				document.getElementById("scoreQualityM.scoreBts").style.background="#e2e2e2";
				document.getElementById("scoreQualityM.scoreFts").style.background="#e2e2e2";
				document.getElementById("scoreQualityM.scoreEc").style.background="#e2e2e2";
				document.getElementById("scoreQualityM.scoreDscc").style.background="#e2e2e2";
				document.getElementById("scoreQualityM.scoreBbyzt").style.background="#e2e2e2";
				
				document.getElementById("scoreQualityM.scoreFin").readOnly=false;
				document.getElementById("scoreQualityM.scoreFin").style.background="#FFFFFF";
			}else{
			
				document.getElementById("scoreQualityF.scoreBts").readOnly=false;
				document.getElementById("scoreQualityF.scoreFts").readOnly=false;
				document.getElementById("scoreQualityF.scoreEc").readOnly=false;
				document.getElementById("scoreQualityF.scoreDscc").readOnly=false;
				document.getElementById("scoreQualityF.scoreBbyzt").readOnly=false;
				
				document.getElementById("scoreQualityF.scoreBts").style.background="#FFFFFF";
				document.getElementById("scoreQualityF.scoreFts").style.background="#FFFFFF";
				document.getElementById("scoreQualityF.scoreEc").style.background="#FFFFFF";
				document.getElementById("scoreQualityF.scoreDscc").style.background="#FFFFFF";
				document.getElementById("scoreQualityF.scoreBbyzt").style.background="#FFFFFF";
				
				document.getElementById("scoreQualityF.scoreFin").readOnly=true;
				document.getElementById("scoreQualityF.scoreFin").style.background="#e2e2e2";
				
				document.getElementById("scoreQualityM.scoreBts").readOnly=false;
				document.getElementById("scoreQualityM.scoreFts").readOnly=false;
				document.getElementById("scoreQualityM.scoreEc").readOnly=false;
				document.getElementById("scoreQualityM.scoreDscc").readOnly=false;
				document.getElementById("scoreQualityM.scoreBbyzt").readOnly=false;
				
				document.getElementById("scoreQualityM.scoreBts").style.background="#FFFFFF";
				document.getElementById("scoreQualityM.scoreFts").style.background="#FFFFFF";
				document.getElementById("scoreQualityM.scoreEc").style.background="#FFFFFF";
				document.getElementById("scoreQualityM.scoreDscc").style.background="#FFFFFF";
				document.getElementById("scoreQualityM.scoreBbyzt").style.background="#FFFFFF";
				
				document.getElementById("scoreQualityM.scoreFin").readOnly=true;
				document.getElementById("scoreQualityM.scoreFin").style.background="#e2e2e2";			
			}
						
		}
		
		function getFinF(){
		
			if(document.getElementById("scoreQualityF.scoreFin").readOnly==true){
			var fin = 0;
			fin+=parseInt(document.getElementById("scoreQualityF.scoreBts").value);
			fin+=parseInt(document.getElementById("scoreQualityF.scoreFts").value);
			fin+=parseInt(document.getElementById("scoreQualityF.scoreEc").value);
			fin+=parseInt(document.getElementById("scoreQualityF.scoreDscc").value);
			fin+=parseInt(document.getElementById("scoreQualityF.scoreBbyzt").value);
			
			document.getElementById("scoreQualityF.scoreFin").value=fin/5;
			}
			
		}
		
		function getFinM(){
			if(document.getElementById("scoreQualityM.scoreFin").readOnly==true){
			var fin = 0;
			fin+=parseInt(document.getElementById("scoreQualityM.scoreBts").value);
			fin+=parseInt(document.getElementById("scoreQualityM.scoreFts").value);
			fin+=parseInt(document.getElementById("scoreQualityM.scoreEc").value);
			fin+=parseInt(document.getElementById("scoreQualityM.scoreDscc").value);
			fin+=parseInt(document.getElementById("scoreQualityM.scoreBbyzt").value);
			
			document.getElementById("scoreQualityM.scoreFin").value=fin/5;
			}
		}
		
		
		
	</script>

</head>

<body>

<jsp:include page="/module/top.jsp" />
  
    <div class="main">
		<div class="container">
		
			<jsp:include page="/module/optionJudge.jsp" />


			<div class="title">
				种质评分
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
				<a href="judge/getGroupList">返回</a>

	<div>组号：${groupQueue}</div>
	<div>评分模式：
	<input type="radio" name="scoreType" onclick="changeType(1)" checked="checked" >输入总分
	<input type="radio" name="scoreType" onclick="changeType(0)" >输入小分
	</div>
	<div>

		<form action="judge/saveQualityByJudge" method="post">
		<input type="hidden"name="groupId" value="${groupId}">
		<input type="hidden"name="groupQueue" value="${groupQueue}">
		<input type="hidden"name="scoreQualityF.scoreId" value="${scoreQualityF.scoreId}">
			<div>
		<input type="hidden"name="scoreQualityM.scoreId" value="${scoreQualityF.scoreId}">
				<div>
					<table border="1">
						<tr>雌
						</tr>
						<tr>
							<th>体色(背)</th>
							<th>体色(腹)</th>
							<th>额齿</th>
							<th>第4侧齿</th>
							<th>北部疣状突</th>
							<th>得分</th>
						</tr>
						
							<tr>
								<td><input id="scoreQualityF.scoreBts" type="text" name="scoreQualityF.scoreBts" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreQualityF.scoreBts}"></td>

								<td><input id="scoreQualityF.scoreFts" type="text" name="scoreQualityF.scoreFts" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreQualityF.scoreFts}"></td>

								<td><input id="scoreQualityF.scoreEc" type="text" name="scoreQualityF.scoreEc" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreQualityF.scoreEc}"></td>

								<td><input id="scoreQualityF.scoreDscc" type="text" name="scoreQualityF.scoreDscc" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreQualityF.scoreDscc}"></td>

								<td><input id="scoreQualityF.scoreBbyzt" type="text" name="scoreQualityF.scoreBbyzt" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreQualityF.scoreBbyzt}"></td>

								<td><input id="scoreQualityF.scoreFin" type="text" name="scoreQualityF.scoreFin" placeholder="请输入" 
									value="${scoreQualityF.scoreFin}"></td>
								
							</tr>
					</table>

				</div>

				<div>

					<table border="1">
						<tr>雄
						</tr>
						<tr>
							<th>体色(背)</th>
							<th>体色(腹)</th>
							<th>额齿</th>
							<th>第4侧齿</th>
							<th>北部疣状突</th>
							<th>得分</th>
						</tr>						
							<tr>
								<td><input id="scoreQualityM.scoreBts" type="text" name="scoreQualityM.scoreBts" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreQualityM.scoreBts}"></td>

								<td><input id="scoreQualityM.scoreFts" type="text" name="scoreQualityM.scoreFts" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreQualityM.scoreFts}"></td>

								<td><input id="scoreQualityM.scoreEc" type="text" name="scoreQualityM.scoreEc" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreQualityM.scoreEc}"></td>

								<td><input id="scoreQualityM.scoreDscc" type="text" name="scoreQualityM.scoreDscc" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreQualityM.scoreDscc}"></td>

								<td><input id="scoreQualityM.scoreBbyzt" type="text" name="scoreQualityM.scoreBbyzt" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreQualityM.scoreBbyzt}"></td>

								<td><input id="scoreQualityM.scoreFin" type="text" name="scoreQualityM.scoreFin" placeholder="请输入"
									value="${scoreQualityM.scoreFin}"></td>
							
							</tr>
					</table>
				</div>

				<div>
					<input type="submit" value="保存">
				</div>
			</div>
		</form>
	</div>
			</div>
			

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />


</body>
</html>
