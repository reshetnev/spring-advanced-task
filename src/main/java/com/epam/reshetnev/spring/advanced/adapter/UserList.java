package com.epam.reshetnev.spring.advanced.adapter;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.User;

public class UserList {

    private List<User> users;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    
}
