package com.vinspier.controller;

import com.vinspier.pojo.Role;
import com.vinspier.pojo.User;
import com.vinspier.service.RoleService;
import com.vinspier.service.UserService;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;
    /**用户注册*/
    @RequestMapping(value = "/Register")
    public String register(HttpServletRequest request, @ModelAttribute User user) throws ServletException,IOException{
        try {
            user.setRoleId(Constant.ROLE_CHARACTER_USER);
            userService.register(user);
            request.setAttribute("msg","恭喜你,注册成功,请登录邮箱完成激活");
        } catch (Exception e) {
            request.setAttribute("msg","用户注册失败，请重新注册");
        }
        return "view/notification_message";
    }

    /**用户激活*/
    @RequestMapping(value = "/Active")
    public String active(HttpServletRequest request) throws ServletException,IOException{
        try {
            String code = request.getParameter("code");
            User user = userService.active(code);
            if(user == null){
                request.setAttribute("msg","激活失败，请重新注册或者重新激活");
                return "view/notification_message";
            }
            request.setAttribute("msg","激活成功，请<a href='http://localhost:8080/login'>登录</a>");
        } catch (Exception e) {
            request.setAttribute("msg","激活失败，请重新注册或者重新激活");
        }
        return "view/notification_message";
    }

    /**用户登录*/
    @RequestMapping(value = "/Login")
    public String login(HttpServletRequest request, HttpServletResponse response, @RequestParam("username") String username, @RequestParam("password") String password) throws ServletException,IOException{
        try {
            User loginUser = userService.login(username,password);
                if (loginUser != null) {
                    request.getSession().setAttribute("user", loginUser);
                    if (Constant.USER_IS_ACTIVE != loginUser.getState()) {
                        String emailMsg = "恭喜" + loginUser.getRealname() + ":成为我们商城的一员,请登录邮箱</a>激活";
                        System.out.println(emailMsg);
                        request.setAttribute("msg", "此用户未激活，请先登录邮箱激活后登录");
                        return "view/notification_message";
                    }
                    //将登陆的用户保存在session中
                    request.getSession().setAttribute("user", loginUser);


                    //判断是否记住用户名
                    if (Constant.SAVE_NAME.equals(request.getParameter("saveName"))) {
                        Cookie saveNameCookie = new Cookie("saveName", URLEncoder.encode(username, "utf-8"));
                        saveNameCookie.setPath(request.getContextPath() + "/");
                        saveNameCookie.setMaxAge(Integer.MAX_VALUE);
                        response.addCookie(saveNameCookie);
                    } else {
                        Cookie saveNameCookie = new Cookie("saveName", "");
                        saveNameCookie.setPath(request.getContextPath() + "/");
                        saveNameCookie.setMaxAge(0);
                        response.addCookie(saveNameCookie);
                    }
                    //判断自动登录
                    if (Constant.AUTO_LOGIN.equals(request.getParameter("autoLogin"))) {
                        Cookie autoLoginCookie = new Cookie("autoLoginCookie", URLEncoder.encode(loginUser.getUsername(), "utf-8") + "@" + URLEncoder.encode(loginUser.getPassword(), "utf-8"));
                        autoLoginCookie.setPath(request.getContextPath() + "/");
                        autoLoginCookie.setMaxAge(60 * 60 * 24 * 7);
                        response.addCookie(autoLoginCookie);
                    } else {
                        Cookie autoLoginCookie = new Cookie("autoLoginCookie", "");
                        autoLoginCookie.setPath(request.getContextPath() + "/");
                        autoLoginCookie.setMaxAge(0);
                        response.addCookie(autoLoginCookie);
                    }
                } else {
                    request.setAttribute("msg", "用户名或密码有误");
                    return "view/login";
                }

        } catch (Exception e) {
            request.setAttribute("msg","登录失败");
            return "view/notification_message";
        }
        return "redirect:index";
    }

    /**用户登出*/
    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request)throws Exception{
        request.getSession().invalidate();
        return "redirect:/index";
    }

  /**  获取用户信息    */
  @RequestMapping(value = "/userInformation")
  public String userInformation(@RequestParam("uid") String uid, Model model) throws Exception{
      model.addAttribute("user",userService.getInformation(uid));
      return "view/user_information";
  }
    /**  查询余额信息    */
    @RequestMapping(value = "/check_balance")
    public String check_balance(@RequestParam("uid") String uid, Model model) throws Exception{
        model.addAttribute("user",userService.getInformation(uid));
        return "view/check_balance";
    }


  /**更新用户信息*/
  @RequestMapping(value = "/modifyUserInformation")
    public String modifyUserInformation(HttpServletRequest request,@RequestParam("uid") String uid) throws Exception{
      User user = new User();
      user.setUid(uid);
      user.setUsername(request.getParameter("username"));
      user.setRealname(request.getParameter("realname"));
      user.setReceivedAddress(request.getParameter("receivedAddress"));
      user.setTelephone(request.getParameter("telephone"));
      user.setEmail(request.getParameter("email"));
      user.setSex(request.getParameter("sex"));
      user.setBirthday(request.getParameter("birthday"));
      userService.modifyUser(user);
      request.getSession().setAttribute("user",userService.getInformation(uid));
      request.setAttribute("msg","修改成功 <a href='http://localhost:8080/index'>返回首页</a>");
      return "view/notification_message";
  }

  /** 修改用户密码*/
  @RequestMapping(value = "/resetPassword")
  public String modifyPassword(HttpServletRequest request,@RequestParam("resetPassword") String resetPassword) throws Exception{
      try {
          User user = (User)request.getSession().getAttribute("user");
          userService.modifyPassword(user.getUid(),resetPassword);
          User resetUser = userService.login(user.getUsername(),resetPassword);
          request.getSession().setAttribute("user",resetUser);
          request.setAttribute("msg","修改密码成功，<a href='http://localhost:8080/index'>返回首页</a>");
      } catch (Exception e) {
          request.setAttribute("msg","修改密码失败，请重新修改");
          return "view/notification_message";
      }
      return "view/notification_message";
  }
}
