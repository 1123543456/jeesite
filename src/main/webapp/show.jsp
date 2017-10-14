<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>demo</title>
<meta name="decorator" content="default" />
<script type="text/javascript">
		function getUrl(){
			var url = document.getElementById("demo").value;
			location = "${ctx}/demo/demo?url="+url;
		}
</script>
</head>
<body>
	<table>
		<tr>
			<td>请输入链接：<input type="text" style="height:30px" value="" size="150" id="demo"/></td>
		</tr>
		<tr>
			<input type="button" onclick="getUrl();" value="查询"/>
		</tr>
	</table>
</body>
</html>
