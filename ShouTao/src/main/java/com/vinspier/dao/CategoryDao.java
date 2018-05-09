package com.vinspier.dao;

import com.vinspier.pojo.Category;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
@Repository
public interface CategoryDao {
    List<Category> findAll() throws Exception;
    List<Category> searchContent(String searchContent) throws Exception;
    List<String> loadCategoryNames() throws Exception;
    Category getByCid(String cid) throws Exception;
    void addCategory(Category category) throws Exception;
    void deleteCategoryByCid(String cid) throws Exception;

}
