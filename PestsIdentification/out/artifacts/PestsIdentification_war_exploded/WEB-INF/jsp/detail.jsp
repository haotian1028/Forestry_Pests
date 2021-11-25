<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>昆虫详情</title>
    <link rel="stylesheet" href="../../layui-v2.6.8/layui/css/layui.css">
    <script src="../../layui-v2.6.8/layui/layui.js"></script>
    <link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">

</head>
<body class="layui-bg-gray">
<div class="layui-fluid">
    <div class="layui-row">
            <ul class="layui-nav" lay-filter="test">
                <li class="layui-nav-item"><a href="../../index.jsp">主页</a></li>
                <li class="layui-nav-item"><a href="${pageContext.request.contextPath}/allInsect?n=0">所有昆虫</a></li>
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
                <li class="layui-nav-item layui-this"><a href="#">昆虫详情</a></li>
            </ul>
        <div class="layui-col-xs12" style="float: left">
            <h1>
                <small class="layui-font-cyan">${InsectInfo.insectName}详情</small>
            </h1>
        </div>
    </div>
    <div class="layui-row">
        <div class="layui-col-xs12" style="float: left">
            <form action="${pageContext.request.contextPath}/searchInsect" style="float: right"
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
            <div class="layui-card layui-col-md2" style="margin: 15px">
                <div class="layui-card-header">名称</div>
                <div class="layui-card-body">
                    ${InsectInfo.insectName}
                </div>
            </div>
            <div class="layui-card layui-col-md2" style="margin: 15px">
                <div class="layui-card-header">昆虫代码</div>
                <div class="layui-card-body">
                    ${InsectInfo.insectId}
                </div>
            </div>
            <div class="layui-card layui-col-md2 " style="margin: 15px">
                <div class="layui-card-header">拉丁学名</div>
                <div class="layui-card-body">
                    ${InsectInfo.scientificName}
                </div>
            </div>
            <div class="layui-col-md2" style="margin: 15px">
                <div class="layui-panel">
                    <div style="padding: 30px;">
                        <span><a href="${pageContext.request.contextPath}/image/toInsertEcoImage?id=${InsectInfo.insectId}">添加生态图片</a></span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md2" style="margin: 15px">
                <div class="layui-panel">
                    <div style="padding: 30px;">
                        <span><a href="${pageContext.request.contextPath}/image/ecoImage?id=${InsectInfo.insectId}">查询生态图片</a></span>
                    </div>
                </div>
            </div>
            <div class="layui-col-md2" style="margin: 15px">
                <div class="layui-panel">
                    <div style="padding: 30px;">
                        <span><a href="${pageContext.request.contextPath}/image/toInsertSpecimenImage?id=${InsectInfo.insectId}">添加标本图片</a></span>
                    </div>
                </div>
            </div>
        </div>
        <div class="layui-col-xs12">
            <div class="layui-collapse">
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">活跃作物</h2>
                    <div class="layui-colla-content layui-show">${InsectInfo.crop}</div>
                </div>
                <div class="layui-colla-item">
                    <h2 class="layui-colla-title">区域</h2>
                    <div class="layui-colla-content layui-show">${InsectInfo.area}</div>
                </div>
            </div>

            <script>
                //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
                layui.use('element', function(){
                    var element = layui.element;

                    //…
                });
            </script>
        </div>
        <div class="layui-col-xs12">
            <div class="layui-card">
                <div class="layui-card-header">标本图片</div>
                <div class="layui-card-body">
                    <div class=" layui-carousel" id="test1">
                        <div carousel-item>
                            <c:forEach var="image" items="${image}">
                                <div><img src="${image.imagePath}" width="100%" height="80%" alt=""
                                          onerror="this.src='../image/error.png'"></div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

</div>
<script>
    //注意：折叠面板 依赖 element 模块，否则无法进行功能性操作
    layui.use('element', function () {
        var element = layui.element;

        //…
    });
</script>

<script>
    layui.use('carousel', function () {
        var carousel = layui.carousel;
        //建造实例
        carousel.render({
            elem: '#test1',
            width: '80%', //设置容器宽度
            height: '80%',
            arrow: 'always', //始终显示箭头
            autoplay: true,
            //,anim: 'updown' //切换动画方式
        });
    });
</script>
</body>
</html>
