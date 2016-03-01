package com.epam.reshetnev.spring.core.service;

import java.util.List;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.UserAccount;

public interface UserAccountService {

    public void save(UserAccount account);

    public void delete(UserAccount account);

    public UserAccount getById(Integer id);

    public List<UserAccount> getAll();

    public void update(UserAccount account);

    public void saveAll(List<UserAccount> accounts);

    public UserAccount getByUser(User user);

    public void bookTicket(User user, Ticket ticket) throws Exception;
}
