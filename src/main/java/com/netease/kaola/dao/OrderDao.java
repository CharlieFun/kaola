package com.netease.kaola.dao;

import com.netease.kaola.entity.Order;
import org.springframework.stereotype.Repository;

/**
 * Created by funstar on 2018/3/9.
 */
@Repository
public interface OrderDao {
    void add(Order order);
}
