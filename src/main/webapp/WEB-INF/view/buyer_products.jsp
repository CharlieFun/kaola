<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="/head.jsp"></jsp:include>
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
                    <a href="${pageContext.request.contextPath}/product/show?id=${product.id}&lastBuyPrice=${product.lastBuyPrice}" class="link">
                        <div class="img"><img src="/product/showImg?id=${product.id}" alt=""></div>
                        <h3>${product.title}</h3>
                        <div class="price"><span class="v-unit">¥</span><span class="v-value">${product.price}</span></div>
                        <c:if test="${product.lastBuyPrice > 0}">
                            <span class="had"><b>已购买</b></span>
                        </c:if>
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
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pageIndex.js"></script>
<jsp:include page="/foot.jsp"></jsp:include>
