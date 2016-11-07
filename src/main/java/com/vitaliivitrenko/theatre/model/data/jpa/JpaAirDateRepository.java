package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.AirDateRepository;
import com.vitaliivitrenko.theatre.model.domain.AirDate;
import org.springframework.stereotype.Repository;

@Repository
public class JpaAirDateRepository extends JpaCrudRepository<AirDate, Long> implements AirDateRepository {

    public JpaAirDateRepository() {
        super(AirDate.class);
    }
}
