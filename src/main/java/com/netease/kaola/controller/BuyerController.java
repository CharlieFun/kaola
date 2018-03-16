package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.netease.kaola.entity.Order;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.entity.ShoppingCart;
import com.netease.kaola.entity.User;
import com.netease.kaola.service.*;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funstar on 2018/3/8.
 */
@Controller
public class BuyerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerController.class);
    private Gson gson = new Gson();
    private JsonParser parser = new JsonParser();
    @Autowired
    private ProductBiz productBiz;
    @Autowired
    private OrderdetailBiz orderdetailBiz;
    @Autowired
    private UserBiz userBiz;
    @Autowired
    private OrderBiz orderBiz;
    @Autowired
    private ShoppingCartBiz shoppingCartBiz;

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(@Param("productId") Long productId, @Param("num") int num, HttpSession session) {
        LOGGER.info("购买的商品ID是：{}", productId);
        LOGGER.info("购买数量是：{}", num);
        String username = (String) session.getAttribute("username");
        User user = userBiz.findByUsername(username);
        boolean flag = orderBiz.buy(user.getId(), productId, num);
        Map<String, Integer> map = new HashMap<>();
        if (flag) {
            map.put("code", 200);
        }
        String res = gson.toJson(map);
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/buyShoppingCart", method = RequestMethod.POST)
    public Map<String, Object> buyShoppingCart(@Param("strProductIds") String strProductIds,
                                               @Param("strNums") String strNums,
                                               HttpSession session) {
        LOGGER.info("购买商品ID Array:{}", strProductIds);
        LOGGER.info("购买商品数量 Array:{}", strNums);
        JsonArray productIds = parser.parse(strProductIds).getAsJsonArray();
        JsonArray nums = parser.parse(strNums).getAsJsonArray();
        User user = (User) session.getAttribute("currentUser");
        boolean flag = orderBiz.buyShoppingCart(user.getId(), productIds, nums);
        Map<String, Object> map = new HashMap<>();
        if (flag) {
            map.put("code", 200);
        }
        return map;
    }

    @RequestMapping("/account")
    public String showAccount(HttpSession session, Model model) {
        String username = (String) session.getAttribute("username");
        LOGGER.info("当前登录的用户名是：{}", username);
        List<Orderdetail> orderdetails = orderdetailBiz.findAllOrderdetailsByUsername(username);
        Double account = orderdetailBiz.calculateAccount(orderdetails);
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("account", account);
        return "/account";
    }

}
