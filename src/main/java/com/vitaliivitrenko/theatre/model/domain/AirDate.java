package com.vitaliivitrenko.theatre.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
public class AirDate extends DomainObject {

    private LocalDateTime dateTime;

    @OneToOne(optional = false)
    @JoinColumn
    private Auditorium auditorium;

    @ManyToOne(optional = false)
    @JoinColumn
    private Event event;

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }



    @Override
    public String toString() {
        return "dateTime=" + dateTime +
                ", auditorium=" + auditorium ;
    }
}
