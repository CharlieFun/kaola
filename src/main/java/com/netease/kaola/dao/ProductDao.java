package com.netease.kaola.dao;

import com.netease.kaola.entity.Product;
import org.springframework.stereotype.Repository;

/**
 * Created by funstar on 2018/1/26.
 */
@Repository
public interface ProductDao {

    void add(Product product);

}
