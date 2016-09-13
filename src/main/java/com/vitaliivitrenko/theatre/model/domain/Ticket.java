package com.vitaliivitrenko.theatre.model.domain;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class Ticket extends DomainObject implements Comparable<Ticket> {

    private User user;

    private Event event;

    private LocalDateTime purchaseDate;

    private long seat;

    public Ticket(User user, Event event, LocalDateTime dateTime, long seat) {
        this.user = user;
        this.event = event;
        this.purchaseDate = dateTime;
        this.seat = seat;
    }

    public User getUser() {
        return user;
    }

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public long getSeat() {
        return seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, event, seat);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Ticket other = (Ticket) obj;
        if (purchaseDate == null) {
            if (other.purchaseDate != null) {
                return false;
            }
        } else if (!purchaseDate.equals(other.purchaseDate)) {
            return false;
        }
        if (event == null) {
            if (other.event != null) {
                return false;
            }
        } else if (!event.equals(other.event)) {
            return false;
        }
        if (seat != other.seat) {
            return false;
        }
        return true;
    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = purchaseDate.compareTo(other.getPurchaseDate());

        if (result == 0) {
            result = event.getName().compareTo(other.getEvent().getName());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + ", user=" + user + ", event=" + event + ", purchaseDate=" + purchaseDate + ", seat=" + seat;
    }

    
}
