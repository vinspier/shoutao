package com.vinspier.controller;



import com.vinspier.pojo.Product;
import com.vinspier.pojo.User;
import com.vinspier.service.CartService;
import com.vinspier.service.ProductService;
import com.vinspier.utils.UUIDUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/27 0027.
 */
@Controller
public class CartController {
    private String cartItemId;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/addToCart")
    public String addToCart(HttpServletRequest request,HttpServletResponse response) throws Exception{
        try {

            User user = (User) request.getSession().getAttribute("user");
            if( user == null){
                return "redirect:login";
            }

            int count = Integer.parseInt(request.getParameter("count"));
            String pid = request.getParameter("pid");
            Product product = productService.getById(pid);

            List<com.vinspier.pojo.CartItem> cartItems = cartService.cartList(((User)request.getSession().getAttribute("user")).getUid());
            ArrayList<String> productIds = new ArrayList<String>();
            for(com.vinspier.pojo.CartItem cartItem:cartItems){
                productIds.add(cartItem.getProduct().getPid());
            }

            if(productIds.contains(pid)){
                for(com.vinspier.pojo.CartItem cartItem:cartItems){
                    if(cartItem.getProduct().getPid().equals(pid)){
                        cartItem.setCounts(count+cartItem.getCounts());
                        cartItem.setSubtotal(product.getShop_price()*1.0*cartItem.getCounts());
                        cartService.updateCart(cartItem);
                    }
                }
            }else{
                com.vinspier.pojo.CartItem cartItem = new com.vinspier.pojo.CartItem();
                cartItem.setUser(user);
                cartItemId = UUIDUtils.getId();
                cartItem.setItemId(cartItemId);
                cartItem.setCounts(count);
                cartItem.setSubtotal(product.getShop_price()*count);
                cartItem.setProduct(product);

                cartService.save(cartItem);
            }

        } catch (Exception e) {
            request.setAttribute("msg","添加购物车失败");
            return "view/notification_message";
        }
        return "redirect:/cart";
    }

    @RequestMapping(value = "/deleteFromCart")
    public String deleteFromCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        String pid = request.getParameter("itemId");
        cartService.deleteFromCart(pid);
        return "redirect:/cart";
    }

    @RequestMapping(value = "/cleanCart")
    public String cleanCart(HttpServletRequest request, HttpServletResponse response) throws Exception{
        cartService.cleanCart();
        return "redirect:/cart";
    }



    @RequestMapping(value = "/cart")
    public String listCart(HttpServletRequest request,Model model) throws Exception{
        List<com.vinspier.pojo.CartItem> cartItems = cartService.cartList(((User)request.getSession().getAttribute("user")).getUid());
        model.addAttribute("cartList",cartItems);
        return "view/cart";
    }



}
