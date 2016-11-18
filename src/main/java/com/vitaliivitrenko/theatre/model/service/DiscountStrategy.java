package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.domain.Event;
import com.vitaliivitrenko.theatre.model.domain.User;

import java.time.LocalDateTime;

/**
 *
 * @author Vitalii Vitrenko
 */
public interface DiscountStrategy {

    byte count(User user, Event event, LocalDateTime airDateTime, long numberOfTickets);
}
