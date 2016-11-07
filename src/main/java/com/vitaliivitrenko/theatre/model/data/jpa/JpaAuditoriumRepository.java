package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.AuditoriumRepository;
import com.vitaliivitrenko.theatre.model.domain.Auditorium;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAuditoriumRepository extends JpaCrudRepository<Auditorium, Long> implements AuditoriumRepository {

    public JpaAuditoriumRepository() {
        super(Auditorium.class);
    }
}
