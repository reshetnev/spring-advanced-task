package com.epam.reshetnev.spring.core.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.reshetnev.spring.core.dao.UserDao;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;
import com.google.common.base.Preconditions;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final Logger log = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private TicketService ticketService;

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Override
    public User getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public User getByEmail(String email) {
        Optional<User> user = getAll()
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst();

        if (!user.isPresent()) {
            log.info("User is not found with (Email): " + email);
            return null;
        }

        return user.get();
    }

    @Override
    public List<User> getAllByName(String name) {
        return getAll()
                .stream()
                .filter(u -> u.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public List<Ticket> getBookedTickets(User user) {
        return ticketService.getAll()
                .stream()
                .filter(t -> (t.getUserId() != null
                                && t.getUserId().equals(user.getId())
                                && t.getIsPurchased()))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public void update(User user) {
        Preconditions.checkNotNull(user.getId(), "User id should not be null");
        userDao.update(user);
    }

    @Override
    public void saveAll(List<User> users) {
        users.forEach(user -> save(user));
    }

}
