package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.netease.kaola.service.ProductBiz;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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

    @RequestMapping("/test")
    @ResponseBody
    public String test(){
        Map<String,String> map = new HashMap<>();
        map.put("123","fsd");
        map.put("333","aaa");
        String res = gson.toJson(map);
        return res;
    }

    @RequiresRoles("seller")
    @RequestMapping("")
    public String showProducts(Model model){
        model.addAttribute("products",productBiz.findAll());
        return "seller_products";
    }
}
