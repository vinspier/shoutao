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
    public Page<Product> searchByPage(int pageNumber, int pageSize, String searchContent) throws Exception{
        Page<Product> productPage = new Page<Product>(pageNumber,pageSize);

        List<Product> productList = productDao.searchByPage(searchContent,Constant.PRODUCT_FLAG_UP,productPage.getStartIndex(),productPage.getPageSize());
        productPage.setData(productList);

        int totalRecords = productDao.getTotalRecord(searchContent,Constant.PRODUCT_FLAG_UP);
        productPage.setTotalRecord(totalRecords);

        return productPage;
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

     public String resetPflag(String pid,int pflag) throws Exception{
        String message = "";
         try {
             productDao.resetPflag(pid,pflag);
             if(pflag == 1){
                 message = "商品下架成功";
             }
             if(pflag == 0){
                 message = "商品上架成功";
             }
         } catch (Exception e) {
             if(pflag == 1){
                 message = "商品下架失败";
             }
             if(pflag == 0){
                 message = "商品上架失败";
             }
         }
         return message;
     }

     public String resetIsHot(String pid,int is_hot) throws Exception{
        String message = "";
         try {
             productDao.resetIsHot(pid,is_hot);
             if(is_hot == 1){
                 message = "设置热门商品成功";
             }
             if(is_hot == 0){
                 message = "取消商品热门属性成功";
             }
         } catch (Exception e) {
             if(is_hot == 1){
                 message = "设置热门商品失败";
             }
             if(is_hot == 0){
                 message = "取消商品热门属性失败";
             }
             return message;
         }
         return message;
     }

    public void deleteProduct(String pid) throws Exception{
        productDao.deleteProduct(pid);
    }
}
