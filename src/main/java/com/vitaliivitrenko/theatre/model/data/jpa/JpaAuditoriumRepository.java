package com.vitaliivitrenko.theatre.model.data.jpa;

import com.vitaliivitrenko.theatre.model.data.AuditoriumRepository;
import com.vitaliivitrenko.theatre.model.domain.*;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class JpaAuditoriumRepository extends JpaCrudRepository<Auditorium, Long> implements AuditoriumRepository {

    public JpaAuditoriumRepository() {
        super(Auditorium.class);
    }

    public List<Auditorium> findForEvent(Event event) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Auditorium> query =  builder.createQuery(Auditorium.class);
        Root<Event> events =  query.from(Event.class);
        Join<Event, AirDate> airDates = events.join(Event_.airDates);
        Join<AirDate, Auditorium> auditorium = airDates.join(AirDate_.auditorium);
        Predicate equalEvent =  builder.equal(airDates.get(AirDate_.event), event);
        return getEntityManager().createQuery(query.where(equalEvent)).getResultList();
    }
}
