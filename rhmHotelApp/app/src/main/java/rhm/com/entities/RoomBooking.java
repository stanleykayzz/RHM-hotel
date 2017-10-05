package rhm.com.entities;

import java.util.Date;
import java.util.List;

/**
 * Created by stanley on 02/09/2017.
 */

public class RoomBooking {

    private Long ref;
    private String start, end, roomCategory;
    private Double price;
    private int quantity;
    private String reason;
/*
    public RoomBooking(Long ref, String start, String end, String roomCategory, Double price,int quantity) {
        this.ref = ref;
        this.start = start;
        this.end = end;
        this.roomCategory = roomCategory;
        this.price = price;
        this.quantity = quantity;
    }*/

    public RoomBooking(Long ref, String start, String end, String roomCategory, Double price) {
        this.ref = ref;
        this.start = start;
        this.end = end;
        this.roomCategory = roomCategory;
        this.price = price;
    }

    public RoomBooking(Long ref, String start, String end, String roomCategory, Double price, String reason) {
        this.ref = ref;
        this.start = start;
        this.end = end;
        this.roomCategory = roomCategory;
        this.price = price;
        this.reason = reason;
    }

    public RoomBooking(){

    }

    public Long getRef() {
        return ref;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRef(Long ref) {
        this.ref = ref;
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

    public String getRoomCategory() {
        return roomCategory;
    }

    public void setRoomCategory(String roomCategory) {
        this.roomCategory = roomCategory;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
