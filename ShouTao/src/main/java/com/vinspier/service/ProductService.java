package com.vinspier.service;


import com.vinspier.entity.Page;
import com.vinspier.pojo.Product;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
public interface ProductService {
    List<Product> findHot() throws Exception;
    List<Product> findNew() throws Exception;
    Product getById(String pid) throws  Exception;
    Page<Product> getByPage(int pageNumber, int pageSize, String cid) throws Exception;
    Page<Product> getProductToPage(int pflag,int pageNumber, int pageSize) throws Exception;
    Page<Product> searchByPage(int pageNumber, int pageSize, String searchContent) throws Exception;
    String resetPflag(String pid,int pflag) throws Exception;
    String resetIsHot(String pid,int is_hot) throws Exception;
    void deleteProduct(String pid) throws Exception;
    String uploadProduct(Product product, MultipartFile file,String realPath) throws Exception;
    void editProduct(Product product) throws Exception;
}
