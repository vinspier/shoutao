package com.vinspier.controller;

import com.vinspier.entity.Page;
import com.vinspier.pojo.Product;
import com.vinspier.service.ProductService;
import constant.Constant;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/12/5 0005.
 */
@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @RequestMapping(value = "/getByPid")
    public String getByPid(@Param("pid") String pid,Model model) throws Exception{
        Product product = productService.getById(pid);
        model.addAttribute("product",product);
        return "view/product_info";
    }

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


}
