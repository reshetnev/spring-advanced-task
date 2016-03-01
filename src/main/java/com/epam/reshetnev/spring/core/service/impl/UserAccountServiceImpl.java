package com.epam.reshetnev.spring.core.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.epam.reshetnev.spring.core.dao.UserAccountDao;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.UserAccount;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.UserAccountService;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

    private static final Logger log = Logger.getLogger(UserAccountServiceImpl.class);

    @Autowired
    private UserAccountDao userAccountDao;

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

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

    @Override
    public UserAccount getByUser(User user) {
        Optional<UserAccount> account = getAll()
                .stream()
                .filter(a -> a.getUserId().equals(user.getId()))
                .findFirst();

        if (!account.isPresent()) {
            log.info("UserAccount is not found with (User): " + user.toString());
            return null;
        }
        return account.get();
    }

    @Override
    @Transactional
    public void bookTicket(User user, Ticket ticket) throws Exception {
        Event event = eventService.getById(ticket.getEventId());
        LocalDate date = event.getDate();
        List<Integer> seats = Lists.newArrayList(ticket.getSeat());
        List<Double> prices = bookingService.getTicketPrices(event, date, seats, user);
        Double bill = prices.stream().mapToDouble(Double::doubleValue).sum();
        UserAccount account = getByUser(user);
        Double balance = account.getMoney() - bill;
        account.setMoney(balance);
        update(account);
        if (balance >= 0) {

            log.info("Balance for User: " + user.toString() +
                    " after booking ticket: " + ticket.toString() +
                    " for event: " + event.toString() +
                    " is: " + account.getMoney());
        } else {

            log.info("Money is not enough!");
            throw new Exception("Money is not enough! Rollback transaction.");
        }
    }

}
