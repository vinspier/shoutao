package com.vinspier.controller;

import com.vinspier.pojo.*;
import com.vinspier.service.*;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private ProductService productService;
    @Autowired
    private AdministratorService administratorService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/admin_login")
    public String adminLogin(HttpServletRequest request, HttpServletResponse response, @RequestParam("adminName") String name, @RequestParam("password") String password)throws Exception{
        try {
            Administrator admin = administratorService.getByNameAndPassword(name,password);
            if( admin != null){
                request.getSession().setAttribute("admin",admin);

                Role role = roleService.getById(admin.getRoleId());
                if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMINISTRATOR)){
                    request.getSession().setAttribute("roleName","最高权限管理员");
                }
                if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMIN)){
                    request.getSession().setAttribute("roleName","普通权限管理员");
                }

                Cookie saveNameCookie = new Cookie("saveName", URLEncoder.encode(admin.getAdminName(), "utf-8"));
                saveNameCookie.setPath(request.getContextPath() + "/");
                saveNameCookie.setMaxAge(Integer.MAX_VALUE);
                response.addCookie(saveNameCookie);
                return "redirect:adminIndex";
            }else{
                request.setAttribute("msg","账号或密码错误");
                return "admin/admin_login";
            }
        } catch (Exception e) {
            request.setAttribute("msg","登录失败 请重新登录");
            return "admin/notification_message";
        }
    }

    @RequestMapping(value = "/admin_information")
    public String admin_information(HttpServletRequest request,@RequestParam("adminId") String adminId, Model model) throws Exception{
        Administrator admin = administratorService.getByAdminId(Integer.parseInt(adminId));
        model.addAttribute("admin",admin);
        return "admin/admin_information";
    }

    @RequestMapping(value = "/admin_list")
    public String admin_list(HttpServletRequest request,Model model)throws Exception{
        Administrator admin = (Administrator) request.getSession().getAttribute("admin");
        Role role = roleService.getById(admin.getRoleId());
        if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMINISTRATOR)){
            model.addAttribute("role","最高权限管理者");
            model.addAttribute("roleBiggest",1);
        }
        if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMIN)){
            model.addAttribute("role","普通管理者");
            model.addAttribute("roleBiggest",0);
        }
        List<Administrator> administrators = administratorService.adminList(admin.getAdminId());
        model.addAttribute("administrators",administrators);
        List<Role> roles = new ArrayList<Role>();
        for(Administrator administrator:administrators){
            roles.add(roleService.getById(administrator.getRoleId()));
        }
        model.addAttribute("roles",roles);
        return "admin/admin_list";
    }

    @RequestMapping(value = "/admin_resetPassword")
    public String admin_resetPassword(HttpServletRequest request,@RequestParam("resetPassword") String password) throws Exception{
        try {
            Administrator admin = (Administrator)request.getSession().getAttribute("admin");
            Administrator adminResetPassword = administratorService.resetPassword(admin.getAdminId(),password);
            request.getSession().setAttribute("admin",adminResetPassword);
            request.setAttribute("msg","修改密码成功，<a href='http://localhost:8080/adminIndex'>返回首页</a>");
        } catch (Exception e) {
            request.setAttribute("msg","修改密码失败，请重新修改");
            return "admin/notification_message";
        }
        return "admin/notification_message";
    }

    @RequestMapping(value = "/admin_delete")
    public String admin_delete(HttpServletRequest request,@RequestParam("adminId") String adminId) throws Exception{
        try {
            administratorService.deleteAdmin(Integer.parseInt(adminId));
            request.setAttribute("msg","删除成功，<a href='http://localhost:8080/admin_list'>返回查看</a>管理员列表");
        } catch (Exception e) {
            request.setAttribute("msg","删除失败，请重新操作");
            return "admin/notification_message";
        }
        return "admin/notification_message";
    }

    @RequestMapping(value = "/admin_add")
    public String admin_add(HttpServletRequest request,@ModelAttribute Administrator admin) throws Exception{
       int if_exist = administratorService.addNewAdmin(admin);
       if(if_exist == 0){
           return "redirect:admin_list";
       }else {
           request.setAttribute("msg","该名称已存在，请更换其他名称");
           return "admin/notification_message";
       }

    }

    @RequestMapping(value = "/admin_checkAllUsersByState")
    public String admin_checkAllUsers(@RequestParam("state") String state, Model model) throws Exception{
        int user_state = Integer.parseInt(state);
        List<User> users = new ArrayList<User>();
        if(user_state == 0){users = userService.getAllUsersNotActive();}
        if(user_state == 1){users = userService.getAllUsersActive();}
        if(user_state == 2){users = userService.getAllUsers();}
        model.addAttribute("users",users);
        model.addAttribute("state",Constant.USER_ALL);
        return "admin/user_list";
    }

    /**  获取用户信息    */
    @RequestMapping(value = "/admin_userInformation")
    public String admin_userInformation(@RequestParam("uid") String uid, Model model) throws Exception{
        model.addAttribute("user",userService.getInformation(uid));
        return "admin/user_information";
    }
    /**管理员修改用户信息*/
    @RequestMapping(value = "/admin_modifyUserInformation")
    public String admin_modifyUserInformation(HttpServletRequest request,@ModelAttribute User user)throws Exception{
        userService.modifyUser(user);
        request.getSession().setAttribute("user",userService.getInformation(request.getParameter("uid")));
        request.setAttribute("msg","修改成功 <a href='http://localhost:8080/adminIndex'>返回首页</a>");
        return "admin/notification_message";
    }
    /**管理员激活用户*/
    @RequestMapping(value = "/admin_activeUser")
    public String admin_activeUser(HttpServletRequest request,@RequestParam("uid") String uid) throws Exception{
        try {
            userService.activeByUid(uid);
            request.setAttribute("msg","激活成功，<a href='admin_checkAllUsersByState?state=1'>返回查看</a>已激活用户列表");
        } catch (Exception e) {
            request.setAttribute("msg","激活失败，请重新操作");
            return "admin/notification_message";
        }
        return "admin/notification_message";
    }
    @RequestMapping(value = "/admin_deleteUser")
    public String admin_deleteUser(HttpServletRequest request,@RequestParam("uid") String uid) throws Exception{
        userService.deleteUserByUid(uid);
        request.setAttribute("msg","删除成功，<a href='/admin_checkAllUsersByState?state=2'>返回</a>查看用户列表");
        return "admin/notification_message";
    }

    @RequestMapping(value = "/admin_resetPflag")
    public String admin_resetPflag(HttpServletRequest request,@RequestParam("pid") String pid,@RequestParam("flag") String pflag)throws Exception{
         String message =  productService.resetPflag(pid,Integer.parseInt(pflag));
         request.setAttribute("msg",message);
        return "admin/notification_message";
    }
    @RequestMapping(value = "/admin_resetIsHot")
    public String admin_resetIsHot(HttpServletRequest request,@RequestParam("pid") String pid,@RequestParam("is_hot") String is_hot)throws Exception{
           String message =  productService.resetIsHot(pid,Integer.parseInt(is_hot));
           request.setAttribute("msg",message);
        return "admin/notification_message";
    }
    @RequestMapping(value = "/admin_deleteProduct")
    public String admin_deleteProduct(HttpServletRequest request,@RequestParam("pid") String pid)throws Exception{
        try {
            productService.deleteProduct(pid);
            request.setAttribute("msg","删除成功");
        } catch (Exception e) {
            request.setAttribute("msg","删除失败 请重新尝试");
        }
        return "admin/notification_message";
    }

    @RequestMapping(value = "/getOrderByState")
    public String getOrderByState(HttpServletRequest request,@RequestParam("state") String state,Model model) throws Exception{
        List<Order> orderList = new ArrayList<Order>();
        try {
            if(Integer.parseInt(state) == Constant.ORDER_ALL_STATE){
                orderList = administratorService.getOrderAllState();
            }else {
                orderList = administratorService.getOrderByState(Integer.parseInt(state));
            }
            model.addAttribute("orderList",orderList);
            model.addAttribute("state",Integer.parseInt(state));
        }catch (Exception e){
            request.setAttribute("msg","查询失败请重新尝试");
            return "admin/notification_message";
        }
        return "admin/order_list";
    }

    @RequestMapping(value = "/admin_deleteOrderByOid")
    public String admin_deleteOrderByOid(@RequestParam("oid") String oid,HttpServletRequest request) throws Exception{
        try {
            orderService.orderDeleteByOid(oid);
            request.setAttribute("msg","删除成功 返回查看所有用户"+"<a href='/getOrderByState?state=4'>订单信息</a>");
        }catch (Exception e){
            request.setAttribute("msg","删除失败，请重新尝试");
        }
        return "admin/notification_message";
    }

    @RequestMapping(value = "/deliveryWriteNumber")
    public String deliveryWriteNumber(@RequestParam("uid") String uid,@RequestParam("oid") String oid,Model model) throws Exception{
        Order order = administratorService.getOrderByOid(uid,oid);
        model.addAttribute("order",order);
        System.out.println(order);
        return "admin/order_delivery";
    }

    @RequestMapping(value = "/admin_deliveryOrder")
    public String admin_deliveryOrder(@RequestParam("oid") String oid,@RequestParam("deliveryNumber") String deliveryNumber,HttpServletRequest request)throws Exception{
        try{
            administratorService.deliveryOrder(oid,deliveryNumber);
            request.setAttribute("msg","发货成功 返回查看其他"+"<a href='/getOrderByState?state=1'>未发货订单信息</a>");
        }catch (Exception e){
            request.setAttribute("msg","发货失败，请重新操作");
        }
        return "admin/notification_message";
    }

   @RequestMapping(value = "/adminIndex")
    public String index(HttpServletRequest request,Model model) throws Exception {
        Administrator admin = (Administrator)request.getSession().getAttribute("admin");
      Role role = roleService.getById(admin.getRoleId());
      if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMINISTRATOR)){
          request.getSession().setAttribute("adminLevel",1);
      }
      if(role.getRoleId().equals(Constant.ROLE_CHARACTER_ADMIN)){
          request.getSession().setAttribute("adminLevel",0);
      }
        List<Product> hotProducts = productService.findHot();
        model.addAttribute("hotProducts", hotProducts);
        List<Product> newProducts = productService.findNew();
        model.addAttribute("newProducts", newProducts);
        return "admin/index";
    }

    @RequestMapping(value = "/admin_logout")
    public String adminLogout(HttpServletRequest request) throws Exception{
        request.getSession().invalidate();
        return "redirect:adminLogin";
    }
}
