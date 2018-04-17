package com.vinspier.dao;

import com.vinspier.pojo.CartItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Repository
public interface CartDao {
    void addToCart(CartItem cartItem)throws Exception;
    void deleteFromCart(String itemId)throws Exception;
    List<CartItem> cartList(String uid) throws  Exception;
    void cleanCart()throws Exception;
    void updateCart(CartItem cartItem)throws Exception;
    CartItem getCartItemById(String itemId)throws Exception;

}
