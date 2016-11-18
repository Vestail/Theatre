package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.domain.Event;
import com.vitaliivitrenko.theatre.model.domain.User;

import java.time.LocalDateTime;

/**
 *
 * @author Vitalii_Vitrenko
 */
public class CountTicketsDiscountStrategy implements DiscountStrategy {

    private static final int DISCOUNT_TICKETS_NUMBER = 10;
    private static final byte DISCOUNT_BY_TICKET = 50;

    @Override
    public byte count(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        long preNumber = 0;
        if (user != null) {
            preNumber = user.getTickets().size() % DISCOUNT_TICKETS_NUMBER;
        }
        long discountTickets = (numberOfTickets + preNumber) / DISCOUNT_TICKETS_NUMBER;
        return (byte) ((discountTickets * 100.) / numberOfTickets * (DISCOUNT_BY_TICKET / 100.));
    }

}
