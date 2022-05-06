package db.entity;

public class Journals {

    private int id;
    private String name;
    private Publishers publishers;
    private int year;
    private int number;
    private Thematics thematics;

    public Journals() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Publishers getPublishers() {
        return publishers;
    }

    public void setPublishers(Publishers publishers) {
        this.publishers = publishers;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Thematics getThematics() {
        return thematics;
    }

    public void setThematics(Thematics thematics) {
        this.thematics = thematics;
    }

    @Override
    public String toString() {
        return "Журнал{" +
                "id = " + id +
                ", Название = '" + name + '\'' +
                ", Издатель = " + publishers.getName() +
                ", Год издания = " + year +
                ", Номер = " + number +
                ", Тип = " + thematics.getName() +
                "}\n";
    }
}
