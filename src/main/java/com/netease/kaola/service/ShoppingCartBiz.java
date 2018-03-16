package com.netease.kaola.service;

import com.netease.kaola.entity.ShoppingCart;

import java.util.List;

/**
 * Created by funstar on 2018/3/11.
 */
public interface ShoppingCartBiz {
    void add(Long userId, Long productId, int num);

    List<ShoppingCart> findShoppingCartByUserId(Long userId);

    void delete(Long id);
}
