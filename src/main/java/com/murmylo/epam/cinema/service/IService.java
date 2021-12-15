package com.murmylo.epam.cinema.service;

import com.murmylo.epam.cinema.db.entity.Entity;

import java.sql.SQLException;
import java.util.List;

public interface IService<T extends Entity> {
    boolean insert(T entity) throws SQLException;

    boolean update(T entity) throws SQLException;

    boolean delete(T entity) throws SQLException;

    T get(T entity) throws SQLException;

    List<T> findAll() throws SQLException;
}
