package com.netease.kaola.service;

import com.netease.kaola.entity.Product;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by funstar on 2018/1/26.
 */
public interface ProductBiz {
    boolean add(Product product, MultipartFile file, String path);
}
