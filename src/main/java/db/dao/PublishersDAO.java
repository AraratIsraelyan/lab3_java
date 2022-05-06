package db.dao;
import db.entity.Publishers;

import java.sql.SQLException;
import java.util.List;

public interface PublishersDAO {

    //create
    void add(Publishers publisher) throws SQLException;

    //read
    List<Publishers> getAll() throws SQLException;

    Publishers getById(int id) throws SQLException;

    //update
    void update(Publishers publisher) throws SQLException;

    //delete
    void remove(Publishers publisher) throws SQLException;

}
