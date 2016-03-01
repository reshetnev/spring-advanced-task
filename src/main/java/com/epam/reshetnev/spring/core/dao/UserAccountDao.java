package com.epam.reshetnev.spring.core.dao;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.UserAccount;

public interface UserAccountDao {

    public void save(UserAccount account);

    public void delete(UserAccount account);

    public UserAccount getById(Integer id);

    public List<UserAccount> getAll();

    public void update(UserAccount account);
}
