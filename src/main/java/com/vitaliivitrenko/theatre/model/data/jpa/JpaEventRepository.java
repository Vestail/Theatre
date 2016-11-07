package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.EventRepository;
import com.vitaliivitrenko.theatre.model.domain.Event;
import org.springframework.stereotype.Repository;

@Repository
public class JpaEventRepository extends JpaCrudRepository<Event, Long> implements EventRepository {

    public JpaEventRepository() {
        super(Event.class);
    }
}
