package com.vitaliivitrenko.theatre.model.domain;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author Yuriy_Tkach, Vitalii Vitrenko
 */
@MappedSuperclass
public class DomainObject {

    @Id
    @GeneratedValue
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
