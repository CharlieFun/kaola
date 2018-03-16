package com.netease.kaola.service;

import com.google.gson.JsonArray;

/**
 * Created by funstar on 2018/3/9.
 */
public interface OrderBiz {
    boolean buy(Long userId, Long productId, int amount);

    boolean buyShoppingCart(Long id, JsonArray productIds, JsonArray nums);
}
