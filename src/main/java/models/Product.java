package models;

public class Product {

    private Long id;
    private Long category_id;
    private String name;
    private Double price;

    public Product(Long category_id,String name,Double price){
        this.category_id = category_id;
        this.name = name;
        this.price = price;
    }

    public Product(Long id, String name, Double price,Long category_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category_id = category_id;
    }

    public Long getId() {
        return id;
    }
    public Long getCategory_id() {
        return category_id;
    }

    public void setCategory_id(Long category_id) {
        this.category_id = category_id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + name + " - " + price + " - " + category_id;
    }
}
