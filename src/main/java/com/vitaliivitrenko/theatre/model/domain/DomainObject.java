package com.vitaliivitrenko.theatre.model.domain;

/**
 * @author Yuriy_Tkach
 */
public class DomainObject {

    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "id=" + id;
    }
}
