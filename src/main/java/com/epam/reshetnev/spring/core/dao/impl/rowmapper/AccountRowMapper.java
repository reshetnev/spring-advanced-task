package com.epam.reshetnev.spring.core.dao.impl.rowmapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.epam.reshetnev.spring.core.domain.UserAccount;

public class AccountRowMapper implements RowMapper<UserAccount> {

    @Override
    public UserAccount mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new UserAccount(rs.getInt("id"),
                rs.getInt("userId") != 0 ? rs.getInt("userId") : null,
                rs.getDouble("money"));
    }

}
