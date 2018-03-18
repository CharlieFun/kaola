package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.entity.Product;
import com.netease.kaola.service.OrderdetailBiz;
import com.netease.kaola.service.ProductBiz;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funstar on 2018/1/25.
 */
@Controller
@RequestMapping("/seller")
public class SellerController {
    private Gson gson = new Gson();
    @Autowired
    private ProductBiz productBiz;
    @Autowired
    private OrderdetailBiz orderdetailBiz;

    @RequiresRoles("seller")
    @RequestMapping("")
    public String showProducts(Model model) {
        List<Product> products = productBiz.findAll();
        List<Orderdetail> orderdetails = orderdetailBiz.findAllHaveBuyProducts();
        Map<Long, Integer> orderdetailMap = new HashMap<>();
        for (Orderdetail orderdetail : orderdetails) {
            if (orderdetail != null && orderdetail.getProductId() != null) {
                Long productId = orderdetail.getProductId();
                if (orderdetailMap.containsKey(productId)) {
                    orderdetailMap.put(productId, orderdetailMap.get(productId) + orderdetail.getAmount());
                } else {
                    orderdetailMap.put(productId, orderdetail.getAmount());
                }
            }
        }
        for (Product product : products) {
            if (orderdetailMap.containsKey(product.getId())) {
                product.setHaveBuy(true);
                product.setTotalBuyNum(orderdetailMap.get(product.getId()));
            }
        }
        model.addAttribute("products", products);
        return "seller_products";
    }
}
