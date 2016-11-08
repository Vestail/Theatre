package com.vitaliivitrenko.theatre.model.domain;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Map;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * @author Yuriy_Tkach
 */
@Entity
@Table(name = "\"user\"")
public class User extends DomainObject {

    private String firstName;

    private String lastName;

    private String email;

    private LocalDate birthday;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("purchaseDate")
    @MapKey(name = "purchaseDate")
    private SortedMap<LocalDate, Ticket> tickets = new TreeMap<>();

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public SortedMap<LocalDate, Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(Map<LocalDate, Ticket> tickets) {
        this.tickets = new TreeMap<>(tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, email);
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
        User other = (User) obj;
        if (email == null) {
            if (other.email != null) {
                return false;
            }
        } else if (!email.equals(other.email)) {
            return false;
        }
        if (firstName == null) {
            if (other.firstName != null) {
                return false;
            }
        } else if (!firstName.equals(other.firstName)) {
            return false;
        }
        if (lastName == null) {
            if (other.lastName != null) {
                return false;
            }
        } else if (!lastName.equals(other.lastName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return super.toString() + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + ", birthday=" + birthday;
    }

    
}
