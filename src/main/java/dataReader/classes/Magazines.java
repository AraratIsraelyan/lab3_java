package dataReader.classes;

import dataReader.libraryManagement.AbstractLiterature;
import db.entity.Journals;
import db.services.PublishersService;
import db.services.ThematicsService;

import java.sql.SQLException;

public class Magazines extends AbstractLiterature {

    protected String number;
    protected String thematic;

    public String getNumber(String number){
        return number;
    };
    public String getContent(String content){
        return content;
    };

    public void setNumber(String number) {
        this.number = number;
    }

    public void setThematic(String thematic) {
        this.thematic = thematic;
    }

    @Override
    public String toString() {
        return super.toString() + "," + number + "," + thematic + "\n";
    }

    @Override
    public String getType() {
        return "magazine";
    }

    @Override
    public void setData(String[] str) {
        super.setData(str);
        number = str[4];
        thematic = str[5];
    }

    public Journals convertData() {
        Journals journals = new Journals();

        journals.setName(this.name);
        PublishersService publishersService = new PublishersService();
        try {
            journals.setPublishers(publishersService.getOrAdd(this.publisher));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        journals.setYear(Integer.parseInt(this.year));
        journals.setNumber(Integer.parseInt(this.number));

        ThematicsService thematicsService = new ThematicsService();
        try {
            journals.setThematics(thematicsService.getOrAdd(this.thematic));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return journals;
    }
}
