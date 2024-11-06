package com.happydog.model;

public class User {
    private String userID;
    private String userEmail;
    private String userName;
    private String address;

    public String getUserID() {
        return userID;
    }



    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPreferences() {
        return preferences;
    }

    public void setPreferences(String preferences) {
        this.preferences = preferences;
    }

    public User(String userEmail, String userName, String address, String phone, String password,String userID) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.address = address;
        this.phone = phone;
        this.password = password;
        this.userID=userID;
    }

    private String phone;
    private String password;
    private String country;
    private String preferences;



}
