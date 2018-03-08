package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.service.OrderdetailBiz;
import com.netease.kaola.service.ProductBiz;
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
 * Created by funstar on 2018/3/8.
 */
@Controller
public class BuyerController {
    public static final Logger LOGGER = LoggerFactory.getLogger(BuyerController.class);
    private Gson gson = new Gson();
    @Autowired
    private ProductBiz productBiz;

    @Autowired
    private OrderdetailBiz orderdetailBiz;

    @ResponseBody
    @RequestMapping(value = "/buy", method = RequestMethod.POST)
    public String buy(@Param("id") Long id, @Param("num") int num) {
        Map<String, Integer> map = new HashMap<>();
        map.put("code", 200);
        String res = gson.toJson(map);
        return res;
    }

    @RequestMapping("/account")
    public String showAccount(HttpSession session, Model model) {
        List<Orderdetail> orderdetails = orderdetailBiz.findAllOrderdetailsByUsername((String) session.getAttribute("username"));
        Double account = orderdetailBiz.calculateAccount(orderdetails);
        model.addAttribute("orderdetails", orderdetails);
        model.addAttribute("account", account);
        return "/account";
    }
}
