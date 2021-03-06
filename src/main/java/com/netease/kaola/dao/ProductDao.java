package com.netease.kaola.dao;

import com.netease.kaola.entity.Product;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Map;

/**
 * Created by funstar on 2018/1/26.
 */
@Repository
public interface ProductDao {

    void add(Product product);

    void updateWithoutImg(Product product);

    void update(Product product);

    void delete(Long id);

    List<Product> findAll();

    Map<String,Object> getImgDataById(Long id);

    Product getProductById(Long id);
}
