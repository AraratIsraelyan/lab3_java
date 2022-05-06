package db.entity;

public class Thematics {

    private int id;
    private String name;

    public Thematics(){

    }

    public Thematics(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "Thematics{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
