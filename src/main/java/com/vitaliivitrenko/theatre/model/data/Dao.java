package com.vitaliivitrenko.theatre.model.data;

import java.util.Collection;

/**
 *
 * @author Vitalii_Vitrenko
 * @param <T> an entity type
 * @param <PK> a primary key type
 */
public interface Dao<T, PK> {
    
    void create(T entity);
    T read(PK primaryKey);
    Collection<T> read();
    void update(T entity);
    void delete(T entity);
}
