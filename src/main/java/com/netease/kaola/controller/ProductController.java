package com.netease.kaola.controller;

import com.google.gson.Gson;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.entity.Product;
import com.netease.kaola.service.OrderdetailBiz;
import com.netease.kaola.service.ProductBiz;
import org.apache.ibatis.annotations.Param;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by funstar on 2018/1/25.
 */
@Controller
@RequestMapping("/product")
public class ProductController {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private Gson gson = new Gson();
    @Autowired
    private ProductBiz productBiz;

    @Autowired
    private OrderdetailBiz orderdetailBiz;

    @RequestMapping("")
    public String showProducts(Model model) {
        model.addAttribute("products", productBiz.findAll());
        return "buyer_products";
    }

    @RequestMapping("/addView")
    public String addView() {
        return "/product_add";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Product product, MultipartFile file, HttpServletRequest request) {
//        String path = request.getSession().getServletContext().getRealPath("/img");
        if (file == null) {
            LOGGER.info("file为空");
        }
        productBiz.add(product, file);
        return "redirect:/seller";
    }

    @RequestMapping("/updateView")
    public String updateView(@Param("id") Long id, Model model) {
        model.addAttribute("product", productBiz.getProductById(id));
        return "product_update";
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(Product product, MultipartFile file) {
        LOGGER.info(product.toString());
        productBiz.update(product, file);
        return "redirect:/seller";
    }

    @RequestMapping("/delete")
    public String delete(@Param("id") Long id) {
        productBiz.delete(id);
        return "redirect:/seller";
    }

    @RequestMapping("/show")
    public String showProduct(@Param("id") Long id, Model model) {
        model.addAttribute("product", productBiz.getProductById(id));
        return "product_show";
    }

    @RequestMapping("/showImg")
    public void showImg(@Param("id") Long id, HttpServletResponse response) {
        byte[] imgData = productBiz.getImgDataById(id);
        response.setContentType("img/jpeg");
        response.setCharacterEncoding("utf-8");
        try {

            OutputStream outputStream = response.getOutputStream();
            InputStream in = new ByteArrayInputStream(imgData);

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                outputStream.write(buffer, 0, len);
            }
            outputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
