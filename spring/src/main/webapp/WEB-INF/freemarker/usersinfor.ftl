<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<head>
  <title>全部用户信息</title>
</head>
<body>
	<#list usersList as user>
		<P>用户ID  ：${user.userId}</p>
		<P>用户姓名：${user.userName}</p>
		<P>账户余额：${user.balance}</p>
	</#list>
</body>
</html>