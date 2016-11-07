package com.vitaliivitrenko.theatre.model.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Created by Vitalii_Vitrenko on 9/13/2016.
 */
public class AirDate extends DomainObject {

    private LocalDateTime dateTime;

    private Auditorium auditorium;

    public AirDate(LocalDateTime dateTime, Auditorium auditorium) {
        this.dateTime = Objects.requireNonNull(dateTime);
        this.auditorium = Objects.requireNonNull(auditorium);
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public Auditorium getAuditorium() {
        return auditorium;
    }

    @Override
    public String toString() {
        return "dateTime=" + dateTime +
                ", auditorium=" + auditorium ;
    }
}
