package com.netease.kaola.service.impl;

import com.netease.kaola.dao.OrderdetailDao;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.service.OrderdetailBiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by funstar on 2018/3/8.
 */
@Service
public class OrderdetailBizImpl implements OrderdetailBiz {
    @Autowired
    private OrderdetailDao orderdetailDao;

    @Override
    public List<Orderdetail> findAllOrderdetailsByUsername(String username) {
        return orderdetailDao.findAllOrderdetailsByUsername(username);
    }

    @Override
    public Double calculateAccount(List<Orderdetail> orderdetails) {
        Double account = 0.0;
        for (Orderdetail orderdetail : orderdetails) {
            if (orderdetail != null) {
                account += orderdetail.getAmount() * orderdetail.getProduct().getPrice();
            }
        }
        return account;
    }
}
