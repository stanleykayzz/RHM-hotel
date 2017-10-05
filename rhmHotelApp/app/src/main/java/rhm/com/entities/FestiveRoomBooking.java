package rhm.com.entities;

/**
 * Created by stanley on 02/09/2017.
 */

public class FestiveRoomBooking {
    private Long id;
    private String start, end;
    private double price;


    public FestiveRoomBooking() {
    }

    public FestiveRoomBooking(Long id, String start, String end, double price) {
        this.id = id;
        this.start = start;
        this.end = end;
        price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double p) {
        this.price = p;
    }
}
