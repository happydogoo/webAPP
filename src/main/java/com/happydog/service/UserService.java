package com.happydog.service;



import com.happydog.persistence.UserDao;

public class UserService {
    private UserDao userDao;

    public UserService() {
        userDao = new UserDao();
    }

    public boolean isUsernameExists(String username) {
        return userDao.checkUsername(username);
    }
}
