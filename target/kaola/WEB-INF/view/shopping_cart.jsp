<%--
  Created by IntelliJ IDEA.
  User: funstar
  Date: 2018/3/8
  Time: 下午4:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>java</title>
    <link rel="stylesheet" href="${cp}/dist/css/style.css"/>
    <script src="${cp}/js/jquery-3.1.0.min.js" type="text/javascript"></script>
</head>
<body>
<div class="n-support">请使用Chrome、Safari等webkit内核的浏览器！</div>
<div class="n-head">
    <div class="g-doc f-cb">
        <div class="user">
            买家你好，<span class="name">${sessionScope.username}</span>！<a href="${cp}/logout">[退出]</a>
        </div>
        <ul class="nav">
            <li><a href="${cp}/product">首页</a></li>
            <li><a href="${cp}/account">账务</a></li>
            <shiro:hasRole name="seller">
                <li><a href="./public.html">发布</a></li>
            </shiro:hasRole>
            <shiro:hasRole name="buyer">
                <li><a href="${cp}/shoppingCart">购物车</a></li>
            </shiro:hasRole>
        </ul>
    </div>
</div>
<div class="g-doc">
    <div class="m-tab m-tab-fw m-tab-simple f-cb">
        <h2>购物车</h2>
    </div>
    <div class="m-cartbox">
        <div class="head f-cb">
            <div class="col col1">
                <input type="checkbox" class="u-chk" name="selectAll" id="selectAll">
                <label for="selectAll" class="lab">全选</label>
            </div>
            <div class="col col2">商品信息</div>
            <div class="col col3">单价(元)</div>
            <div class="col col4">数量</div>
            <div class="col col5">金额(元)</div>
            <div class="col col6">操作</div>
        </div>
        <div class="m-cart">
            <div class="goods">
                <c:forEach var="item" items="${items}">
                    <ul class="m-goods">
                        <li data-err="0" class="gooditm">
                            <div class="col col0">
                                <input type="checkbox" class="u-chk" name="selectGood" id="checkbox${item.product.id}">
                            </div>
                            <div class="col col2">
                                <a href="/product/show?id=${item.product.id}" target="_blank" class="imgwrap">
                                    <img src="/product/showImg?id=${item.product.id}">
                                </a>
                                <div class="txtwrap">
                                    <h3 class="goodtlt">
                                        <a href="/product/show?id=${item.product.id}" target="_blank">
                                                ${item.product.title}
                                        </a>
                                    </h3>
                                </div>
                            </div>
                            <div class="col col3">
                                <div class="newprice">
                                    <span>${item.product.price}</span>
                                </div>
                            </div>
                            <div class="col col4">
                                <div>
                                    <span>${item.num}</span>
                                </div>
                            </div>
                            <div class="col col5">
                                <span class="sum sumrow">${item.product.price*item.num}</span>
                            </div>
                            <div class="col col6">
                                <a class="u-opt" href="/shoppingCart/delete/?id=${item.id}">删除</a>
                                <br/>
                                <span class="u-opt">移入我的收藏</span>
                            </div>
                        </li>
                    </ul>
                </c:forEach>
                <%--<div class="cartinfo">--%>
                <%--<span class="discount">活动优惠：-￥0.00</span>--%>
                <%--<div class="allsum">--%>
                <%--<span>商品应付总计：<em class="totalnum ">￥1059.00</em></span>--%>
                <%--</div>--%>
                <%--<div class="taxbox">--%>
                <%--<span class="j-taxnum"><em class="tt">预估税费：</em>￥0.00</span>--%>
                <%--</div>--%>
                <%--</div>--%>
            </div>
        </div>
        <div class="totalbox">
            <div id="point"></div>
            <div class="m-total f-cb" id="totalbar" style="position: relative; width: 1088px;">
                <div class="ttbar">
                    <div class="rt">
                        <%--<p class="allgoods">已选商品 <em class="num">1</em> 件 <span class="itm">总价(不含运费)：<em class="num">￥1059.00</em></span>--%>
                        <%--</p>--%>
                        <%--<p class="allmoney">活动优惠：-￥0.00<span class="itm">商品应付总计：￥1059.00</span><span class="itm">商品税费（不含运费税）：￥0.00</span></p>--%>
                        <button type="button" id="confirmBuy" class="gobuy">去结算</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/foot.jsp"></jsp:include>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/global.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript">
    var loading = new Loading();

    //todo 全部勾选商品功能未实现，勾选后页面开始转圈，不知道是因为什么
//    $('#selectAll').click(function () {
//        if (this.checked) {
//            console.log("我被勾选了");
//            allShoppingCars = getShoppingCars();
//            for (var i = 0; i < allShoppingCars.length; i++) {
//                var checkBox = document.getElementById("checkbox" + allShoppingCars[i].productId);
//                checkBox.checked = true;
//            }
//
//        } else {
//            console.log("我没有被勾选");
//        }
//    });

    function getShoppingCars() {
        judgeIsLogin();
        var shoppingCarProducts = "";
        var user = {};
        user.userId = ${currentUser.id};
        loading.show();
        $.ajax({
            async: false, //设置同步
            type: 'GET',
            url: '${cp}/shoppingCart/getShoppingCarts',
            data: user,
            dataType: 'json',
            success: function (result) {
                shoppingCarProducts = result.result;
            },
            error: function (result) {
                loading.result('查询错误');
            }
        });
//        shoppingCarProducts = eval("(" + shoppingCarProducts + ")");
        res = JSON.parse(shoppingCarProducts);
        return res;
    }

    var confirmBuy = util.get("confirmBuy");
    var buyPage = {
        init: function () {
            confirmBuy.addEventListener('click', function (e) {
                var allShoppingCars = getShoppingCars();
                var shoppingCartIds = new Array;
                var productIds = new Array;
                var productNums = new Array;
                var buyCounts = 0;
                for (var i = 0; i < allShoppingCars.length; i++) {
                    var checkBox = document.getElementById("checkbox" + allShoppingCars[i].productId);
                    if (checkBox.checked) {
                        shoppingCartIds[buyCounts] = allShoppingCars[i].id;
                        productIds[buyCounts] = allShoppingCars[i].productId;
                        productNums[buyCounts] = allShoppingCars[i].num;
                        buyCounts++;
                    }
                }
                loading.show();
                if (buyCounts == 0) {
                    loading.result("未选中商品");
                }
                else {
                    console.log(productIds);
                    console.log(productNums);
                    ajax({
                        data: {
                            strProductIds: JSON.stringify(productIds),
                            strNums: JSON.stringify(productNums),
                            strShoppingCartIds: JSON.stringify(shoppingCartIds)
                        },
                        url: '/buyShoppingCart',
                        type: 'POST',
                        success: function (result) {
                            loading.result('购买成功', function () {
                                location.href = '/product';
                            });
                        },
                        error: function (message) {
                            loading.result(message || '购买失败');
                        }
                    });
                }
            }.bind(this), false);
        }
    };
    buyPage.init();


    function getProductById(id) {
        var productResult = "";
        var product = {};
        product.id = id;
        $.ajax({
            async: false, //设置同步
            type: 'POST',
            url: '${cp}/getProductById',
            data: product,
            dataType: 'json',
            success: function (result) {
                productResult = result.result;
            },
            error: function (result) {
                layer.alert('查询错误');
            }
        });
        productResult = JSON.parse(productResult);
        return productResult;
    }

    function judgeIsLogin() {
        if ("${currentUser.id}" == null || "${currentUser.id}" == undefined || "${currentUser.id}" == "") {
            window.location.href = "${cp}/login";
        }
    }

    //    function selectAllClick(){
    //        $ = document.getElementById;
    //
    //    }

    function addToShoppingRecordsPre(productsId, productsCounts) {
        for (var i = 0; i < productsId.length; i++) {
            addToShoppingRecords(productsId[i], productsCounts[i]);
        }
        layer.confirm('前往订单状态？', {icon: 1, title: '购买成功', btn: ['前往订单', '继续购买']},
            function () {
                window.location.href = "${cp}/shopping_record";
            },
            function (index) {
                window.location.href = "${cp}/shopping_car";
            }
        );
    }

    function addToShoppingRecords(productId, productCounts) {
        judgeIsLogin();
        var shoppingRecord = {};
        shoppingRecord.userId = ${currentUser.id};
        shoppingRecord.productId = productId;
        shoppingRecord.counts = productCounts;
        var buyResult = "";
        $.ajax({
            async: false,
            type: 'POST',
            url: '${cp}/addShoppingRecord',
            data: shoppingRecord,
            dataType: 'json',
            success: function (result) {
                buyResult = result.result;
            },
            error: function (result) {
                layer.alert('购买错误');
            }
        });
        var product = getProductById(productId);
        if (buyResult == "success") {
            deleteShoppingCar(productId);
            layer.msg("商品 " + product.name + " 购买成功", {icon: 1});
        }
        else if (buyResult == "unEnough") {
            layer.alert("商品 " + product.name + " 库存不足，购买失败")
        }
    }

    function deleteShoppingCar(productId) {
        var shoppingCar = {};
        shoppingCar.userId = ${currentUser.id};
        shoppingCar.productId = productId;
        var deleteResult = "";
        $.ajax({
            async: false,
            type: 'POST',
            url: '${cp}/deleteShoppingCar',
            data: shoppingCar,
            dataType: 'json',
            success: function (result) {
                deleteResult = result.result;
            },
            error: function (result) {
                layer.alert('查询用户错误');
            }
        });
    }
</script>