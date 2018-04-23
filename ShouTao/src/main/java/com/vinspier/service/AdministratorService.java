package com.vinspier.service;

import com.vinspier.pojo.Administrator;

public interface AdministratorService {
    Administrator getByNameAndPassword(String name, String password) throws Exception;
}
