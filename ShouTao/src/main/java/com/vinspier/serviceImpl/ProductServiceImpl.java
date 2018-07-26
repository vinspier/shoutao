package com.vinspier.serviceImpl;

import com.vinspier.dao.CategoryDao;
import com.vinspier.dao.ProductDao;
import com.vinspier.entity.Page;
import com.vinspier.pojo.Category;
import com.vinspier.pojo.Product;
import com.vinspier.service.ProductService;
import com.vinspier.utils.UUIDUtils;
import constant.Constant;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private CategoryDao categoryDao;

    public List<Product> findHot() throws Exception{
        return productDao.findHotProducts(Constant.PRODUCT_IS_HOT,Constant.PRODUCT_FLAG_UP);
    }
    public List<Product> findNew() throws Exception{
        return productDao.findNewProducts(Constant.PRODUCT_FLAG_UP);
    }
    public Page<Product> searchByPage(int pageNumber, int pageSize, String searchContent) throws Exception{
        Page<Product> productPage = new Page<Product>(pageNumber,pageSize);
        List<Product> productList = productDao.searchByPage("%"+searchContent+"%",Constant.PRODUCT_FLAG_UP);
        List<Category> categoryList = categoryDao.searchContent(searchContent);
        for(Category c :categoryList){
            List<Product> productList1 = productDao.getOnCid(c.getCid(),Constant.PRODUCT_FLAG_UP);
            Iterator<Product> iterator = productList1.iterator();
            while(iterator.hasNext()){
                productList.add(iterator.next());
            }
        }
        productPage.setTotalRecord(productList.size());
        List<Product> pageData = new ArrayList<Product>();
        if(pageNumber<productPage.getTotalPage()){
            for(int i=productPage.getStartIndex();i<productPage.getStartIndex()+pageSize;i++){
                pageData.add(productList.get(i));
            }
        }else {
            for(int i=productPage.getStartIndex();i<productList.size();i++){
                pageData.add(productList.get(i));
            }
        }
        productPage.setData(pageData);

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

    public String uploadProduct(Product product,MultipartFile file,String realPath) throws Exception{
        try {
            if(file == null){
                throw new Exception("未收到上传的图片信息");
            }
            String resetPictureName = UUIDUtils.resetPictureName();
            String ext = FilenameUtils.getExtension(file.getOriginalFilename());

            product.setPid(UUIDUtils.getId());
            product.setPdate(new Date());
            product.setPimage("upload/"+resetPictureName+"."+ext);

            String storePath = realPath.replace("\\","/").replace("/target/ShouTao","/src/main/webapp/WEB-INF/upload");
            productDao.uploadProduct(product);
            /**当商品信息全部正确无误存入数据库后再上传商品到项目目录*/
            file.transferTo(new File(storePath+"/"+resetPictureName+"."+ext));
        }catch (Exception e){
            return "商品上传失败，请重新尝试";
        }
        return "上传成功，返回"+"<a href='/adminIndex' >首页</a>";
    }

    public void editProduct(Product product) throws Exception{
        productDao.editProduct(product);
    }
}
