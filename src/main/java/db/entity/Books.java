package db.entity;

public class Books {

    private int id;
    private String name;
    private Publishers publishers;
    private int year;
    private int pages;
    private Covers covers;

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

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Covers getCovers() {
        return covers;
    }

    public void setCovers(Covers covers) {
        this.covers = covers;
    }

    public Books() {
    }

    @Override
    public String toString() {
        return "Книга{" +
                "id=" + id +
                ", Название = '" + name + '\'' +
                ", Издатель = " + publishers.getName() +
                ", Год издания = " + year +
                ", Количество страниц =" + pages +
                ", Тип обложки =" + covers.getName() +
                "}\n";
    }
}
