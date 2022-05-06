package db.dao;

import db.entity.Books;

import java.sql.SQLException;
import java.util.List;

public interface ForeignTablesDAO<T> {

    //create
    void add(T t) throws SQLException;

    //read
    List<T> getAll() throws SQLException;

    T getById(int id) throws SQLException;

    //update
    void update(T t) throws SQLException;

    //delete
    void remove(T t) throws SQLException;

    T getOrAdd(String name) throws SQLException;

}
