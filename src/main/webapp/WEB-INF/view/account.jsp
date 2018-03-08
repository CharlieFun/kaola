<%--
  Created by IntelliJ IDEA.
  User: funstar
  Date: 2018/3/8
  Time: 下午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/head.jsp"></jsp:include>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>已购买的内容</h2>
    </div>
    <table class="m-table m-table-row n-table g-b3">
        <colgroup><col class="img"/><col/><col class="time"/><col class="price"/></colgroup>
        <thead>
        <tr><th>内容图片</th><th>内容名称</th><th>购买时间</th><th>购买价格</th></tr>
        </thead>
        <tbody>
        <tr>
            <td><a href="./show.html"><img src="http://nec.netease.com/img/s/1.jpg" alt=""></a></td>
            <td><h4><a href="./show.html">内容名称</a></h4></td>
            <td><span class="v-time">2016-03-12 12:12</span></td>
            <td><span class="v-unit">¥</span><span class="value">123.9</span></td>
        </tr>
        </tbody>
        <tfoot>
        <tr>
            <td colspan="3"><div class="total">总计：</div></td>
            <td><span class="v-unit">¥</span><span class="value">123.9</span></td>
        </tr>
        </tfoot>
    </table>
</div>
<jsp:include page="/foot.jsp"></jsp:include>