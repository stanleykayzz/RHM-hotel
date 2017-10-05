package rhm.com.entities;

/**
 * Created by stanley on 02/09/2017.
 */

public class RoomCategory {
    private Long id;
    private String name;
    private double Price;

    public RoomCategory(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        Price = price;
    }

    public RoomCategory(){

    }


    public Long getId() {
        return id;
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

    public double getPrice() {
        return Price;
    }

    public void setPrice(double price) {
        Price = price;
    }
}
