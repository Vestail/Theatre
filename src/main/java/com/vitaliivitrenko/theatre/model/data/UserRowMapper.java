package com.vitaliivitrenko.theatre.model.data;

import com.vitaliivitrenko.theatre.model.domain.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Vitalii_Vitrenko on 9/13/2016.
 */
@Component
public class UserRowMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        User user = new User();
        user.setId(rs.getLong("ID"));
        user.setFirstName(rs.getString("FIRST_NAME"));
        user.setLastName(rs.getString("LAST_NAME"));
        user.setBirthday(rs.getDate("BIRTHDAY").toLocalDate());
        user.setEmail(rs.getString("EMAIL"));
        return user;
    }
}
