package dataReader.classes;

import dataReader.libraryManagement.AbstractLiterature;

public class Magazines extends AbstractLiterature {

    protected String number;
    protected String content;

    public String getNumber(String number){
        return number;
    };
    public String getContent(String content){
        return content;
    };

    public void setNumber(String number) {
        this.number = number;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return super.toString() + "," + number + "," + content + "\n";
    }

    @Override
    public String getType() {
        return "magazine";
    }

    @Override
    public void setData(String[] str) {
        super.setData(str);
        number = str[4];
        content = str[5];
    }
}
