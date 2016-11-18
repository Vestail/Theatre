package com.vitaliivitrenko.theatre.model.service;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.data.Dao;
import ua.epam.spring.hometask.domain.User;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.Collection;
import java.util.Objects;

/**
 *
 * @author Vitalii_Vitrenko
 */
@Component
public class UserServiceImpl implements UserService {

    public Dao<User, Long> userDao;

    @Inject
    public UserServiceImpl(@Named("userDao") Dao<User, Long> userDao) {
        this.userDao = Objects.requireNonNull(userDao);
    }

    @Override
    public User getUserByEmail(String email) {
        return getAll().stream().filter(e -> e.getEmail().equals(email))
                .limit(1).findFirst().orElse(null);
    }

    @Override
    public User save(User user) {
        if (user.getId() == null) {
            userDao.create(user);
        } else {
            userDao.update(user);
        }
        return user;
    }

    @Override
    public void remove(User user) {
        userDao.delete(user);
    }

    @Override
    public User getById(Long id) {
        return userDao.read(id);
    }

    @Override
    public Collection<User> getAll() {
        return userDao.read();
    }

}
