package test;

import com.vinspier.dao.CartDao;
import com.vinspier.dao.OrderDao;
import com.vinspier.dao.ProductDao;
import com.vinspier.dao.UserDao;
import com.vinspier.pojo.*;
import com.vinspier.service.AdministratorService;
import com.vinspier.service.CartService;
import com.vinspier.utils.MailUtil;
import com.vinspier.utils.UUIDUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26 0026.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml","classpath:mybatis-spring.xml"}) //加载配置文件
public class TestService {
    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private CartDao cartDao;
    @Autowired
    private CartService cartService;
    @Autowired
    private AdministratorService administratorService;


    @Test
    public void service() throws Exception{
        System.out.println(productDao.getTotalRecord("1",0));
    }

    @Test
    public void orderService() throws Exception{
        User user = userDao.getByCode("10BE097C3EA0421CBECD0A6596C1416B");
        System.out.println(user.getUid());
        List<Order> orders = orderDao.getOrderByUid(user.getUid());
        System.out.println(orders);
        for(Order order:orders){
            List<OrderItem> orderItems = orderDao.getOrderItems(order.getOid());
            for(OrderItem orderItem:orderItems){
                System.out.println(orderItem.getProduct().getPname());
            }
        }

    }

    @Test
    public void orderItemService() throws Exception{
        OrderItem orderItem = new OrderItem();
        orderItem.setItemid(UUIDUtils.getId());
        orderItem.setCounts(0);
        orderItem.setSubtotal(0.0);
        orderItem.setProduct(productDao.getById("1"));
        Order order = new Order();
        order.setOid("79A3788CE3DC48ED8527E5E50A1044CE");
        orderItem.setOrder(order);
        orderDao.createOrderItem(orderItem);
    }

    @Test
    public void getOrderByOrderID() throws Exception{
        Order order = orderDao.getOrderByOrderID("AAF4D054C64340DAA0C210FF14BC0A52");
        System.out.println(order);
    }

    @Test
    public void getOrdersByState() throws Exception{
        List<Order> orders = orderDao.getOrderByUidAndState("60D42C9F99A3454995D17F81544D46E0",1);
        for (Order order : orders){
            System.out.println(order);
        }
    }

    @Test
    public void cartList() throws Exception{
        List<CartItem> cartItems = cartDao.cartList("4EC82DF967834D31A84FAF2F9BA2E5AA");
        for (CartItem cartItem:cartItems){
            System.out.println(cartItem.getItemId());
        }
    }

    @Test
    public void addToCart() throws Exception{
        CartItem cartItem = new CartItem();
        cartItem.setItemId("12");
        cartItem.setCounts(12);
        cartItem.setSubtotal(12*23.0);
        cartItem.setProduct(productDao.getById("1"));
        cartItem.setUser(userDao.getByCode("35A8E482139A426084B4E38FF08F0444"));
        cartDao.addToCart(cartItem);
    }

    @Test
    public void deleteFromCart()throws Exception{
        cartDao.deleteFromCart("36267C52A6EA45EEA29FA213A8117E56");
    }

    @Test
    public void cleanCart()throws Exception{
        cartDao.cleanCart();
    }

    @Test
    public void updateCart()throws Exception{
        CartItem cartItem = new CartItem();
        cartItem.setItemId("2D273334F2A84D61BBD5EC60239ECCBD");
        cartItem.setCounts(3);
        cartItem.setSubtotal(100.0);
        cartDao.updateCart(cartItem);
    }

    @Test
    public void getCartById()throws  Exception{
        CartItem cartItem = cartService.getCartItemById("60D42C9F99A3454995D17F81544D46E0");
        System.out.println(cartItem);
    }

    @Test
    public void setCodeNull()throws Exception{
        User user = new User();
        user.setUid("939B6717977542668E71217C1D4288F0");
        user.setCode("");
        user.setState(0);
        userDao.active(user);
    }

    @Test
    public void setEmailMessage() throws Exception{
        String emailMsg="恭喜"+"FXB"+":成为我们商城的一员,<a href='http://localhost:8080/Active?code=afhahfkjahklfhlkadhlfka'>点此激活</a>";
        MailUtil emailUtil = new MailUtil();
        emailUtil.sendMail("fxb","954579147@qq.com",emailMsg);
    }

 /*   @Test
    public void searchByPage() throws Exception{
        List<Product> productList = productDao.searchByPage("华为",0,1,8);
        for(Product product: productList){
            System.out.println(product.getPname());
        }
    }*/

    @Test
    public void getOrderByState() throws Exception{
        List<Order> orders = administratorService.getOrderAllState();
        System.out.println(orders.size());
    }

    @Test
    public void test_suggest() throws Exception{
        Suggestion suggestion = new Suggestion();
        suggestion.setSuggest_content("AFHAKLFA");
        suggestion.setId(UUIDUtils.getId());
        suggestion.setLike_count(21);
        User user = new User();
        user.setUid("EDC429AE2F444BF982E328608F8DAC7B");
        suggestion.setUser(user);
        userDao.suggest(suggestion);
    }

    @Test
    public void test_search() throws Exception{
        String search = "%华为%";
        int up = 0;
        List<Product> productList = productDao.searchByPage(search,up);
        System.out.println(productList);
    }
}
