import dataReader.classes.Reader;
import db.ConnectUtil;
import db.entity.Books;
import db.entity.Covers;
import db.entity.Journals;
import db.entity.Publishers;
import db.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Runnable r1 = Main::transactionsOne;
        Runnable r2 = Main::transactionsTwo;

        Thread myThread1 = new Thread(r1);
        Thread myThread2 = new Thread(r2);
        myThread1.start();
        myThread2.start();

    }

    private static void transactionsOne() {
        Connection connection = ConnectUtil.getConnection();
        try {
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        DirtyReadService dirtyReadService = new DirtyReadService(connection);
        Books books = new Books();

        books.setName("threadPull1");
        PublishersService publishersService = new PublishersService();
        CoversService coversService = new CoversService();
        try {
            Publishers publishers = publishersService.getOrAdd("testtest");
            Covers covers = coversService.getOrAdd("testing");
            books.setPublishers(publishers);
            books.setYear(2142);
            books.setPages(2345);
            books.setCovers(covers);

            dirtyReadService.add(books);
            Thread.sleep(100);
            List<Books> booksList = dirtyReadService.getAll();
            System.out.println("Thread_1" + booksList);
            books = booksList.get(1);
            books.setName("threadPull2");
            books.setYear(4321);
            dirtyReadService.update(books);
            Thread.sleep(10000);
            connection.commit();
        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private static void transactionsTwo() {
        try {

            Connection connection = ConnectUtil.getConnection();
            connection.setTransactionIsolation(Connection.TRANSACTION_READ_UNCOMMITTED);
            connection.setAutoCommit(false);
            DirtyReadService dirtyReadService = new DirtyReadService(connection);
            Thread.sleep(150);
            System.out.println("Thread_2" + dirtyReadService.getAll());
            Books books = dirtyReadService.getAll().get(0);
            dirtyReadService.remove(books);
            System.out.println("Thread_2" + dirtyReadService.getAll());
            Thread.sleep(1000);
            System.out.println("Thread_2" + dirtyReadService.getAll());
            connection.commit();

        } catch (SQLException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


}
