package db.services;

import db.ConnectUtil;
import db.dao.BooksDAO;
import db.entity.Books;
import db.entity.Covers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksService implements BooksDAO {
    private static final Connection connection = ConnectUtil.getInstance();

    @Override
    public void add(Books book) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO books (id, name, publisher_id, year, pages, cover_id) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, book.getId());
            preparedStatement.setString(2,book.getName());
            preparedStatement.setInt(3, book.getPublisher_id());
            preparedStatement.setInt(4, book.getYear());
            preparedStatement.setInt(5, book.getPages());
            preparedStatement.setInt(6,book.getCover_id());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public List<Books> getAll() throws SQLException{
        List<Books> booksList = new ArrayList<>();
        String sql = "SELECT id, name FROM books";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Books book = new Books();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setPublisher_id(resultSet.getInt("publisher_id"));
                book.setYear(resultSet.getInt("year"));
                book.setPages(resultSet.getInt("pages"));
                book.setCover_id(resultSet.getInt("cover_id"));
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
            if (connection != null){
                connection.close();
            }
        }
        return booksList;
    }

    @Override
    public Books getById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM books WHERE id=?";
        Books book = new Books();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            book.setId(resultSet.getInt("id"));
            book.setName(resultSet.getString("name"));
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return book;
    }


    @Override
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
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public void update(Books book) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE books SET name=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,book.getName());
            preparedStatement.setInt(2,book.getId());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
    }

    @Override
    public Books getByPublisher(int publisher_id) throws SQLException{
        PreparedStatement preparedStatement = null;
        Books book = new Books();
        String sql = "SELECT id, name, publisher_id, year, number, thematic_id FROM publishers WHERE publisher_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,publisher_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            book.setPublisher_id(resultSet.getInt("publisher_id"));
            preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return book;
    }

    @Override
    public Books getByCover(int cover_id) throws SQLException{
        PreparedStatement preparedStatement = null;
        Books book = new Books();
        String sql = "SELECT id, name, publisher_id, year, pages, cover_id FROM books WHERE cover_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cover_id);

            ResultSet resultSet = preparedStatement.executeQuery();
            book.setCover_id(resultSet.getInt("cover_id"));
            preparedStatement.executeQuery();
        }
        catch (SQLException e){
            e.printStackTrace();
        }finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
            if (connection != null){
                connection.close();
            }
        }
        return book;
    }

}
