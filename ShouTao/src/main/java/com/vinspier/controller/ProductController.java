package com.vinspier.controller;

import com.vinspier.entity.Page;
import com.vinspier.pojo.Category;
import com.vinspier.pojo.Product;
import com.vinspier.service.CategoryService;
import com.vinspier.service.ProductService;
import constant.Constant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/getByPid")
    public String getByPid(@Param("pid") String pid,Model model) throws Exception{
        Product product = productService.getById(pid);
        String cid = product.getCid();
        Category category = categoryService.getByCid(cid);
        model.addAttribute("product",product);
        model.addAttribute("category",category);
        return "view/product_info";
    }

    /**用户查询某一分类下的商品*/
    @RequestMapping(value = "/getByPage")
    public String getByPage(HttpServletRequest request, @Param("cid") String cid, Model model) throws Exception{
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.getByPage(pageNumber,pageSize,cid);
            Category category = categoryService.getByCid(cid);
            model.addAttribute("productPage",productPage);
            model.addAttribute("category",category);
            model.addAttribute("isCategory",Constant.PAGINATION_DISPLAY_ORIGIN_B);
        } catch (Exception e) {
            request.setAttribute("msg","分页查询失败");
        }
        return "view/product_list";
    }


    /**搜索引擎的简单实现*/
    @RequestMapping(value = "/searchByPage")
    public String searchByPage(HttpServletRequest request,@RequestParam("pageNumber") String pageNumber,@RequestParam("searchContent") String searchContent,Model model) throws Exception{
        try {
            int pageNumber1 = Integer.parseInt(pageNumber);
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.searchByPage(pageNumber1,pageSize,searchContent);
            if(productPage.getData() == null || productPage.getData().size() <= 0){
                request.setAttribute("msg","未找到和   ["+searchContent+"]   相关的商品");
                return "view/notification_message";
            }else {
                model.addAttribute("searchContent",searchContent);
                model.addAttribute("isSearch",Constant.PAGINATION_DISPLAY_ORIGIN_A);
                model.addAttribute("productPage",productPage);
                return "view/product_list";
            }
        } catch (NumberFormatException e) {
            return "redirect:index";
        }
    }

}
