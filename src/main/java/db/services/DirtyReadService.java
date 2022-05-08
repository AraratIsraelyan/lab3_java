package db.services;

import db.ConnectUtil;
import db.entity.Books;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DirtyReadService {

    private Connection connection;

    private static final PublishersService publishersService = new PublishersService();

    private static final CoversService coversService = new CoversService();

    public DirtyReadService(Connection connection) {
        this.connection = connection;
    }

    public void add(Books book) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO books (name, publisher_id, year, pages, cover_id) VALUES (?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setInt(2, book.getPublishers().getId());
            preparedStatement.setInt(3, book.getYear());
            preparedStatement.setInt(4, book.getPages());
            preparedStatement.setInt(5,book.getCovers().getId());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public List<Books> getAll() throws SQLException{
        List<Books> booksList = new ArrayList<>();
        String sql = "SELECT id, name, publisher_id, year, pages, cover_id FROM books";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Books book = new Books();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPublishers(publishersService.getById(resultSet.getInt("publisher_id")));
                book.setYear(resultSet.getInt("year"));
                book.setPages(resultSet.getInt("pages"));
                book.setCovers(coversService.getById(resultSet.getInt("cover_id")));
                booksList.add(book);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (statement != null){
                statement.close();
            }
        }
        return booksList;
    }

    public void update(Books book) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE books SET name=?, publisher_id=?, year=?, pages=?, cover_id=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setInt(2,book.getPublishers().getId());
            preparedStatement.setInt(3,book.getYear());
            preparedStatement.setInt(4,book.getPages());
            preparedStatement.setInt(5,book.getCovers().getId());
            preparedStatement.setInt(6,book.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    public void remove(Books book) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM books WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,book.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }
}
