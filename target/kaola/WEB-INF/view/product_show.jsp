<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<jsp:include page="/head.jsp"></jsp:include>
<div class="g-doc">
    <div class="n-show f-cb" id="showContent">
        <div class="img"><img src="/product/showImg?id=${product.id}" alt=""></div>
        <div class="cnt">
            <h2>名称&nbsp;</h2>
            <h2>${product.title}</h2>
            <p class="summary">摘要&nbsp;</p>
            <p class="summary">${product.summary}</p>
            <div class="price">
                <span class="v-unit">¥</span><span class="v-value">${product.price}</span>
            </div>
            <div class="num">
                数量
                <span id="minusNum" class="lessNum"><a>-</a></span>
                <span id="allNum" class="totalNum">1</span>
                <span id="plusNum" class="moreNum"><a>+</a></span>
            </div>
            <div class="oprt f-cb">
                <button class="u-btn u-btn-primary" data-buy="1" name="item" id="${product.id}">购 买</button>
                <span class="u-btn u-btn-primary z-dis">已购买</span>
                <span class="buyprice">当时购买价格：¥123.9</span>
                <%--<a href="./edit.html" class="u-btn u-btn-primary">编 辑</a>--%>
            </div>
        </div>
    </div>
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>详细信息</h2>
    </div>
    <div class="n-detail">
        ${product.detail}
    </div>
</div>
<%-- script 节点不要使用自结束 --%>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/pageShow.js"></script>
<script>
    var $ = function (id) {
        return document.getElementById(id);
    };

    $('minusNum').onclick = function (e) {
        e = window.event || e;
        o = e.srcElement || e.target;
        var num = $('allNum').textContent;
        if (num > 0) {
            num--;
            $('allNum').innerHTML = num;
        } else {
            alert("您没有购买任何商品");
        }
    };

    $('plusNum').onclick = function (e) {
        e = window.event || e;
        o = e.srcElement || e.target;
        var num = $('allNum').textContent;
        num++;
        $('allNum').innerHTML = num;
    };

</script>
<jsp:include page="/foot.jsp"></jsp:include>