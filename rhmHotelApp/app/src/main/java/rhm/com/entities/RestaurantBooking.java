package rhm.com.entities;

/**
 * Created by stanley on 02/09/2017.
 */

public class RestaurantBooking {

    private  String date, place;

    public RestaurantBooking(String date, String place) {
        this.date = date;
        this.place = place;
    }
    public RestaurantBooking() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
}
