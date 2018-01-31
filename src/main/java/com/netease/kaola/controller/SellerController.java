package com.netease.kaola.controller;

import com.google.gson.Gson;
import org.springframework.stereotype.Controller;
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

    @RequestMapping("/")
    @ResponseBody
    public String view(){
        Map<String,String> map = new HashMap<>();
        map.put("123","fsd");
        map.put("333","aaa");
        String res = gson.toJson(map);
        return res;
    }
}
