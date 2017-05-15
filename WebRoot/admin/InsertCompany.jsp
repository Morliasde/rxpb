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

<title>批量添加参选单位</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript">
		var i=1;
		
		function addInput ()
    {
        var input = document.createElement('input');  //创建input节点
		input.setAttribute('type', 'text');  //定义类型是文本输入
		
		var companyNum = document.getElementById('companyNum').value;
		
		
		while(true){
			if(companyNum==0){
				break;
			}
			var div = document.createElement('div');
			var br = document.createElement('br');
			var input = document.createElement('input');  //创建input节点
			
			input.setAttribute('type', 'text');  //定义类型是文本输入
			input.setAttribute('name',"companyName"+i );
			input.setAttribute('placeholder',"请输入参选单位"+i);
			input.setAttribute('style','width:300px');		
			
			div.appendChild(input);
			
			document.getElementById('CompanyNames').appendChild(div); //添加到form中显示		
			companyNum=companyNum-1;
			i=i+1;
			
		}
		
		
    }
	</script>

</head>

<body>
	<jsp:include page="/module/top.jsp" />

	<div class="main">
		<div class="container">
			<jsp:include page="/module/optionAdmin.jsp" />

			<div class="title">
				批量添加参选单位
				<hr>
			</div>

			<div class="article">
				<div class="warning">${msg }</div>
				<a href="admin/getCompanyList">返回 </a><br>
				<strong>注意:空着的参选单位不会录入数据库</strong>

				


				<hr>
				
					<div>						
							<div>
								添加数目：<input id="companyNum" type="text" name="crabAmount" value="">
							
								<input type="button" value="确定" onclick="addInput();" >
							</div>
						
					</div>
			
			<hr>
				<form action="admin/insertCompany" method="post">
					<div id="CompanyNames">
					
					
					</div>
					<div>
						<input type="submit" value="添加">
					</div>

				</form>


			</div>

		</div>
	</div>
	<jsp:include page="/module/footer.jsp" />


</body>
</html>
