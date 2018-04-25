package com.vinspier.service;

import com.vinspier.pojo.User;

import java.util.List;

/**
 * Created by Administrator on 2017/11/20 0020.
 */
public interface UserService {
    void register(User user) throws Exception;
    User active(String code) throws Exception;
    void activeByUid(String uid) throws Exception;
    User login(String username, String password) throws Exception;
    User getInformation(String uid) throws Exception;
    void modifyUser(User user) throws Exception;
    void modifyPassword(String uid,String password) throws Exception;
    List<User> getAllUsers() throws Exception;
    List<User> getAllUsersNotActive() throws Exception;
    List<User> getAllUsersActive() throws Exception;
    int checkUserNameExist(String username)throws Exception;
    void deleteUserByUid(String uid) throws Exception;
}
