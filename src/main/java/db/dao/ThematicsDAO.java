package db.dao;
import db.entity.Thematics;

import java.sql.SQLException;
import java.util.List;

public interface ThematicsDAO {

    //create
    void add(Thematics thematic) throws SQLException;

    //read
    List<Thematics> getAll() throws SQLException;

    Thematics getById(int id) throws SQLException;

    //update
    void update(Thematics thematic) throws SQLException;

    //delete
    void remove(Thematics thematic) throws SQLException;
}
