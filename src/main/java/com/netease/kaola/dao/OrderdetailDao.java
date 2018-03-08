package com.netease.kaola.dao;

import com.netease.kaola.entity.Orderdetail;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by funstar on 2018/3/8.
 */
@Repository
public interface OrderdetailDao {
    void add(Orderdetail orderdetail);

    List<Orderdetail> findAllOrderdetailsByUsername(String username);
}
