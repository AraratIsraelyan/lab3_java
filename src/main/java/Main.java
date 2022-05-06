import dataReader.classes.Reader;
import db.entity.Books;
import db.entity.Journals;
import db.services.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {

        Reader reader = new Reader();
        reader.read();
        reader.readCatalog("input_file.csv");
        reader.saveFile();

        JournalsService journalsService = new JournalsService();
        BooksService booksService = new BooksService();
        PublishersService publishersService = new PublishersService();
        ThematicsService thematicsService = new ThematicsService();
        CoversService coversService = new CoversService();
        try {
            Scanner scanner = new Scanner(System.in);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Вывод всех книг\n" + booksService.getAll() + '\n');
            System.out.println("Вывод всех журналов\n" + journalsService.getAll() + '\n');

            System.out.println("Введите id книги для удаления");
            int id = scanner.nextInt();
            booksService.remove(booksService.getById(id));
            System.out.println("Вывод всех книг\n" + booksService.getAll() + '\n');

            System.out.println("Введите id журнала, который надо поменять");
            id = scanner.nextInt();
            Journals journals = journalsService.getById(id);
            System.out.println("Введите через зяпятую данные, которые надо ввести");
            String[] changeString = bufferedReader.readLine().split(",");
            journals.setName(changeString[0]);
            journals.setPublishers(publishersService.getOrAdd(changeString[1]));
            journals.setYear(Integer.parseInt(changeString[2]));
            journals.setNumber(Integer.parseInt(changeString[3]));
            journals.setThematics(thematicsService.getOrAdd(changeString[4]));
            journalsService.update(journals);
            System.out.println("Вывод всех журналов\n" + journalsService.getAll() + '\n');

            Books books = new Books();
            System.out.println("Введите через зяпятую данные, которые надо ввести");
            String[] addString = bufferedReader.readLine().split(",");
            books.setName(addString[0]);
            books.setPublishers(publishersService.getOrAdd(addString[1]));
            books.setYear(Integer.parseInt(addString[2]));
            books.setPages(Integer.parseInt(addString[3]));
            books.setCovers(coversService.getOrAdd(addString[4]));
            booksService.add(books);
            System.out.println("Вывод всех книг\n" + booksService.getAll() + '\n');
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

}
