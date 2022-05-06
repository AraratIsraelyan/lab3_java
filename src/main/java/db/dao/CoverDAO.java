package db.dao;
import db.entity.Covers;
import db.entity.Publishers;

import java.sql.SQLException;
import java.util.List;

public interface CoverDAO {

    //create
    void add(Covers cover) throws SQLException;

    //read
    List<Covers> getAll() throws SQLException;

    Covers getById(int id) throws SQLException;

    //update
    void update(Covers cover) throws SQLException;

    //delete
    void remove(Covers cover) throws SQLException;

}
