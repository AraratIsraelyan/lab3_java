package dataReader.libraryManagement;

import dataReader.interfaces.MySQL;
import db.ConnectUtil;
import db.entity.Books;
import db.entity.Covers;
import db.entity.Publishers;
import db.services.BooksService;
import db.services.CoversService;
import db.services.PublishersService;
import java.sql.*;
import java.sql.SQLException;
import db.ConnectUtil;

public class QueryManagement implements MySQL{

    private static final Connection connection = ConnectUtil.getInstance();


    public static void addPreparedLine(String data) {
        PreparedStatement preparedStatement = null;
        String SPLITTER = ",";
        String[] param = data.split(SPLITTER);

        Publishers publisher = new Publishers();

        try {
            preparedStatement.setString(1, param[0]);
            preparedStatement.setString(2, param[1]);
            preparedStatement.setString(3, param[2]);
            preparedStatement.setString(4, param[3]);
            preparedStatement.setString(5, param[4]);
            preparedStatement.executeUpdate();
        }
        catch (SQLException e) {
            System.err.println("Ошибка при отправки команды -- addPreparedLine --");
        }
    }

    public void createNewBook(Books book){
        Publishers publisher = new Publishers();
        publisher.setId(1);
        publisher.setName("АСТ");
        PublishersService publishersService = new PublishersService();

        Covers cover = new Covers();
        cover.setId(1);
        cover.setName("Твердая");
        CoversService coverService = new CoversService();

        //Books book = new Books();
        book.setId(1);
        book.setName("Криптономикон");
        book.setPublisher_id(1);
        book.setYear(2020);
        book.setPages(788);
        book.setCover_id(1);
        BooksService booksService = new BooksService();

        try {
            publishersService.add(publisher);
            coverService.add(cover);
            booksService.add(book);
        }
        catch (SQLException e) {
            System.out.println(e.getMessage());
            System.out.println("couldn't connect!");
            throw new RuntimeException(e);
        }
    }
    protected String query;

    @Override
    public String getQuery(){
        return query;
    }

    @Override
    public void setQuery(String query){
        this.query = query;
    }
}

