package com.board.main.domain.user.entity;

public class Member {
    private String userId;
    private String userName;
    private String password;

    public String getUserId() {
        return userId;
    }

    public void setUserID(String userID) {
        this.userId = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
