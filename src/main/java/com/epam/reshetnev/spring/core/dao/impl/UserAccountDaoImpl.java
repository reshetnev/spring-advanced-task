package com.epam.reshetnev.spring.core.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.epam.reshetnev.spring.core.dao.UserAccountDao;
import com.epam.reshetnev.spring.core.dao.impl.rowmapper.AccountRowMapper;
import com.epam.reshetnev.spring.core.domain.UserAccount;

@Repository
public class UserAccountDaoImpl implements UserAccountDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void save(UserAccount account) {
        jdbcTemplate.update("INSERT INTO accounts (id, userId, money) VALUES (?,?,?)",
                null,
                account.getUserId(),
                account.getMoney());
    }

    @Override
    public void delete(UserAccount account) {
        jdbcTemplate.update("DELETE FROM accounts WHERE accounts.id = ?",
                account.getId());
    }

    @Override
    public UserAccount getById(Integer id) {
        return jdbcTemplate.queryForObject("SELECT * FROM accounts WHERE accounts.id = ?",
                new Object[] {id},
                new AccountRowMapper());
    }

    @Override
    public List<UserAccount> getAll() {
        return jdbcTemplate.query("SELECT * FROM accounts",
                new AccountRowMapper());
    }

    @Override
    public void update(UserAccount account) {
        jdbcTemplate.update("UPDATE accounts SET userId = ?, money = ? WHERE accounts.id = ?",
                account.getUserId(),
                account.getMoney(),
                account.getId());
    }

}
