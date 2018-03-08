<%--
  Created by IntelliJ IDEA.
  User: funstar
  Date: 2018/3/8
  Time: 下午4:04
  To change this template use File | Settings | File Templates.
--%>
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
