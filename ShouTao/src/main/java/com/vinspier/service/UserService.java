package com.vinspier.service;

import com.vinspier.pojo.User;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public interface UserService {
    void register(User user) throws Exception;
    User active(String code) throws Exception;
    User login(String username, String password) throws Exception;
    User getInformation(String uid) throws Exception;
    void modifyUser(User user) throws Exception;
}