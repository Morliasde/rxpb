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

<title>口感评分</title>

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
				document.getElementById("scoreTasteF.scoreYgys").readOnly=true;	
				document.getElementById("scoreTasteF.scoreSys").readOnly=true;
				document.getElementById("scoreTasteF.scoreGhys").readOnly=true;
				document.getElementById("scoreTasteF.scoreXwxw").readOnly=true;
				document.getElementById("scoreTasteF.scoreGh").readOnly=true;
				document.getElementById("scoreTasteF.scoreFbjr").readOnly=true;
				document.getElementById("scoreTasteF.scoreBzjr").readOnly=true;				
				
				document.getElementById("scoreTasteF.scoreYgys").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreSys").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreGhys").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreXwxw").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreGh").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreFbjr").style.background="#e2e2e2";
				document.getElementById("scoreTasteF.scoreBzjr").style.background="#e2e2e2";
				
				document.getElementById("scoreTasteF.scoreFin").readOnly=false;
				document.getElementById("scoreTasteF.scoreFin").style.background="#FFFFFF";
				
				document.getElementById("scoreTasteM.scoreYgys").readOnly=true;	
				document.getElementById("scoreTasteM.scoreSys").readOnly=true;
				document.getElementById("scoreTasteM.scoreGhys").readOnly=true;
				document.getElementById("scoreTasteM.scoreXwxw").readOnly=true;
				document.getElementById("scoreTasteM.scoreGh").readOnly=true;
				document.getElementById("scoreTasteM.scoreFbjr").readOnly=true;
				document.getElementById("scoreTasteM.scoreBzjr").readOnly=true;				
				
				document.getElementById("scoreTasteM.scoreYgys").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreSys").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreGhys").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreXwxw").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreGh").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreFbjr").style.background="#e2e2e2";
				document.getElementById("scoreTasteM.scoreBzjr").style.background="#e2e2e2";
				
				document.getElementById("scoreTasteM.scoreFin").readOnly=false;
				document.getElementById("scoreTasteM.scoreFin").style.background="#FFFFFF";
			}else{
			
				document.getElementById("scoreTasteF.scoreYgys").readOnly=false;	
				document.getElementById("scoreTasteF.scoreSys").readOnly=false;
				document.getElementById("scoreTasteF.scoreGhys").readOnly=false;
				document.getElementById("scoreTasteF.scoreXwxw").readOnly=false;
				document.getElementById("scoreTasteF.scoreGh").readOnly=false;
				document.getElementById("scoreTasteF.scoreFbjr").readOnly=false;
				document.getElementById("scoreTasteF.scoreBzjr").readOnly=false;				
				
				document.getElementById("scoreTasteF.scoreYgys").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreSys").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreGhys").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreXwxw").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreGh").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreFbjr").style.background="#FFFFFF";
				document.getElementById("scoreTasteF.scoreBzjr").style.background="#FFFFFF";
				
				document.getElementById("scoreTasteF.scoreFin").readOnly=true;
				document.getElementById("scoreTasteF.scoreFin").style.background="#e2e2e2";
				
				document.getElementById("scoreTasteM.scoreYgys").readOnly=false;	
				document.getElementById("scoreTasteM.scoreSys").readOnly=false;
				document.getElementById("scoreTasteM.scoreGhys").readOnly=false;
				document.getElementById("scoreTasteM.scoreXwxw").readOnly=false;
				document.getElementById("scoreTasteM.scoreGh").readOnly=false;
				document.getElementById("scoreTasteM.scoreFbjr").readOnly=false;
				document.getElementById("scoreTasteM.scoreBzjr").readOnly=false;				
				
				document.getElementById("scoreTasteM.scoreYgys").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreSys").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreGhys").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreXwxw").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreGh").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreFbjr").style.background="#FFFFFF";
				document.getElementById("scoreTasteM.scoreBzjr").style.background="#FFFFFF";
				
				document.getElementById("scoreTasteM.scoreFin").readOnly=true;
				document.getElementById("scoreTasteM.scoreFin").style.background="#e2e2e2";			
			}
						
		}
		
		function getFinF(){
		
			if(document.getElementById("scoreTasteF.scoreFin").readOnly==true){
			var fin = 0;
			fin+=parseInt(document.getElementById("scoreTasteF.scoreYgys").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreSys").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreGhys").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreXwxw").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreGh").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreFbjr").value);
			fin+=parseInt(document.getElementById("scoreTasteF.scoreBzjr").value);
			
			document.getElementById("scoreTasteF.scoreFin").value=fin/7;
			}
			
		}
		
		function getFinM(){
			if(document.getElementById("scoreTasteM.scoreFin").readOnly==true){
			var fin = 0;
			fin+=parseInt(document.getElementById("scoreTasteM.scoreYgys").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreSys").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreGhys").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreXwxw").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreGh").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreFbjr").value);
			fin+=parseInt(document.getElementById("scoreTasteM.scoreBzjr").value);
			
			document.getElementById("scoreTasteM.scoreFin").value=fin/7;
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
				口感评分
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

		<form action="judge/saveTasteByJudge" method="post">
		<input type="hidden"name="groupId" value="${groupId}">
		<input type="hidden"name="groupQueue" value="${groupQueue}">
		<input type="hidden"name="scoreTasteF.scoreId" value="${scoreTasteF.scoreId}">
			<div>
		<input type="hidden"name="scoreTasteM.scoreId" value="${scoreTasteF.scoreId}">
				<div>
					<table border="1">
						<tr>雌</tr>
						<tr>
							<th>蟹盖颜色</th>
							<th>腮颜色</th>
							<th>膏、黄颜色</th>
							<th>腥味、香味</th>
							<th>膏、黄</th>
							<th>腹部肌肉</th>
							<th>第二，三步足肌肉</th>
							<th>得分</th>
						</tr>
						
							<tr>
								<td><input id="scoreTasteF.scoreYgys" type="text" name="scoreTasteF.scoreYgys" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreYgys}"></td>

								<td><input id="scoreTasteF.scoreSys" type="text" name="scoreTasteF.scoreSys" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreSys}"></td>

								<td><input id="scoreTasteF.scoreGhys" type="text" name="scoreTasteF.scoreGhys" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreGhys}"></td>

								<td><input id="scoreTasteF.scoreXwxw" type="text" name="scoreTasteF.scoreXwxw" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreXwxw}"></td>

								<td><input id="scoreTasteF.scoreGh" type="text" name="scoreTasteF.scoreGh" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreGh}"></td>
									
								<td><input id="scoreTasteF.scoreFbjr" type="text" name="scoreTasteF.scoreFbjr" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreFbjr}"></td>
								
								<td><input id="scoreTasteF.scoreBzjr" type="text" name="scoreTasteF.scoreBzjr" placeholder="请输入" readonly="readonly" onblur="getFinF()" style="background: #e2e2e2"
									value="${scoreTasteF.scoreBzjr}"></td>

								<td><input id="scoreTasteF.scoreFin" type="text" name="scoreTasteF.scoreFin" placeholder="请输入" 
									value="${scoreTasteF.scoreFin}"></td>
								
							</tr>
					</table>

				</div>

				<div>

					<table border="1">
						<tr>雄
						</tr>
						<tr>
							<th>蟹盖颜色</th>
							<th>腮颜色</th>
							<th>膏、黄颜色</th>
							<th>腥味、香味</th>
							<th>膏、黄</th>
							<th>腹部肌肉</th>
							<th>第二，三步足肌肉</th>
							<th>得分</th>
						</tr>					
							<tr>
								<td><input id="scoreTasteM.scoreYgys" type="text" name="scoreTasteM.scoreYgys" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreYgys}"></td>

								<td><input id="scoreTasteM.scoreSys" type="text" name="scoreTasteM.scoreSys" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreSys}"></td>

								<td><input id="scoreTasteM.scoreGhys" type="text" name="scoreTasteM.scoreGhys" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreGhys}"></td>

								<td><input id="scoreTasteM.scoreXwxw" type="text" name="scoreTasteM.scoreXwxw" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreXwxw}"></td>

								<td><input id="scoreTasteM.scoreGh" type="text" name="scoreTasteM.scoreGh" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreGh}"></td>
									
								<td><input id="scoreTasteM.scoreFbjr" type="text" name="scoreTasteM.scoreFbjr" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreFbjr}"></td>
								
								<td><input id="scoreTasteM.scoreBzjr" type="text" name="scoreTasteM.scoreBzjr" placeholder="请输入" readonly="readonly" onblur="getFinM()" style="background: #e2e2e2"
									value="${scoreTasteM.scoreBzjr}"></td>


								<td><input id="scoreTasteM.scoreFin" type="text" name="scoreTasteM.scoreFin" placeholder="请输入"
									value="${scoreTasteM.scoreFin}"></td>
							
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
