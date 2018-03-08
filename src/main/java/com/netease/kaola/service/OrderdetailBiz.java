package com.netease.kaola.service;

import com.netease.kaola.entity.Orderdetail;

import java.util.List;

/**
 * Created by funstar on 2018/3/8.
 */
public interface OrderdetailBiz {
    List<Orderdetail> findAllOrderdetailsByUsername(String username);

    Double calculateAccount(List<Orderdetail> orderdetails);
}
