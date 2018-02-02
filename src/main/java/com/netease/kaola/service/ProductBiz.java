package com.netease.kaola.service;

import com.netease.kaola.entity.Product;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by funstar on 2018/1/26.
 */
public interface ProductBiz {
    boolean add(Product product, MultipartFile file);

    List<Product> findAll();

    byte[] getImgDataById(Long id);
}
