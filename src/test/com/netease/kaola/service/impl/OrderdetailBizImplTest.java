package com.netease.kaola.service.impl;

import com.google.gson.Gson;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.entity.Product;
import com.netease.kaola.service.OrderdetailBiz;
import com.netease.kaola.service.ProductBiz;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by funstar on 2018/3/8.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class OrderdetailBizImplTest {
    @Autowired
    private OrderdetailBiz orderdetailBiz;

    @Autowired
    private ProductBiz productBiz;

    private Gson gson = new Gson();

    @Test
    public void findAllOrderdetailsByUsername() throws Exception {
        List<Orderdetail> orderdetails = orderdetailBiz.findAllOrderdetailsByUsername("buyer");
        String res = gson.toJson(orderdetails);
        System.out.print(orderdetails.size());
        System.out.print(res);
    }

    @Test
    public void findAllProducts() throws Exception {
        List<Product> products = productBiz.findAll();
        System.out.println(products.get(0));
    }

}