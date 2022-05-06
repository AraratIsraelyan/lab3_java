package db.services;

import db.ConnectUtil;
import db.dao.ForeignTablesDAO;
import db.entity.Covers;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoversService implements ForeignTablesDAO<Covers> {
    private static final Connection connection = ConnectUtil.getInstance();

    @Override
    public void add(Covers cover) throws SQLException {
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO covers (name) VALUES (?)";

        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cover.getName());
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
    public List<Covers> getAll() throws SQLException{
        List<Covers> coversList = new ArrayList<>();

        String sql = "SELECT id, name FROM covers";
        Statement statement = null;

        try {
            statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()){
                Covers cover = new Covers();
                cover.setId(resultSet.getInt("id"));
                cover.setName(resultSet.getString("name"));

                coversList.add(cover);
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
        return coversList;
    }

    @Override
    public Covers getById(int id) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM covers WHERE id=?";
        Covers cover = new Covers();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cover.setId(resultSet.getInt("id"));
                cover.setName(resultSet.getString("name"));
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
        return cover;
    }

    @Override
    public void update(Covers covers) throws SQLException{
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE covers SET name=? WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,covers.getName());
            preparedStatement.setInt(2,covers.getId());
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
    public void remove(Covers cover) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "DELETE FROM covers WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,cover.getId());
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
    public Covers getOrAdd(String name) throws SQLException {

        Covers covers = getByName(name);

        if (covers == null) {
            covers = new Covers();
            covers.setName(name);
            add(covers);
            return getByName(name);
        }

        return covers;
    }

    private Covers getByName(String name) throws SQLException {

        PreparedStatement preparedStatement = null;
        String sql = "SELECT id, name FROM covers WHERE name=?";
        Covers cover = new Covers();
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, name);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null;
            }
            cover.setId(resultSet.getInt("id"));
            cover.setName(resultSet.getString("name"));
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            if (preparedStatement != null){
                preparedStatement.close();
            }
        }
        return cover;
    }


}
