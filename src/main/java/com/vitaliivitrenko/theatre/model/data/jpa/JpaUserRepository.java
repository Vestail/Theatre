package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.UserRepository;
import com.vitaliivitrenko.theatre.model.domain.User;
import org.springframework.stereotype.Repository;

@Repository
public class JpaUserRepository extends JpaCrudRepository<User, Long> implements UserRepository {

    public JpaUserRepository() {
        super(User.class);
    }
}
