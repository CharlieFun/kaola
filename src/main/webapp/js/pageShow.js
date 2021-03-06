(function (w, d, u) {
    var productId = $("button[name='buy']").attr('id');
    var showContent = util.get('showContent');
    if (!showContent) {
        return;
    }
    var loading = new Loading();
    var layer = new Layer();
    var page = {
        init: function () {
            showContent.addEventListener('click', function (e) {
                var productNum = $('allNum').textContent;
                // var testId = $("button[name='buy']").at
                console.log(productNum+'+'+productId);
                var ele = e.target;
                var buy = ele && ele.dataset.buy;
                var cart = ele && ele.dataset.cart;
                if (buy) {
                    //var id = productId;
                    console.log(productNum + '+' + productId);
                    console.log(productId);
                    layer.reset({
                        content: '确认购买本内容吗？',
                        onconfirm: function () {
                            layer.hide();
                            loading.show();
                            ajax({
                                data: {productId: productId, num: productNum},
                                url: '/buy',
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
                        }.bind(this)
                    }).show();
                    return;
                }
                if (cart) {
                    //var id = productId;
                    console.log(productNum + '+' + productId);
                    console.log('到这里了');
                    ajax({
                        data: {productId: productId, num: productNum},
                        url: '/shoppingCart/add',
                        type: 'POST',
                        success: function (result) {
                            loading.show();
                            loading.result('加入购物车成功');
                        },
                        error: function (message) {
                            loading.show();
                            loading.result(message || '加入购物车失败');
                        }
                    });
                    return;
                }
            }.bind(this), false);
        }
    };
    page.init();
})(window, document);