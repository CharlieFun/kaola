<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<jsp:include page="/nav.jsp"></jsp:include>

<!-- Page Content -->
<div id="page-wrapper">
    <div class="container-fluid">
        <div>
            <h1 class="page-header">修改商品</h1>
        </div>
        <div class="panel-heading">
        </div>
        <div class="row">
            <div class="col-lg-12">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        商品信息
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="dataTable_wrapper">
                            <form action="${pageContext.request.contextPath}/product/update" method="post"
                                  enctype="multipart/form-data">
                                <input type="hidden" name="id" value="${product.id}">
                                <label>商品标题</label>
                                <input class="form-control" name="title" value="${product.title}">
                                <label>商品简介</label>
                                <input class="form-control" name="summary" value="${product.summary}">
                                <label>商品价格</label>
                                <input class="form-control" name="price" value="${product.price}">
                                <label>详细描述</label>
                                <textarea class="form-control" name="detail">${product.detail}</textarea>
                                <label>上架状态</label>
                                <select name="status" class="form-control">
                                    <option value="true" selected>上架</option>
                                    <option value="false">下架</option>
                                </select>
                                <label>添加照片</label>
                                <input name="file" type="file">
                                <label></label>
                                <button type="submit"
                                        class="btn btn-primary form-control">提交修改
                                </button>
                            </form>
                        </div>
                        <!-- /.table-responsive -->
                    </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->
            </div>
            <!-- /.col-lg-12 -->
        </div>
    </div>

    <!-- /.container-fluid -->
</div>
</div>
<!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->
<jsp:include page="/bottom.jsp"></jsp:include>
