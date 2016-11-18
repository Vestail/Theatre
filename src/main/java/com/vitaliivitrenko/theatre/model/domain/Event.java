package com.vitaliivitrenko.theatre.model.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

@Entity
public class Event extends DomainObject {

    private String name;

    private double basePrice;

    @Enumerated(EnumType.STRING)
    private EventRating rating;

    @OneToMany(mappedBy = "event", orphanRemoval = true)
    @OrderBy("dateTime")
    @MapKey(name = "dateTime")
    private SortedMap<LocalDateTime, AirDate> airDates = new TreeMap<>();

    /**
     * Add air date to event if there is no air date on airDate.getDateTime()
     * 
     * @param airDate Air date
     *
     * @return <code>true</code> if successful, otherwise <code>false</code>
     */
    public boolean addAirDate(AirDate airDate) {
        return airDates.putIfAbsent(airDate.getDateTime(), airDate) == null;
    }


    /**
     * Removes AirDate with given LocalDateTime
     * 
     * @param dateTime Date and time of AirDate to remove
     * @return <code>true</code> if successful, <code>false</code> if not there
     */
    public boolean removeAirDate(LocalDateTime dateTime) {
        return airDates.remove(dateTime) != null;
    }

    /**
     * Checks if event airs on particular date and time
     * 
     * @param dateTime
     *            Date and time to check
     * @return <code>true</code> event airs on that date and time
     */
    public boolean airsOnDateTime(LocalDateTime dateTime) {
        return airDates.containsKey(dateTime);
    }

    /**
     * Checks if event airs on particular date
     * 
     * @param date
     *            Date to ckeck
     * @return <code>true</code> event airs on that date
     */
    public boolean airsOnDate(LocalDate date) {
        return airDates.keySet().stream().anyMatch( k -> k.toLocalDate().equals(date));
    }

    /**
     * Checking if event airs on dates between <code>from</code> and
     * <code>to</code> inclusive
     * 
     * @param from
     *            Start date to check
     * @param to
     *            End date to check
     * @return <code>true</code> event airs on dates
     */
    public boolean airsOnDates(LocalDate from, LocalDate to) {
        return airDates.keySet().stream()
                .anyMatch(k -> k.toLocalDate().compareTo(from) >= 0 && k.toLocalDate().compareTo(to) <= 0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public EventRating getRating() {
        return rating;
    }

    public void setRating(EventRating rating) {
        this.rating = rating;
    }

    public NavigableMap<LocalDateTime, AirDate> getAirDates() {
        return new TreeMap<>(airDates);
    }

    public void setAirDates(NavigableMap<LocalDateTime, AirDate> airDates) {
        this.airDates = new TreeMap<>(airDates);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
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
        Event other = (Event) obj;
        if (name == null) {
            if (other.name != null) {
                return false;
            }
        } else if (!name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", name=" + name  + ", basePrice=" + basePrice + ", rating=" + rating;
    }
    
    

}
