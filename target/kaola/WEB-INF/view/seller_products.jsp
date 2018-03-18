<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/nav.jsp"></jsp:include>
<link href="${pageContext.request.contextPath}/dist/css/ui.css"
      rel="stylesheet" type="text/css">
<script src=""></script>
<!-- Page Content -->
<div id="page-wrapper">

    <div class="container-fluid">
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <a href="${pageContext.request.contextPath}/product/addView" class="btn btn-primary"
                           role="button">发布</a>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <table class="table table-striped table-bordered table-hover" id="dataTables-example">

                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>缩略图</th>
                                    <th>商品名称</th>
                                    <th>描述</th>
                                    <th>单价</th>
                                    <th>发布状态</th>
                                    <th>售出状态</th>
                                    <th>售出数量</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <%--<c:forEach var="className" items="${entrty.value}">--%>
                                <c:forEach var="product" items="${products}">
                                    <tr>
                                        <td>${product.id}</td>
                                        <td>
                                            <img class="smallImg" src="/product/showImg?id=${product.id}">
                                        </td>
                                        <td>${product.title}</td>
                                        <td>${product.summary}</td>
                                        <td>${product.price}</td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.status}">
                                                    已发布
                                                </c:when>
                                                <c:otherwise>
                                                    已下架
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>
                                            <c:choose>
                                                <c:when test="${product.haveBuy}">
                                                    已售出
                                                </c:when>
                                                <c:otherwise>
                                                    未售出
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                        <td>${product.totalBuyNum}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/product/updateView?id=${product.id}"
                                               class="btn btn-primary" name="update">修改</a>
                                            <c:choose>
                                                <c:when test="${product.haveBuy}">
                                                    <button class="btn btn-danger" disabled="disabled">删除</button>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="${pageContext.request.contextPath}/product/delete?id=${product.id}"
                                                       class="btn btn-danger" name="delete" id="${product.id}"
                                                       onclick="return confirm('是否要删除该商品？')">删除</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </td>
                                    </tr>
                                </c:forEach>
                                <%--</c:forEach>--%>
                                </tbody>
                            </table>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <%--</c:forEach>--%>

    </div>
    <!-- /.container-fluid -->
</div>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->

<jsp:include page="/bottom.jsp"></jsp:include>

