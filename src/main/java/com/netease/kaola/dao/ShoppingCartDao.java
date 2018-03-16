package com.netease.kaola.dao;

import com.netease.kaola.entity.ShoppingCart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by funstar on 2018/3/11.
 */
@Repository
public interface ShoppingCartDao {
    void add(ShoppingCart shoppingCart);

    List<ShoppingCart> findShoppingCartByUserId(Long userId);

    void delete(Long id);

    ShoppingCart findShoppingCartByUserIdAndProductId(@Param("userId") Long userId,
                                                      @Param("productId") Long productId);

    void updateNum(@Param("id") Long id, @Param("num") int num);
}
