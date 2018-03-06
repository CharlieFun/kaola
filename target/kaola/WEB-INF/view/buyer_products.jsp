<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/dist/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            买家你好，<span class="name">${sessionScope.username}</span>！<a href="${pageContext.request.contextPath}/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="${pageContext.request.contextPath}/product">首页</a></li>
            <li><a href="${pageContext.request.contextPath}/account">账务</a></li>
            <shiro:hasRole name="seller">
                <li><a href="./public.html">发布</a></li>
            </shiro:hasRole>
            <shiro:hasRole name="buyer">
                <li><a href="./public.html">购物车</a></li>
            </shiro:hasRole>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <div class="tab">
            <ul>
                <li class="z-sel"><a href="./index.html">所有内容</a></li>
                <li><a href="./index.html">未购买的内容</a></li>
            </ul>
        </div>
    </div>
    <div class="n-plist">
        <ul class="f-cb" id="plist">
            <c:forEach var="product" items="${products}">
                <li id="${product.id}">
                    <a href="${pageContext.request.contextPath}/product/show?id=${product.id}" class="link">
                        <div class="img"><img src="/product/showImg?id=${product.id}" alt=""></div>
                        <h3>${product.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${product.price}</span></div>
                    </a>
                </li>
            </c:forEach>
            <li id="p-1">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    <span class="had"><b>已售出</b></span>
                </a>
            </li>
            <li id="p-2">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                    <span class="had"><b>已购买</b></span>
                </a>
            </li>
            <li id="p-3">
                <a href="./show.html" class="link">
                    <div class="img"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></div>
                    <h3>内容</h3>
                    <div class="price"><span class="v-unit">¥</span><span class="v-value">123.9</span></div>
                </a>
                <span class="u-btn u-btn-normal u-btn-xs del" data-del="3">删除</span>
            </li>


        </ul>
    </div>
</div>
<div class="n-foot">
    <p>版权所有：网易云课堂<a href="http://mooc.study.163.com/smartSpec/detail/85002.htm">Java开发工程师(Web方向)</a>微专业团队</p>
</div>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pageIndex.js"></script>
</body>
</html>