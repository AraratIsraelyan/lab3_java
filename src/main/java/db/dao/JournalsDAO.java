package db.dao;

import db.entity.Journals;

import java.sql.SQLException;
import java.util.List;

public interface JournalsDAO {

    //create
    void add(Journals journal) throws SQLException;

    //read
    List<Journals> getAll() throws SQLException;

    Journals getById(int id) throws SQLException;

    //update
    void update(Journals journal) throws SQLException;

    //delete
    void remove(Journals journal) throws SQLException;

}
