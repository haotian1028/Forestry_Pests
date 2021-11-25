
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>首页</title>
    <script src="static/js/jquery-3.6.0.js"></script>
    <script src="static/js/jump.jsp"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">


</head>
<body class="layui-bg-gray" >



<div  class="layui-fluid" style="margin-top: 10%">
    <div class="layui-row">
        <div class="layui-col-md10">
            <div class="jumbotron">
                <h1>欢迎使用昆虫识别系统！</h1>
                <p><a class="btn btn-primary btn-lg" href="${pageContext.request.contextPath}/allInsect?n=0" role="button">开始使用!</a></p>
            </div>
        </div>
    </div>
</div>

</body>
</html>
