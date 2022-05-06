package dataReader.interfaces;

public interface CatalogLiterature {

    void setName(String name);
    void setPublisher(String publisher);
    void setYear(String year);

    String getName();
    String getPublisher();
    String getYear();

    String getType();
    void setData(String[] strings);

}
