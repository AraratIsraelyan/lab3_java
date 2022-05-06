package db.dao;
import db.entity.Books;

import java.sql.SQLException;
import java.util.List;

public interface BooksDAO {

    //create
    void add(Books book) throws SQLException;

    //read
    List<Books> getAll() throws SQLException;

    Books getById(int id) throws SQLException;

    //update
    void update(Books book) throws SQLException;

    //delete
    void remove(Books book) throws SQLException;

}
