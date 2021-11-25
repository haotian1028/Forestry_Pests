<%--
  Created by IntelliJ IDEA.
  User: 张浩天
  Date: 2021/6/21
  Time: 22:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/updateOrder" method="post">
    <input type="text" value="${order.orderId}" name="id">
    <input type="text" value="${order.orderName}" name="name">
    <input type="submit" value="修改">
</form>
</body>
</html>
