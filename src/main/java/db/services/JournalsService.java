package db.services;

import db.ConnectUtil;
import db.dao.JournalsDAO;
import db.entity.Journals;

import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class JournalsService implements JournalsDAO {
    private static final Connection connection = ConnectUtil.getInstance();
    private static final PublishersService publisherService = new PublishersService();
    private static final ThematicsService thematicsService = new ThematicsService();

    @Override
    public void add(Journals journal) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO journals (name, publisher_id, year, number, thematic_id) VALUES (?, ?, ?, ?, ?)";
        try{
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,journal.getName());
            preparedStatement.setInt(2, journal.getPublishers().getId());
            preparedStatement.setInt(3, journal.getYear());
            preparedStatement.setInt(4, journal.getNumber());
            preparedStatement.setInt(5,journal.getThematics().getId());
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
    public List<Journals> getAll() throws SQLException{
        List<Journals> journalsList = new ArrayList<>();
        String sql = "SELECT id, name, publisher_id, year, number, thematic_id FROM journals";
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()){
                Journals journals = new Journals();
                journals.setId(resultSet.getInt("id"));
                journals.setName(resultSet.getString("name"));
                journals.setPublishers(publisherService.getById(resultSet.getInt("publisher_id")));
                journals.setYear(resultSet.getInt("year"));
                journals.setNumber(resultSet.getInt("number"));
                journals.setThematics(thematicsService.getById(resultSet.getInt("thematic_id")));
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
            if (resultSet.next()) {
                journal.setId(resultSet.getInt("id"));
                journal.setName(resultSet.getString("name"));
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
        return journal;
    }

    @Override
    public void update(Journals journal) throws SQLException{
        PreparedStatement preparedStatement = null;
        String sql = "UPDATE journals SET name=?, publisher_id=?, year=?, number=?, thematic_id=?  WHERE id=?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,journal.getName());
            preparedStatement.setInt(2,journal.getPublishers().getId());
            preparedStatement.setInt(3,journal.getYear());
            preparedStatement.setInt(4,journal.getNumber());
            preparedStatement.setInt(5,journal.getThematics().getId());
            preparedStatement.setInt(6,journal.getId());
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
        }
    }
}
