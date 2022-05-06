package db.entity;

public class Journals {

    private int id;
    private String name;
    private int publisher_id;
    private int year;
    private int number;
    private int thematic_id;

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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getThematic_id() {
        return thematic_id;
    }

    public void setThematic_id(int thematic_id) {
        this.thematic_id = thematic_id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Journals journals = (Journals) o;

        if (id != journals.id) return false;
        if (publisher_id != journals.publisher_id) return false;
        if (year != journals.year) return false;
        if (number != journals.number) return false;
        if (thematic_id != journals.thematic_id) return false;
        return name != null ? name.equals(journals.name) : journals.name == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + publisher_id;
        result = 31 * result + year;
        result = 31 * result + number;
        result = 31 * result + thematic_id;
        return result;
    }

    @Override
    public String toString() {
        return "Journals{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", publisher_id=" + publisher_id +
                ", year=" + year +
                ", number=" + number +
                ", thematic_id=" + thematic_id +
                '}';
    }
}
