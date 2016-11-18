package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.domain.Event;
import com.vitaliivitrenko.theatre.model.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.List;

@Component
public class DiscountServiceImpl implements DiscountService {

    private final List<DiscountStrategy> strategies;

    @Inject
    public DiscountServiceImpl(@Value("#{discountStrategies}") List<DiscountStrategy> strategies) {
        this.strategies = strategies;
    }

    @Override
    public byte getDiscount(User user, Event event, LocalDateTime airDateTime, long numberOfTickets) {
        return (byte) strategies.stream()
                .mapToInt(e -> e.count(user, event, airDateTime, numberOfTickets))
                .max().orElse(0);
    }

}
