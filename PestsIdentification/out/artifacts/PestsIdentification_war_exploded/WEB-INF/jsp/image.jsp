<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>识别结果</title>
    <link rel="stylesheet" href="../../layui-v2.6.8/layui/css/layui.css">
    <script src="../../layui-v2.6.8/layui/layui.js"></script>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="layui-bg-gray">
<div class="layui-fluid">
    <div class="layui-row">
            <ul class="layui-nav" lay-filter="test">
                <li class="layui-nav-item"><a href="../../index.jsp">主页</a></li>
                <li class="layui-nav-item "><a href="${pageContext.request.contextPath}/allInsect?n=0">所有昆虫</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/allPest">查询害虫</a></li>
                <li class="layui-nav-item  layui-this"><a href="#">识别结果</a></li>
                <li class="layui-nav-item">
                    <a href="javascript:;">百科</a>
                    <dl class="layui-nav-child"> <!-- 二级菜单 -->
                        <dd><a href="${pageContext.request.contextPath}/allOrder">科信息</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/allFamily">目信息</a></dd>
                        <dd><a href="${pageContext.request.contextPath}/allGenus">属信息</a></dd>
                    </dl>
                </li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/image/toPredict">害虫识别</a></li>
            </ul>
        <div class="layui-col-xs12" style="float: left">
            <h1>
                <small class="layui-font-cyan">识别结果</small>
            </h1>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12">
            <form  action="${pageContext.request.contextPath}/searchInsect" style="float: right"
                   method="post">
                <div>
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入昆虫名称" name="name">
                        <span class="input-group-btn">
                        <button class="btn btn-default" type="submit">搜索</button>
                    </span>
                    </div>
                </div>
            </form>
        </div>
    </div>

</div>

<script>
    //注意：导航 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

        //…
    });
</script>
<div class="layui-fluid">
    <div class="layui-row">
        <div class="layui-col-xs12">
            <c:forEach var="info" items="${info}">
                <div class="page-header">
                    <h3>
                        <small>${info}</small>
                    </h3>
                </div>
            </c:forEach>
            <div class="layui-card">

                <div class="layui-card-body">
                    <img src="../saved/IMG.jpg" width="100%" height="50%">
                </div>

            </div>
        </div>


    </div>
</div>
</body>
</html>
