package com.vinspier.service;


import com.vinspier.entity.Page;
import com.vinspier.pojo.Product;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
public interface ProductService {
    List<Product> findHot() throws Exception;
    List<Product> findNew() throws Exception;
    Product getById(String pid) throws  Exception;
    Page<Product> getByPage(int pageNumber, int pageSize, String cid) throws Exception;
}
