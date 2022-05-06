package db.services;

import db.ConnectUtil;
import db.dao.CoverDAO;
import db.dao.ThematicsDAO;
import db.entity.Covers;
import db.entity.Thematics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThematicsService extends ConnectUtil implements ThematicsDAO {
    Connection connection = getConnection();

    @Override
    public void add(Thematics thematic) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO thematics (id, name) VALUES (?, ?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, thematic.getId());
            preparedStatement.setString(2,thematic.getName());
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
    public List<Thematics> getAll() throws SQLException{
        List<Thematics> thematicsList = new ArrayList<>();

        String sql = "SELECT id, name FROM thematics";
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Thematics thematic = new Thematics();
                thematic.setId(resultSet.getInt("id"));
                thematic.setName(resultSet.getString("name"));

                thematicsList.add(thematic);
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
        return thematicsList;
    }

    @Override
    public Thematics getById(int id) throws SQLException{
        PreparedStatement preparedStatement = null;

        String sql = "SELECT id, name FROM thematics WHERE id=?";

        Thematics thematic = new Thematics();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                thematic.setId(resultSet.getInt("id"));
                thematic.setName(resultSet.getString("name"));
            }
            //preparedStatement.executeQuery();
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
        return thematic;
    }

    @Override
    public void update(Thematics thematic) throws SQLException{
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE thematics SET name=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,thematic.getName());
            preparedStatement.setInt(2,thematic.getId());
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
    public void remove(Thematics thematic) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM thematics WHERE id=?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,thematic.getId());
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
}

