package com.vinspier.service;

import com.vinspier.pojo.Category;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
public interface CategoryService {
    List<Category> findAll() throws Exception;
}
