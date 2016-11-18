package com.vitaliivitrenko.theatre.model.service;

import com.vitaliivitrenko.theatre.model.domain.Auditorium;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

@Component
public class AuditoriumServiceImpl implements AuditoriumService {

    private final Map<String, Auditorium> auditoriums;

    @Autowired
    public AuditoriumServiceImpl(@Value("#{auditoriums}") Map<String, Auditorium> auditoriums) {
        this.auditoriums = Objects.requireNonNull(auditoriums);
    }

    @Override
    public Set<Auditorium> getAll() {
        return new HashSet<>(auditoriums.values());
    }

    @Override
    public Auditorium getByName(String name) {
        Objects.requireNonNull(name, "name cannot be null");
        return auditoriums.get(name);
    }

}
