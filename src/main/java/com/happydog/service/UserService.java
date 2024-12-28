package com.happydog.service;

import com.happydog.persistence.UserDao;

public class UserService {

//    public boolean login(String username, String password) {
//      }
//
//    public boolean register(String username, String password) {
//     }

    private UserDao userDao=new UserDao();
    public boolean changePassword(String username,String newPassword,String currentPassword){
        return userDao.changePassword(username,newPassword,currentPassword);

    }
    public boolean isUsernameExists(String username) {
        return userDao.checkUsername(username);
    }

    public boolean changeUsername(String username,String newUsername){

        return userDao.changeUsername(username,newUsername);
    }
}
