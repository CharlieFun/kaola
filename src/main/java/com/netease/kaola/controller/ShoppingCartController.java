package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.netease.kaola.entity.ShoppingCart;
import com.netease.kaola.entity.User;
import com.netease.kaola.service.ShoppingCartBiz;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funstar on 2018/3/11.
 */
@Controller
@RequestMapping("/shoppingCart")
public class ShoppingCartController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerController.class);
    private Gson gson = new Gson();
    @Autowired
    private ShoppingCartBiz shoppingCartBiz;

    @RequestMapping("")
    public String showShoppingCar(HttpSession session, Model model) {
        User currentUser = (User) session.getAttribute("currentUser");
        List<ShoppingCart> shoppingCarts = shoppingCartBiz.findShoppingCartByUserId(currentUser.getId());
        model.addAttribute("items", shoppingCarts);
        return "/shopping_cart";
    }

    @ResponseBody
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addShoppingCart(@Param("productId") Long productId, @Param("num") int num, HttpSession session) {
        LOGGER.info("购物车，购买的商品ID是：{}", productId);
        LOGGER.info("购买数量是：{}", num);
        User currentUser = (User) session.getAttribute("currentUser");
        shoppingCartBiz.add(currentUser.getId(), productId, num);
        LOGGER.info("加入购物车成功，商品ID:{}", productId);
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 200);
        return gson.toJson(map);
    }

    @ResponseBody
    @RequestMapping(value = "/getShoppingCarts", method = RequestMethod.GET)
    public Map<String, Object> getShoppingCarts(@Param("userId") Long userId) {
        List<ShoppingCart> shoppingCarts = shoppingCartBiz.findShoppingCartByUserId(userId);
        LOGGER.info("查询用户的ID:{}", userId);
        LOGGER.info("购物车的大小:{}", shoppingCarts.size());
        String strShoppingCarts = gson.toJson(shoppingCarts);
        LOGGER.info("购物车的内容:{}", strShoppingCarts);
        Map<String, Object> map = new HashMap<>();
        map.put("code", 200);
        map.put("result", strShoppingCarts);
        return map;
    }

    @RequestMapping("/delete")
    public String delete(@Param("id") Long id) {
        shoppingCartBiz.delete(id);
        return "redirect:/shoppingCart";
    }
}
