package dataReader.libraryManagement;

import dataReader.interfaces.CatalogLiterature;
import db.entity.Books;

public abstract class AbstractLiterature implements CatalogLiterature {

    protected String type;
    protected String name;
    protected String publisher;
    protected String year;


    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getPublisher() {
        return publisher;
    }

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public String getType(){
        return "literature";
    };

    @Override
    public void setData(String[] strings) {
        name = strings[1];
        publisher = strings[2];
        year = strings[3];


    }

    @Override
    public String toString() {
        return getType() + "," + name + "," + publisher + "," + year;
    }

}


