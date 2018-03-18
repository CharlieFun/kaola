<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<jsp:include page="/head.jsp"></jsp:include>
<div class="g-doc">
    <div class="n-result">
        <h3>购买成功！</h3>
        <p>
            <%--<a href="./show.html">[查看内容]</a>--%>
            <a href="${cp}/product">[返回首页]</a>
        </p>
    </div>
</div>
<jsp:include page="/foot.jsp"></jsp:include>
