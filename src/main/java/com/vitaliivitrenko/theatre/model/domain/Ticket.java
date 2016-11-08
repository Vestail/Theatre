package com.vitaliivitrenko.theatre.model.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
public class Ticket extends DomainObject implements Comparable<Ticket> {

    @ManyToOne
    @JoinColumn(nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AirDate airDate;

    private LocalDateTime purchaseDate;

    private long seat;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public AirDate getAirDate() {
        return airDate;
    }

    public void setAirDate(AirDate airDate) {
        this.airDate = airDate;
    }

    public LocalDateTime getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(LocalDateTime purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public long getSeat() {
        return seat;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    @Override
    public int hashCode() {
        return Objects.hash(purchaseDate, airDate, seat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;

        Ticket ticket = (Ticket) o;

        if (getSeat() != ticket.getSeat()) return false;
        if (getUser() != null ? !getUser().equals(ticket.getUser()) : ticket.getUser() != null) {
            return false;
        }
        return getAirDate() != null ? getAirDate().equals(ticket.getAirDate()) : ticket.getAirDate() == null && (getPurchaseDate() != null ? getPurchaseDate().equals(ticket.getPurchaseDate()) : ticket.getPurchaseDate() == null);

    }

    @Override
    public int compareTo(Ticket other) {
        if (other == null) {
            return 1;
        }
        int result = purchaseDate.compareTo(other.getPurchaseDate());

        if (result == 0) {
            result = airDate.getId().compareTo(other.getAirDate().getId());
        }
        if (result == 0) {
            result = Long.compare(seat, other.getSeat());
        }
        return result;
    }

    @Override
    public String toString() {
        return super.toString() + ", user=" + user + ", AirDate=" + airDate + ", purchaseDate=" + purchaseDate + ", seat=" + seat;
    }


}
