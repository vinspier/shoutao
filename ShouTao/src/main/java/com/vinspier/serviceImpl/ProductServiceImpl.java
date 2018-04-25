package com.vinspier.serviceImpl;

import com.vinspier.dao.ProductDao;
import com.vinspier.entity.Page;
import com.vinspier.pojo.Product;
import com.vinspier.service.ProductService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Page<Product> getProductToPage(int pflag,int pageNumber, int pageSize) throws Exception{
        Page<Product> productPage = new Page<Product>(pageNumber,pageSize);
        List<Product> productList = new ArrayList<Product>();
        int totalRecords = 0;
        if(pflag == Constant.PRODUCT_FLAG_UP){
            productList = productDao.getByPage_pflagCondition(pflag,productPage.getStartIndex(),productPage.getPageSize());
            totalRecords = productDao.getTotalRecordState(Constant.PRODUCT_FLAG_UP);
        }
        if(pflag == Constant.PRODUCT_FLAG_DOWN){
            productList = productDao.getByPage_pflagCondition(pflag,productPage.getStartIndex(),productPage.getPageSize());
            totalRecords = productDao.getTotalRecordState(Constant.PRODUCT_FLAG_DOWN);
        }
        if(pflag == Constant.PRODUCT_FLAG_ALL){
            productList = productDao.getByPage_allPflag(productPage.getStartIndex(),productPage.getPageSize());
            totalRecords = productDao.getTotalRecordCount();
        }
        productPage.setData(productList);
        productPage.setTotalRecord(totalRecords);
        return productPage;
    }

    public Page<Product> searchByPage(int pageNumber, int pageSize, String searchContent) throws Exception{
        Page<Product> productPage = new Page<Product>(pageNumber,pageSize);

        List<Product> productList = productDao.searchByPage(searchContent,Constant.PRODUCT_FLAG_UP,productPage.getStartIndex(),productPage.getPageSize());
        productPage.setData(productList);

        int totalRecords = productDao.getTotalRecord(searchContent,Constant.PRODUCT_FLAG_UP);
        productPage.setTotalRecord(totalRecords);

        return productPage;
    }

     public void resetPflag(String pid,int pflag) throws Exception{
        productDao.resetPflag(pid,pflag);
    }

     public void resetIsHot(String pid,int is_hot) throws Exception{
        productDao.resetIsHot(pid,is_hot);
    }

    public void deleteProduct(String pid) throws Exception{
        productDao.deleteProduct(pid);
    }
}
