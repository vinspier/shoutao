package com.vinspier.serviceImpl;

import com.vinspier.dao.ProductDao;
import com.vinspier.entity.Page;
import com.vinspier.pojo.Product;
import com.vinspier.service.ProductService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    public List<Product> findHot() throws Exception{
        return productDao.findHotProducts(Constant.PRODUCT_IS_HOT,Constant.PRODUCT_FLAG_UP);
    }
    public List<Product> findNew() throws Exception{
        return productDao.findNewProducts(Constant.PRODUCT_FLAG_UP);
    }

    public Product getById(String pid) throws Exception{
        return productDao.getById(pid);
    }


    public Page<Product> getByPage(int pageNumber,int pageSize, String cid) throws Exception{
        Page<Product> productPage = new Page<Product>(pageNumber,pageSize);

        List<Product> productList = productDao.getByPage(cid,Constant.PRODUCT_FLAG_UP,productPage.getStartIndex(),productPage.getPageSize());
        productPage.setData(productList);

        int totalRecords = productDao.getTotalRecord(cid,Constant.PRODUCT_FLAG_UP);
        productPage.setTotalRecord(totalRecords);

        return productPage;
    }
}
