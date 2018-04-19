package com.vinspier.dao;

import com.vinspier.pojo.Product;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Repository
public interface ProductDao {
    List<Product> findHotProducts(int is_hot,int is_up) throws Exception;
    List<Product> findNewProducts(int pflag) throws Exception;
    Product getById(String pid) throws Exception;
    int getTotalRecord(String cid,int pflag) throws Exception;
    List<Product> getByPage(String cid,int pflag,int startIndex,int pageSize) throws Exception;
    List<Product> searchByPage(String searchContent,int pflag,int startIndex,int pageSize) throws Exception;
 }
