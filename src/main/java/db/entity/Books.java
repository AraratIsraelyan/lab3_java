package db.entity;

public class Books {

    private int id;
    private String name;
    private int publisher_id;
    private int year;
    private int pages;
    private int cover_id;

    public Books() {
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

    public int getPublisher_id() {
        return publisher_id;
    }

    public void setPublisher_id(int publisher_id) {
        this.publisher_id = publisher_id;
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

    public int getCover_id() {
        return cover_id;
    }

    public void setCover_id(int cover_id) {
        this.cover_id = cover_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Books books = (Books) o;

        if (id != books.id) return false;
        if (publisher_id != books.publisher_id) return false;
        if (year != books.year) return false;
        if (pages != books.pages) return false;
        if (cover_id != books.cover_id) return false;
        return name != null ? name.equals(books.name) : books.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + publisher_id;
        result = 31 * result + year;
        result = 31 * result + pages;
        result = 31 * result + cover_id;
        return result;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher_id=" + publisher_id +
                ", year=" + year +
                ", pages=" + pages +
                ", cover_id=" + cover_id +
                '}';
    }
}
