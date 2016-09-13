package com.vitaliivitrenko.theatre.model.data;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;

import com.vitaliivitrenko.theatre.model.domain.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import javax.inject.Inject;

/**
 *
 * @author Vitalii Vitrenko
 */
@Repository
public class UserJdbcOperationsBasedDao extends JdbcOperationsBasedDao<User, Long> {

    private static final String INSERT_QUERY = "INSERT INTO user VALUES(?, ?, ?, ?)";
    private static final String DELETE_QUERY = "DELETE FROM user WHERE id = ?";
    private static final String READ_QUERY = "SELECT * FROM user WHERE id = ?";
    private static final String READ_ALL_QUERY = "SELECT * FROM user";
    private static final String UPDATE_QUERY = "UPDATE user SET first_name = ?, last_name = ?, "
            + "birthday = ?, email = ?, WHERE ID = ?";

    private RowMapper<User> rowMapper;

    @Inject
    public UserJdbcOperationsBasedDao(RowMapper<User> rowMapper) {
        this.rowMapper = rowMapper;
    }


    @Override
    public void create(User user) {
        KeyHolder generated = new GeneratedKeyHolder();
        getJdbcOperations().update(conn -> {
            PreparedStatement ps = conn.prepareStatement(INSERT_QUERY, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setDate(4, Date.valueOf(user.getBirthday()));

            return ps;
        }, generated);
        user.setId(generated.getKey().longValue());
    }

    @Override
    public void delete(User user) {
        getJdbcOperations().update(DELETE_QUERY, user.getId());
    }

    @Override
    public User read(Long id) {
        try {
            return getJdbcOperations().queryForObject(READ_QUERY, rowMapper, id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public Collection<User> read() {
        return getJdbcOperations().query(READ_ALL_QUERY, rowMapper);
    }

    @Override
    public void update(User user) {
        getJdbcOperations().update(UPDATE_QUERY,
                user.getFirstName(),
                user.getLastName(),
                user.getBirthday(),
                user.getId());
    }

}
