package com.netease.kaola.service.impl;

import com.netease.kaola.dao.ShoppingCartDao;
import com.netease.kaola.entity.ShoppingCart;
import com.netease.kaola.service.ShoppingCartBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by funstar on 2018/3/11.
 */
@Service
public class ShoppingCartBizImpl implements ShoppingCartBiz {
    @Autowired
    private ShoppingCartDao shoppingCartDao;

    @Override
    public void add(Long userId, Long productId, int num) {
        ShoppingCart existShoppingCart = shoppingCartDao.findShoppingCartByUserIdAndProductId(userId, productId);
        if (existShoppingCart == null) {
            ShoppingCart shoppingCart = new ShoppingCart(userId, productId, num);
            shoppingCartDao.add(shoppingCart);
        } else {
            int newNum = existShoppingCart.getNum() + num;
            shoppingCartDao.updateNum(existShoppingCart.getId(), newNum);
        }
    }

    @Override
    public List<ShoppingCart> findShoppingCartByUserId(Long userId) {
        return shoppingCartDao.findShoppingCartByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        shoppingCartDao.delete(id);
    }
}
