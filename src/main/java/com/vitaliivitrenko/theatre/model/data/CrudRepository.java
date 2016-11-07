/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vitaliivitrenko.theatre.model.data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Vitalii_Vitrenko
 */
public interface CrudRepository<T, PK extends Serializable> {

    T find(PK pk);

    List<T> find();

    List<T> find(int from, int to);

    void create(T entity);

    T merge(T entity);

    void delete(T entity);

    void delete(PK primaryKey);

    long size();

}
