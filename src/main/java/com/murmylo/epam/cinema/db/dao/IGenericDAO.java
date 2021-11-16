package com.murmylo.epam.cinema.db.dao;

import com.murmylo.epam.cinema.db.entity.Entity;

import java.util.List;

public interface IGenericDAO<T extends Entity> {
    boolean insert(T entity);

    boolean update(T entity);

    boolean delete(T entity);

    T get(T entity);

    List<T> findAll();
}
