package db.services;

import db.ConnectUtil;
import db.dao.ForeignTablesDAO;
import db.entity.Thematics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ThematicsService implements ForeignTablesDAO<Thematics> {
    private static final Connection connection = ConnectUtil.getInstance();

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
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
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
        }

    }

    @Override
    public Thematics getOrAdd(String name) throws SQLException {
        Thematics thematics = getByName(name);

        if (thematics == null) {
            thematics = new Thematics();
            thematics.setName(name);
            add(thematics);
            return getByName(name);
        }

        return thematics;
    }

    private Thematics getByName(String name) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM thematics WHERE name=?";
        Thematics thematics = new Thematics();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            thematics.setId(resultSet.getInt("id"));
            thematics.setName(resultSet.getString("name"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        return thematics;
    }
}

