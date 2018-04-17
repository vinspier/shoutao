package com.vinspier.service;

import com.vinspier.pojo.CartItem;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
public interface CartService {
    void save(CartItem cartItem)throws Exception;
    void deleteFromCart(String itemId)throws Exception;
    List<CartItem> cartList(String uid) throws  Exception;
    void cleanCart()throws Exception;
    void updateCart(CartItem cartItem)throws Exception;
    CartItem getCartItemById(String itemId)throws Exception;
}
