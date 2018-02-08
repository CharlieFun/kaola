package com.netease.kaola.service.impl;

import com.netease.kaola.dao.ProductDao;
import com.netease.kaola.entity.Product;
import com.netease.kaola.service.ProductBiz;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * Created by funstar on 2018/1/26.
 */
@Service
public class ProductBizImpl implements ProductBiz, InitializingBean {
    public static final Logger LOGGER = LoggerFactory.getLogger(ProductBizImpl.class);
    private Set<String> fileTypes;

    @Autowired
    private ProductDao productDao;

    @Override
    public void afterPropertiesSet() throws Exception {
        fileTypes = new HashSet<>();
        fileTypes.add("png");
        fileTypes.add("jpg");
        fileTypes.add("jpeg");
        fileTypes.add("bmp");
    }

//    @Transactional
//    @Override
//    public boolean add(Product product, MultipartFile file, String path) {
//        if (file.getSize() != 0) {
//            String originalFileName = file.getOriginalFilename();
//            int index = originalFileName.lastIndexOf(".");
//            if (index == -1) {
//                return false;
//            }
//            String fileType = originalFileName.substring(index + 1);
//            if (!fileTypes.contains(fileType)) {
//                return false;
//            }
//            StringBuffer sb = new StringBuffer();
//            sb.append(path);
//            sb.append("/");
//            //用UUID给文件随机命名
//            sb.append(UUID.randomUUID());
//            sb.append(".");
//            sb.append(fileType);
//            String absolutePath = sb.toString();
//            LOGGER.info("absolute path:" + absolutePath);
//            File destFile = new File(absolutePath);
//            if (!destFile.exists()) {
//                destFile.mkdirs();
//            }
//            product.setImgPath(absolutePath);
//            try {
//                file.transferTo(destFile);
//            } catch (IOException e) {
//                LOGGER.error("图片保存失败", e);
//                product.setImgPath("未保存");
//            }
//        } else {
//            product.setImgPath("/");
//        }
//        //添加商品时默认是上架的
//        product.setStatus(true);
//        productDao.add(product);
//        return true;
//    }

    @Transactional
    @Override
    public boolean add(Product product, MultipartFile file) {
        if (file.getSize() != 0) {
            String originalFileName = file.getOriginalFilename();
            int index = originalFileName.lastIndexOf(".");
            if (index == -1) {
                return false;
            }
            String fileType = originalFileName.substring(index + 1);
            if (!fileTypes.contains(fileType)) {
                return false;
            }
            StringBuffer sb = new StringBuffer();
            //用UUID给文件随机命名
            sb.append(UUID.randomUUID());
            sb.append(".");
            sb.append(fileType);
            String newFileName = sb.toString();
            LOGGER.info("newFileName:" + newFileName);
            try {
                product.setImgData(file.getBytes());
            } catch (IOException e) {
                LOGGER.info("图片存储失败", e);
            }
        }
        //添加商品时默认是上架的
        product.setStatus(true);
        productDao.add(product);
        return true;
    }

    @Override
    public void update(Product product, MultipartFile file) {
        if (file.getSize() != 0) {
            String originalFileName = file.getOriginalFilename();
            int index = originalFileName.lastIndexOf(".");
            if (index != -1) {
                String fileType = originalFileName.substring(index + 1);
                if (fileTypes.contains(fileType)) {
                    try {
                        product.setImgData(file.getBytes());
                        productDao.update(product);
                    } catch (IOException e) {
                        LOGGER.info("图片存储失败", e);
                    }
                }
            }
        } else {
            productDao.updateWithoutImg(product);
        }
    }

    @Override
    public void delete(Long id) {
        productDao.delete(id);
    }

    @Override
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public byte[] getImgDataById(Long id) {
        Map<String, Object> map = productDao.getImgDataById(id);
        return (byte[]) map.get("imgData");
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }
}
