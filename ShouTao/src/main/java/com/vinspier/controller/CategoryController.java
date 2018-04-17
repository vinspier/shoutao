package com.vinspier.controller;

import com.vinspier.pojo.Category;
import com.vinspier.service.CategoryService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
