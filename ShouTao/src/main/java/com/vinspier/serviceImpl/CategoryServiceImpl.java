package com.vinspier.serviceImpl;

import com.vinspier.dao.CategoryDao;
import com.vinspier.pojo.Category;
import com.vinspier.service.CategoryService;
import com.vinspier.utils.JedisUtils;
import constant.Constant;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryDao categoryDao;

    public  List<Category> findAll() throws Exception{
       return categoryDao.findAll();
    }

    public void addCategory(Category category) throws Exception{
        categoryDao.addCategory(category);
    }

    public void deleteCategoryByCid(String cid) throws Exception{
        categoryDao.deleteCategoryByCid(cid);
    }

     public int checkName(String name) throws Exception{
        List<String> categoryNames = categoryDao.loadCategoryNames();
        if(categoryNames.contains(name)){
            return Constant.NAME_INAVALIABLE;
        }else{
            return Constant.NAME_AVALIABLE;
        }

     }


    // 从redis里取数据
    public String findFromRedis() throws Exception{
        Jedis jedis = JedisUtils.getJedis();
        String value = jedis.get(Constant.STORE_CATEGORY_LIST);
        if(value == null){
            value = JSONArray.fromObject(findAll()).toString();
            jedis.set(Constant.STORE_CATEGORY_LIST,value);
        }
        return value;
    }
    public Category getByCid(String cid) throws Exception{
        return categoryDao.getByCid(cid);
    }
}
