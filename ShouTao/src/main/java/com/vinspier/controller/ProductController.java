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
    @RequestMapping(value = "/admin_getByPid")
    public String admin_getByPid(@Param("pid") String pid,Model model) throws Exception{
        Product product = productService.getById(pid);
        String cid = product.getCid();
        Category category = categoryService.getByCid(cid);
        model.addAttribute("product",product);
        model.addAttribute("category",category);
        return "admin/product_info";
    }

    /**查询某一分类下的商品*/
    @RequestMapping(value = "/getByPage")
    public String getByPage(HttpServletRequest request, @Param("cid") String cid, Model model) throws Exception{
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.getByPage(pageNumber,pageSize,cid);
            model.addAttribute("productPage",productPage);
        } catch (Exception e) {
            request.setAttribute("msg","分页查询失败");
        }
        return "view/product_list";
    }
    @RequestMapping(value = "/admin_getByPage")
    public String admin_getByPage(HttpServletRequest request, @Param("cid") String cid, Model model) throws Exception{
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.getByPage(pageNumber,pageSize,cid);
            model.addAttribute("productPage",productPage);
        } catch (Exception e) {
            request.setAttribute("msg","分页查询失败");
        }
        return "admin/product_list";
    }

    @RequestMapping(value = "/getProductToPage")
    public String getProductToPage(HttpServletRequest request,@RequestParam("flag") String pflag,Model model) throws Exception{
        try {
            int pageNumber = Integer.parseInt(request.getParameter("pageNumber"));
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.getProductToPage(Integer.parseInt(pflag),pageNumber,pageSize);
            model.addAttribute("flag",Integer.parseInt(pflag));
            model.addAttribute("total",productPage.getTotalRecord());
            model.addAttribute("productPage",productPage);
        } catch (NumberFormatException e) {
            request.setAttribute("msg","分页查询失败");
        }
        return "admin/product_list";
    }

    /**搜索引擎的简单实现*/
    @RequestMapping(value = "/searchByPage")
    public String searchByPage(@RequestParam("pageNumber") String pageNumber,@RequestParam("searchContent") String searchContent,Model model) throws Exception{
        try {
            int pageNumber1 = Integer.parseInt(pageNumber);
            int pageSize = Constant.PRODUCT_PAGE_SIZE;
            Page<Product> productPage = productService.searchByPage(pageNumber1,pageSize,searchContent);
            model.addAttribute("productPage",productPage);
        } catch (NumberFormatException e) {
            return "redirect:index";
        }
        return "view/product_list";
    }


}
