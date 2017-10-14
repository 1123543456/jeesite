<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>demo</title>
<meta name="decorator" content="default" />
</head>
<body>
	<table border="1 solid bule" cellspacing="0">
		<thead>
			<tr>
				<th width="25%">价格</th>
				<th width="25%">评论数</th>
				<th width="25%">星级</th>
				<th width="25%">库存</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>${almanac.price}</td>
				<td>${almanac.reviews}</td>
				<td>${almanac.stars}</td>
				<td>${almanac.store}</td>
			</tr>
		</tbody>
	</table>

</body>
</html>
