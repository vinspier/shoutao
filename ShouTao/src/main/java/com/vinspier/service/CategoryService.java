package com.vinspier.service;

import com.vinspier.pojo.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public interface CategoryService {
    List<Category> findAll() throws Exception;
    Category getByCid(String cid) throws Exception;
    void addCategory(Category category) throws Exception;
    void deleteCategoryByCid(String cid) throws Exception;
    int checkName(String name) throws Exception;
}
