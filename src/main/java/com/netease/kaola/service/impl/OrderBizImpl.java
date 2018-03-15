package com.netease.kaola.service.impl;

import com.netease.kaola.dao.OrderDao;
import com.netease.kaola.dao.OrderdetailDao;
import com.netease.kaola.dao.ProductDao;
import com.netease.kaola.entity.Order;
import com.netease.kaola.entity.Orderdetail;
import com.netease.kaola.entity.Product;
import com.netease.kaola.service.OrderBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

/**
 * Created by funstar on 2018/3/9.
 */
@Service
public class OrderBizImpl implements OrderBiz {
    private static final Logger LOGGER = LoggerFactory.getLogger(OrderBizImpl.class);
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private OrderdetailDao orderdetailDao;
    @Autowired
    private ProductDao productDao;

    @Override
    public boolean buy(Long userId, Long productId, int amount) {
        Date date = new Date();//获得系统时间.
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowTime = dateFormat.format(date);//将时间格式转换成符合Timestamp要求的格式.
        Timestamp createtime = Timestamp.valueOf(nowTime);//把时间转换
        Order order = new Order(userId, createtime);
        orderDao.add(order);
        Long orderId = order.getId();
        LOGGER.info("新创建订单，订单ID为：{}", orderId);
        Product product = productDao.getProductById(productId);
        Orderdetail orderdetail = new Orderdetail(orderId, productId, amount, product.getPrice());
        orderdetailDao.add(orderdetail);
        return true;
    }
}
