package com.netease.kaola.controller;

import com.netease.kaola.entity.Product;
import com.netease.kaola.service.ProductBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by funstar on 2018/1/25.
 */
@Controller
@RequestMapping("product")
public class ProductController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    @Autowired
    private ProductBiz productBiz;

    @RequestMapping("/addView")
    public String addView() {
        return "/product_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Product product, MultipartFile file, HttpServletRequest request) {
        String path = request.getSession().getServletContext().getRealPath("/img");
        if (file == null){
            LOGGER.info("file为空");
        }
        productBiz.add(product, file, path);
        return "redirect:/product/addView";
    }
}
