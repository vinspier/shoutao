package com.vinspier.controller;

import com.vinspier.pojo.*;
import com.vinspier.service.CartService;
import com.vinspier.service.CategoryService;
import com.vinspier.service.ProductService;
import com.vinspier.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/8 0008.
 */
@Controller
public class ViewController {
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register")
    public String register_index() {
        return "view/register";
    }

    @RequestMapping(value = "/login")
    public String login_index(HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        if (user != null) {
            return "redirect:index";
        }
        return "view/login";
    }

    @RequestMapping(value = "/adminLogin")
    public String admin_login_index(HttpServletRequest request) {
        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        if (admin != null) {
            return "redirect:adminIndex";
        }
        return "admin/admin_login";
    }

    @RequestMapping(value = "/index")
    public String index(Model model) throws Exception {
        List<Product> hotProducts = productService.findHot();
        model.addAttribute("hotProducts", hotProducts);
        List<Product> newProducts = productService.findNew();
        model.addAttribute("newProducts", newProducts);
        return "view/index";
    }


    @RequestMapping(value = "/makeSureOrder")
    public String pay(HttpServletRequest request, Model model) throws Exception {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        CartItem cartItem = cartService.getCartItemById(request.getParameter("itemId"));
        cartItems.add(cartItem);
        model.addAttribute("total", cartItem.getSubtotal());
        model.addAttribute("cartToOrderItems", cartItems);
        return "view/order_make_sure";
    }

    @RequestMapping(value = "/makeSureOrders")
    public String createOrder1(@RequestParam("itemId") List<String> itemIds, Model model) throws Exception {
        List<CartItem> cartItems = new ArrayList<CartItem>();
        if (cartItems != null) {
            Double total = 0.0;
            for (String s : itemIds) {
                CartItem cartItem = cartService.getCartItemById(s);
                total += cartItem.getSubtotal();
                cartItems.add(cartItem);
            }
            model.addAttribute("total", total);
            model.addAttribute("cartToOrderItems", cartItems);
            return "view/order_make_sure";
        } else {
            return "redirect:cart";
        }
    }

    @RequestMapping(value = "/makeSureDirectBuy")
    public String directBuy(HttpServletRequest request,Model model) throws Exception{
        try {
            User user = (User) request.getSession().getAttribute("user");
            if( user == null){
                return "redirect:login";
            }
            int count = Integer.parseInt(request.getParameter("count"));
            String pid = request.getParameter("pid");
            Product product = productService.getById(pid);
            model.addAttribute("product",product);
            model.addAttribute("count",count);
            model.addAttribute("totalPay",count*product.getShop_price());
        } catch (Exception e) {
            request.setAttribute("msg","购买失败，青重新尝试");
            return "view/notification_message";
        }
        return "view/orderSure_directBuy";
    }

    @RequestMapping(value = "/changePassword")
    public String changePassword(HttpServletRequest request,Model model) throws Exception{
        User user = (User) request.getSession().getAttribute("user");
        model.addAttribute("user",user);
        return "view/change_password";
    }
    @RequestMapping(value = "/admin_changePassword")
    public String admin_changePassword(HttpServletRequest request,Model model) throws Exception{
        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        model.addAttribute("admin",admin);
        return "admin/change_password";
    }

    @RequestMapping(value = "/admin_addNew")
    public String admin_addNew() throws Exception{
        return "admin/admin_addNew";
    }
    @RequestMapping (value = "/category_add_form")
    public String category_add_form() throws Exception{
        return "admin/category_add";
    }

    @RequestMapping(value = "/product_upload_input")
    public String product_upload_input(Model model) throws Exception{
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/product_upload";
    }

    @RequestMapping(value = "/toEdit")
    public String toEdit(Model model,@RequestParam("pid") String pid) throws Exception{
        Product product = productService.getById(pid);
        model.addAttribute("product",product);
        List<Category> categoryList = categoryService.findAll();
        model.addAttribute("categoryList",categoryList);
        return "admin/product_edit";
    }

    @RequestMapping(value = "/to_suggest")
    public String toSuggest(Model model) throws Exception{
        List<Suggestion> suggestionList = userService.findAllSuggestion();
        model.addAttribute("suggestionList",suggestionList);
        return "view/user_suggest";
    }
}
