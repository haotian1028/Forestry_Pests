<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${genusName}昆虫列表</title>
    <!-- 引入 Bootstrap -->
    <link rel="stylesheet" href="../../layui-v2.6.8/layui/css/layui.css">
    <script src="../../layui-v2.6.8/layui/layui.js"></script>

    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="layui-bg-gray">
    <div class="layui-fluid">
        <div class="layui-row">
                <ul class="layui-nav" lay-filter="test">
                    <li class="layui-nav-item"><a href="../../index.jsp">主页</a></li>
                    <li class="layui-nav-item "><a href="${pageContext.request.contextPath}/allInsect?n=0">所有昆虫</a></li>
                    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/allPest">查询害虫</a></li>
                    <li class="layui-nav-item">
                        <a href="javascript:;">百科</a>
                        <dl class="layui-nav-child"> <!-- 二级菜单 -->
                            <dd><a href="${pageContext.request.contextPath}/allOrder">科信息</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/allFamily">目信息</a></dd>
                            <dd><a href="${pageContext.request.contextPath}/allGenus">属信息</a></dd>
                        </dl>
                    </li>
                    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/image/toPredict">害虫识别</a></li>
                    <li class="layui-nav-item  layui-this"><a href="#">${genusName}昆虫列表</a></li>
                    <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/allGenus">返回</a></li>
                </ul>
            <div class="layui-col-xs12" style="float: left">
                <h1>
                    <small class="layui-font-cyan">${genusName}昆虫列表</small>
                </h1>
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
                <table class="table" lay-filter="demo">
                    <thead>
                    <tr>
                        <th lay-data="{field:'username1', width:390}">昆虫代码</th>
                        <th lay-data="{field:'username2', width:390}">昆虫名称</th>
                        <th lay-data="{field:'username4', width:390}"></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="list" items="${list}">
                        <tr>
                            <td>${list.insectId}</td>
                            <td>${list.insectName}</td>
                            <td>
                                <a href="${pageContext.request.contextPath}/image/toDetail?id=${list.insectId}">详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

        </div>
    </div>
</div>
<script>
    var table = layui.table;
    //转换静态表格
    table.init('demo', {
        height: 450 //设置高度
        , limit: 10 //注意：请务必确保 limit 参数（默认：10）是与你服务端限定的数据条数一致
        //支持所有基础参数
        , col: [[{field: 'username1', title: '名称', sort: true}]]
        , skin: 'row' //行边框风格
        , even: true //开启隔行背景
        , page: true
    });
</script>
</body>
</html>
