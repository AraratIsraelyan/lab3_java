package dataReader.interfaces;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface ReaderInterface {

    final String dir = System.getProperty("user.dir");
    final String filename = "input_file.csv";
    final String PATH = dir + "\\src\\main\\java\\resources\\";
    final String line = "";

    void read() throws FileNotFoundException;

    void readCatalog(String line);

    void saveFile() throws IOException;

    void close();
}
