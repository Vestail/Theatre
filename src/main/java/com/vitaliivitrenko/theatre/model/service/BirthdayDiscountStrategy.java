package com.vitaliivitrenko.theatre.model.service;


import com.vitaliivitrenko.theatre.model.domain.Event;
import com.vitaliivitrenko.theatre.model.domain.User;

import java.time.LocalDateTime;
import java.time.Period;

/**
 *
 * @author Vitalii Vitrenko
 */
public class BirthdayDiscountStrategy implements DiscountStrategy {

    private static final int DEFAULT_BIRTHDAY_RANGE = 5;
    private static final byte DEFAULT_DISCOUNT = 5;

    private int birthdayRange = DEFAULT_BIRTHDAY_RANGE;
    private byte discount = DEFAULT_DISCOUNT;

    @Override
    public byte count(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        if (user == null) {
            return 0;
        }
        int diff = Period.between(user.getBirthday(), airDateTime.toLocalDate()).getDays();
        if (diff >= 0 && diff <= birthdayRange) {
            return discount;
        }
        return 0;
    }

    public void setBirthdayRange(int birthdayRange) {
        this.birthdayRange = birthdayRange;
    }

    public void setDiscount(byte discount) {
        this.discount = discount;
    }

}
