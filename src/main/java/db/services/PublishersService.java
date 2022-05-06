package db.services;

import db.ConnectUtil;
import db.dao.ForeignTablesDAO;
import db.entity.Covers;
import db.entity.Publishers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PublishersService implements ForeignTablesDAO<Publishers> {

    private static final Connection connection = ConnectUtil.getInstance();

    @Override
    public void add(Publishers publisher) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO publishers (id, name) VALUES (?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, publisher.getId());
            preparedStatement.setString(2,publisher.getName());
            preparedStatement.executeUpdate();
        }
        catch (SQLException e){e.printStackTrace();}
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
    }

    @Override
    public List<Publishers> getAll() throws SQLException{
        List<Publishers> publishersList = new ArrayList<>();

        String sql = "SELECT id, name FROM publishers";
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Publishers publisher = new Publishers();
                publisher.setId(resultSet.getInt("id"));
                publisher.setName(resultSet.getString("name"));

                publishersList.add(publisher);
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
        return publishersList;
    }

    @Override
    public Publishers getById(int id) throws SQLException{
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, name FROM publishers WHERE id=?";

        Publishers publisher = new Publishers();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                publisher.setId(resultSet.getInt("id"));
                publisher.setName(resultSet.getString("name"));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        return publisher;
    }

    @Override
    public void update(Publishers publisher) throws SQLException{
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE publishers SET name=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,publisher.getName());
            preparedStatement.setInt(2,publisher.getId());
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

    @Override
    public void remove(Publishers publisher) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM publisher WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,publisher.getId());
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

    @Override
    public Publishers getOrAdd(String name) throws SQLException {
        Publishers publishers = getByName(name);

        if (publishers == null) {
            publishers = new Publishers();
            publishers.setName(name);
            add(publishers);
            return getByName(name);
        }

        return publishers;
    }

    private Publishers getByName(String name) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM publishers WHERE name=?";
        Publishers publishers = new Publishers();

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            publishers.setId(resultSet.getInt("id"));
            publishers.setName(resultSet.getString("name"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        return publishers;
    }
}
