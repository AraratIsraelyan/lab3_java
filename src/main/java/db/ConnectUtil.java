package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectUtil {
    private static final String url = "jdbc:mysql://localhost:3306/test";
    private static final String user = "root";
    private static final String password = "root";

    private static Connection connection;

    private static ConnectUtil instance;

    public static Connection getInstance() {
        if (instance == null) {
            instance = new ConnectUtil();
        }
        return connection;
    }

    private ConnectUtil() {
        connection = null;
        try {
            connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connection is OK!");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            System.out.println("Connection ERROR :/");
        } finally {
            // try { connection.close(); } catch(SQLException se) { /*can't do anything */ }
        }
    }


}