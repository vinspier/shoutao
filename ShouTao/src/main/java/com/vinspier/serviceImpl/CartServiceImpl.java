package com.vinspier.serviceImpl;

import com.vinspier.dao.CartDao;
import com.vinspier.pojo.CartItem;
import com.vinspier.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/1/2 0002.
 */
@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    public void save(CartItem cartItem)throws Exception{
        cartDao.addToCart(cartItem);
    }

    public void deleteFromCart(String itemId)throws Exception{
        cartDao.deleteFromCart(itemId);
    }

    public List<CartItem> cartList(String uid) throws  Exception{
        return cartDao.cartList(uid);
    }

    public void cleanCart()throws Exception{
        cartDao.cleanCart();
    }

    public void updateCart(CartItem cartItem)throws Exception{
        cartDao.updateCart(cartItem);
    }

    public CartItem getCartItemById(String itemId)throws Exception{
        return cartDao.getCartItemById(itemId);
    }
}
