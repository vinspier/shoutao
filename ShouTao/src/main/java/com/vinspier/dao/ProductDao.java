package com.vinspier.dao;

import com.vinspier.pojo.Product;
import constant.Constant;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
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
    int getTotalRecordState(int pflag) throws Exception;
    int getTotalRecordCount() throws  Exception;
    List<Product> getByPage(String cid,int pflag,int startIndex,int pageSize) throws Exception;
    List<Product> getByPage_allPflag(int startIndex,int pageSize) throws Exception;
    List<Product> getByPage_pflagCondition(int pflag,int startIndex,int pageSize) throws Exception;
    ArrayList<Product> getOnCid(String cid,int pflag) throws Exception;
    ArrayList<Product> searchByPage(String searchContent, int pflag) throws Exception;
    void resetPflag(String pid,int pflag) throws Exception;
    void resetIsHot(String pid,int is_hot) throws Exception;
    void deleteProduct(String pid) throws Exception;
    void uploadProduct(Product product) throws Exception;
    void editProduct(Product product) throws Exception;
 }
