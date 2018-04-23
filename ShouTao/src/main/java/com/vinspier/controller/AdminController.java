package com.vinspier.controller;

import com.vinspier.pojo.Administrator;
import com.vinspier.pojo.Product;
import com.vinspier.service.AdministratorService;
import com.vinspier.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AdministratorService administratorService;

    @RequestMapping(value = "/admin_login")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("adminName") String name, @RequestParam("password") String password)throws Exception{
        try {
            Administrator admin = administratorService.getByNameAndPassword(name,password);
            if( admin != null){
                request.getSession().setAttribute("admin",admin);
                Cookie saveNameCookie = new Cookie("saveName", URLEncoder.encode(admin.getAdminName(), "utf-8"));
                saveNameCookie.setPath(request.getContextPath() + "/");
                saveNameCookie.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(saveNameCookie);
                return "redirect:adminIndex";
            }else{
                request.setAttribute("msg","账号或密码错误");
                return "admin/login";
            }
        } catch (Exception e) {
            request.setAttribute("msg","登录失败 请重新登录");
        }
        return null;
    }

    @RequestMapping(value = "/admin_logout")
    public String adminLogout(HttpServletRequest request) throws Exception{
        request.getSession().invalidate();
        return "redirect:adminLogin";
    }
  @RequestMapping(value = "/adminIndex")
    public String index(Model model) throws Exception {
        List<Product> hotProducts = productService.findHot();
        model.addAttribute("hotProducts", hotProducts);
        List<Product> newProducts = productService.findNew();
        model.addAttribute("newProducts", newProducts);
        return "admin/index";
    }
}
