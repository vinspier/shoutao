package com.vinspier.controller;

import com.vinspier.pojo.Category;
import com.vinspier.service.CategoryService;
import com.vinspier.utils.UUIDUtils;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by Administrator on 2017/12/1 0001.
 */
@Controller
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    //  从数据库里取
    @RequestMapping(value = "/findCategory")
    public void findAllCategory(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html:charset=utf-8");
            PrintWriter out = response.getWriter();
            List<Category> categories = categoryService.findAll();
            JSONArray jsonArray = JSONArray.fromObject(categories);
            out.println(jsonArray.toString());
        } catch (Exception e) {
        }
    }

    @RequestMapping(value = "/category_list")
    public String category_list(Model model) throws Exception{
        model.addAttribute("categories",categoryService.findAll());
        return "admin/category_list";
    }

    @RequestMapping(value = "/category_add")
    public String category_add(@RequestParam("cname") String cname) throws Exception{
        Category category = new Category();
        category.setCid(UUIDUtils.getId());
        category.setCid(cname);
        categoryService.addCategory(category);
        return "redirect:category_list";
    }

    @RequestMapping(value = "/category_delete")
    public String category_delete(@RequestParam("cid") String cid) throws Exception{
        categoryService.deleteCategoryByCid(cid);
        return "redirect:category_list";
    }

    @RequestMapping(value = "/check_nameExists")
    public void check_nameExists(HttpServletRequest request, HttpServletResponse response) throws  Exception{
        try {
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html:charset=utf-8");
            PrintWriter out = response.getWriter();
            int name_avaliable = categoryService.checkName(request.getParameter("cname"));
            JSONArray jsonArray = JSONArray.fromObject(name_avaliable);
            out.println(jsonArray.toString());
        } catch (IOException e) {

        }
    }

}
