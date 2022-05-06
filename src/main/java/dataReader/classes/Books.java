package dataReader.classes;

import dataReader.libraryManagement.AbstractLiterature;

public class Books extends AbstractLiterature {

    protected String size;
    protected String cover;

    public String getSize(String size){
        return size;
    };
    public String getCover(String cover){
        return cover;
    };

    public void setSize(String size){
        this.size = size;
    };
    public void setCover(String cover){
        this.cover = cover;
    };

    @Override
    public String toString() {
        return super.toString() + "," + size + "," + cover + "\n";
    }

    @Override
    public String getType() {
        return "book";
    }

    @Override
    public void setData(String[] str) {
        super.setData(str);
        size = str[4];
        cover = str[5];
    }

}
