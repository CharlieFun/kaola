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
        ShoppingCart shoppingCart = new ShoppingCart(currentUser.getId(), productId, num);
        shoppingCartBiz.add(shoppingCart);
        LOGGER.info("加入购物车成功，商品ID:{}", productId);
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 200);
        return gson.toJson(map);
    }

    @RequestMapping("/delete")
    public String delete(@Param("id")Long id){
        shoppingCartBiz.delete(id);
        return "redirect:/shoppingCart";
    }
}
