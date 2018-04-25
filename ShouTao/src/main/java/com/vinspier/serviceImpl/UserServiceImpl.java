package com.vinspier.serviceImpl;

import com.vinspier.dao.UserDao;
import com.vinspier.pojo.User;
import com.vinspier.service.UserService;
import com.vinspier.utils.MailUtil;
import com.vinspier.utils.UUIDUtils;
import constant.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserDao userDao;

    /**用户注册*/
    public void register(User user) throws Exception{
        user.setRoleId(Constant.ROLE_CHARACTER_USER);
        user.setUid(UUIDUtils.getId());
        user.setCode(UUIDUtils.getCode());
        user.setState(Constant.USER_IS_NOT_ACTIVE);
        user.setBalance(Constant.USER_BALANCE);
        userDao.save(user);
        String emailMsg="恭喜"+user.getRealname()+":成为我们商城的一员,<a href='http://localhost:8080/Active?code="+user.getCode()+"'>点此激活</a>";
        MailUtil.sendMail(user.getUsername(),user.getEmail(),emailMsg);
    }
    /**
     * 用户激活
     */
    public User active(String code) throws Exception{
        User user = userDao.getByCode(code);
        if(user == null){
            return null;
        }
        user.setState(Constant.USER_IS_ACTIVE);
        user.setCode(null);
        userDao.active(user);
        return user;
    }
    /**
     * 用户登录
     */
    public User login(String username,String password) throws Exception{
        return userDao.getByUsernameAndPassword(username,password);
    }

    /**用户信息*/
    public User getInformation(String uid) throws Exception{
        return userDao.getUserByUid(uid);
    }

    /**更新用户信息*/
    public void modifyUser(User user) throws Exception{
        userDao.modify(user);
    }

   /** 修改用户密码*/
     public void modifyPassword(String uid,String password) throws Exception{
        userDao.modifyPassword(uid,password);
    }
   /** 获取所有用户信息*/
    public List<User> getAllUsers() throws Exception{
        return userDao.getAllUsers();
    }
    /** 获取未激活用户信息*/
    public List<User> getAllUsersNotActive() throws Exception{
        return userDao.getAllUsersNotActive(Constant.USER_IS_NOT_ACTIVE);
    }
    /** 获取已激活用户信息*/
    public List<User> getAllUsersActive() throws Exception{
        return userDao.getAllUsersActive(Constant.USER_IS_ACTIVE);
    }
    /**判断用户名是否已存在*/
    public int checkUserNameExist(String username)throws Exception{
        List<String> usernames = userDao.getAllUserName();
        if(!usernames.contains(username)){
            return Constant.USER_NAME_AVALIABLE;
        }
        if(usernames.contains(username)){
            return Constant.USER_NAME_INAVALIABLE;
        }
        return 0;
    }
}
