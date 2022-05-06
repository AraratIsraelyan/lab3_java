package dataReader.classes;

import dataReader.interfaces.ReaderInterface;
import dataReader.libraryManagement.AbstractLiterature;
import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import dataReader.libraryManagement.QueryManagement;

public class Reader implements ReaderInterface {

    private final ArrayList<AbstractLiterature> literatureArrayList = new ArrayList<>();
    @Override
    public void read() throws FileNotFoundException {
        File file = new File(PATH + filename);
        String line;
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + filename));
            while ((line = br.readLine()) != null){
                readCatalog(line);
            }

        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readCatalog(String line) {
        String[] literature = line.split(",");
        if (literature[0].equals("book")) {
            Books book = new Books();
            book.setData(literature);
            literatureArrayList.add(book);
            System.out.println(book);
            QueryManagement.addPreparedLine(String.valueOf(book));
        }
        if(literature[0].equals("magazine")){
            Magazines mag = new Magazines();
            mag.setData(literature);
            literatureArrayList.add(mag);
        }

    }

    @Override
    public void saveFile() throws IOException{
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "output.csv"));
            for (AbstractLiterature l : literatureArrayList){
                writer.write(l.toString());
            }
            writer.flush();
            writer.close();
        }
        catch (IOException e){e.printStackTrace();
        }
    }

    @Override
    public void close() {

    }

}


