package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.data.TicketRepository;
import com.vitaliivitrenko.theatre.model.data.UserRepository;
import com.vitaliivitrenko.theatre.model.domain.*;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class BookingServiceImpl implements BookingService {

    private final static double HIGH_RATE_COEFFICIENT = 1.2;
    private final static double VIP_SEAT_COEFFICIENT = 2;

    private final DiscountService discountService;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    @Inject
    public BookingServiceImpl(DiscountService discountService,
                              TicketRepository ticketRepository,
                              UserRepository userRepository) {
        this.discountService = discountService;
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }


    @Override
    public void bookTickets(Set<Ticket> tickets) {
        for (Ticket ticket : tickets) {
            User user = ticket.getUser();
            if (user != null) {
                user.getTickets().add(ticket);
                userRepository.merge(user);
            }
            ticketRepository.create(ticket);
        }
    }

    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(Event event, LocalDateTime dateTime) {
        return ticketRepository.find().stream()
                .filter(e -> e.getAirDate().getEvent().equals(event) && e.getAirDate().getDateTime().equals(dateTime))
                .collect(Collectors.toSet());
    }

    @Override
    public double getTicketsPrice(Event event, LocalDateTime dateTime, User user, Set<Long> seats) {
        if (!event.airsOnDateTime(dateTime)) {
            throw new IllegalArgumentException("there's not event for this time");
        }

        AirDate airDate = event.getAirDates().get(dateTime);
        Auditorium auditorium = airDate.getAuditorium();

        double eventPrice = event.getBasePrice() * (event.getRating() == EventRating.HIGH ? HIGH_RATE_COEFFICIENT : 1);
        long numVipSeats = auditorium.countVipSeats(seats);
        double priceForAll = (seats.size() - numVipSeats) * eventPrice + numVipSeats * eventPrice * VIP_SEAT_COEFFICIENT;
        byte discount = discountService.getDiscount(user, event, dateTime, seats.size());
        return priceForAll - (discount / 10.0 * priceForAll);
    }
}
