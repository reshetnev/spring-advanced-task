package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.reshetnev.spring.core.dao.UserAccountDao;
import com.epam.reshetnev.spring.core.domain.UserAccount;
import com.epam.reshetnev.spring.core.service.UserAccountService;
import com.google.common.base.Preconditions;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    private static final Logger log = Logger.getLogger(UserAccountServiceImpl.class);

    @Autowired
    private UserAccountDao userAccountDao;

    @Override
    public void save(UserAccount account) {
        userAccountDao.save(account);
    }

    @Override
    public void delete(UserAccount account) {
        userAccountDao.delete(account);
    }

    @Override
    public UserAccount getById(Integer id) {
        return userAccountDao.getById(id);
    }

    @Override
    public List<UserAccount> getAll() {
        return userAccountDao.getAll();
    }

    @Override
    public void update(UserAccount account) {
        Preconditions.checkNotNull(account.getId(), "UserAccount id should not be null");
        userAccountDao.update(account);
    }

    @Override
    public void saveAll(List<UserAccount> accounts) {
        accounts.forEach(account -> save(account));
    }

}
