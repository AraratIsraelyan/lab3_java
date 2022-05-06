package db.services;

import db.ConnectUtil;
import db.dao.JournalsDAO;
import db.entity.Books;
import db.entity.Journals;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class JournalsService extends ConnectUtil implements JournalsDAO {
    Connection connection = getConnection();

    @Override
    public void add(Journals journal) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO journals (id, name, publisher_id, year, pages, cover_id) VALUES (?, ?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, journal.getId());
            preparedStatement.setString(2,journal.getName());
            preparedStatement.setInt(3, journal.getPublisher_id());
            preparedStatement.setInt(4, journal.getYear());
            preparedStatement.setInt(5, journal.getNumber());
            preparedStatement.setInt(6,journal.getThematic_id());
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
    public List<Journals> getAll() throws SQLException{
        List<Journals> journalsList = new ArrayList<>();
        String sql = "SELECT id, name FROM journals";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Journals journals = new Journals();
                journals.setId(resultSet.getInt("id"));
                journals.setName(resultSet.getString("name"));
                journals.setPublisher_id(resultSet.getInt("publisher_id"));
                journals.setYear(resultSet.getInt("year"));
                journals.setNumber(resultSet.getInt("number"));
                journals.setThematic_id(resultSet.getInt("thematic_id"));
                journalsList.add(journals);
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
        return journalsList;
    }

    @Override
    public Journals getById(int id) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM journals WHERE id=?";
        Journals journal = new Journals();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            journal.setId(resultSet.getInt("id"));
            journal.setName(resultSet.getString("name"));
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
        return journal;
    }

    @Override
    public void update(Journals journal) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE journals SET name=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,journal.getName());
            preparedStatement.setInt(2,journal.getId());
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
    public void remove(Journals journal) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM journals WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,journal.getId());
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
    public Journals getByPublisher(int publisher_id) throws SQLException{
        PreparedStatement preparedStatement = null;
        Journals journal = new Journals();
        String sql = "SELECT id, name, publisher_id, year, number, thematic_id FROM journals WHERE publisher_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,publisher_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            journal.setPublisher_id(resultSet.getInt("publisher_id"));
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
        return journal;
    }

    @Override
    public Journals getByThematic(int thematic_id) throws SQLException{
        PreparedStatement preparedStatement = null;
        Journals journal = new Journals();
        String sql = "SELECT id, name, publisher_id, year, pages, cover_id FROM journals WHERE thematic_id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,thematic_id);
            ResultSet resultSet = preparedStatement.executeQuery();
            journal.setThematic_id(resultSet.getInt("thematic_id"));
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
        return journal;
    }
}
