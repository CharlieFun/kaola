<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="${cp}/dist/css/style.css"/>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            <shiro:hasRole name="seller">
                卖家
            </shiro:hasRole>
            <shiro:hasRole name="buyer">
                买家
            </shiro:hasRole>
            <span class="name">${sessionScope.username}</span>，您好！<a href="${cp}/logout">[退出]</a>
        </div>
        <ul class="nav">
            <shiro:hasRole name="buyer">
                <li><a href="${cp}/product">首页</a></li>
                <li><a href="${cp}/account">财务</a></li>
                <li><a href="${cp}/shoppingCart">购物车</a></li>
            </shiro:hasRole>
            <shiro:hasRole name="seller">
                <li><a href="${cp}/seller">首页</a></li>
                <li><a href="${cp}/product/addView">发布</a></li>
            </shiro:hasRole>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="n-result">
        <h3>对不起，您没有此权限！</h3>
        <p>
            <%--<a href="./show.html">[查看内容]</a>--%>
            <shiro:hasRole name="buyer">
                <a href="${cp}/product">[返回首页]</a>
            </shiro:hasRole>
            <shiro:hasRole name="seller">
                <a href="${cp}/seller">[返回首页]</a>
            </shiro:hasRole>

        </p>
    </div>
</div>
<jsp:include page="/foot.jsp"></jsp:include>
