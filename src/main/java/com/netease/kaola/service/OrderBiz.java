package com.netease.kaola.service;

/**
 * Created by funstar on 2018/3/9.
 */
public interface OrderBiz {
    boolean buy(Long userId, Long productId, int amount);
}
