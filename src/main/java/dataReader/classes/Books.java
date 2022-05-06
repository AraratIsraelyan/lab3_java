package dataReader.classes;

import dataReader.libraryManagement.AbstractLiterature;
import db.services.CoversService;
import db.services.PublishersService;

import java.sql.SQLException;

public class Books extends AbstractLiterature {

    protected String pages;
    protected String cover;

    public String getPages(){
        return pages;
    };
    public String getCover(String cover){
        return cover;
    };

    public void setPages(String pages){
        this.pages = pages;
    };
    public void setCover(String cover){
        this.cover = cover;
    };

    @Override
    public String toString() {
        return super.toString() + "," + pages + "," + cover + "\n";
    }

    @Override
    public String getType() {
        return "book";
    }

    public db.entity.Books convertData() {
        db.entity.Books books = new db.entity.Books();

        books.setName(this.name);
        PublishersService publishersService = new PublishersService();
        try {
            books.setPublishers(publishersService.getOrAdd(this.publisher));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        books.setYear(Integer.parseInt(this.year));
        books.setPages(Integer.parseInt(this.pages));

        CoversService coversService = new CoversService();
        try {
            books.setCovers(coversService.getOrAdd(this.cover));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return books;
    }

    @Override
    public void setData(String[] str) {
        super.setData(str);
        pages = str[4];
        cover = str[5];
    }

}
