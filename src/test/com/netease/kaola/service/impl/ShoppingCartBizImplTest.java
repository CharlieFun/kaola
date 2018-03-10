package com.netease.kaola.service.impl;

import com.netease.kaola.dao.ShoppingCartDao;
import com.netease.kaola.entity.ShoppingCart;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by funstar on 2018/3/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-context.xml"})
public class ShoppingCartBizImplTest {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Test
    public void addAndFindTest(){
        Long userId = 2L;
        Long productId1 = 3L;
        Long productId2 = 5L;
        ShoppingCart cart1 = new ShoppingCart(userId,productId1,2);
        ShoppingCart cart2 = new ShoppingCart(userId,productId2,6);
        shoppingCartDao.add(cart1);
        shoppingCartDao.add(cart2);
        List<ShoppingCart> carts = shoppingCartDao.findShoppingCartByUserId(userId);
        assertEquals(2,carts.size());
        assertEquals("5",String.valueOf(carts.get(1).getProductId()));
    }

}