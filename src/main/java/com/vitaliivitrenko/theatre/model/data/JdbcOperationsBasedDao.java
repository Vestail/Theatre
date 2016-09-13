package com.vitaliivitrenko.theatre.model.data;

import javax.inject.Inject;
import org.springframework.jdbc.core.JdbcOperations;

/**
 *
 * @author Vitalii Vitrenko
 * @param <T>
 * @param <PK>
 */
public abstract class JdbcOperationsBasedDao<T, PK> implements Dao<T, PK> {

    private JdbcOperations jdbcOperations;

    @Inject
    public void setJdbcOperations(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    public JdbcOperations getJdbcOperations() {
        return jdbcOperations;
    }

}
