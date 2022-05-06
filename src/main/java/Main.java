// Вариант 11: книга, журнал
import dataReader.classes.Reader;
import db.ConnectUtil;
import db.entity.Books;
import db.entity.Covers;
import db.entity.Publishers;
import db.services.BooksService;
import db.services.CoversService;
import db.services.PublishersService;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException {

        Reader reader = new Reader();
        reader.read();
        reader.readCatalog("input_file.csv");

        reader.saveFile();
    }

}
