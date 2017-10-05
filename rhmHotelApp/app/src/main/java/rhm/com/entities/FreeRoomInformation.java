package rhm.com.entities;

import java.util.Objects;

/**
 * Created by stanley on 28/09/2017.
 */

public class FreeRoomInformation {
    private String typeRoomName;
    private double priceByNight;
    private double countNightsPrice ;
    private int countDays ;
    private int countAvailable;
    private int quantity;

    public FreeRoomInformation(){

    }

    public FreeRoomInformation(String typeRoomName, double priceByNight) {
        this.typeRoomName = typeRoomName;
        this.priceByNight = priceByNight;
    }

    public FreeRoomInformation(String typeRoomName, double priceByNight, int countDays) {
        this.typeRoomName = typeRoomName;
        this.priceByNight = priceByNight;
        this.countDays = countDays;
        setCountNightsPrice(this.countDays * this.priceByNight);
    }

    public FreeRoomInformation(String typeRoomName, double priceByNight, double countNightsPrice, int countDays, int countAvailable, int quantity) {
        this.typeRoomName = typeRoomName;
        this.priceByNight = priceByNight;
        this.countNightsPrice = countNightsPrice;
        this.countDays = countDays;
        this.countAvailable = countAvailable;
        this.quantity = quantity;
    }

    public String getTypeRoomName() {
        return typeRoomName;
    }

    public void setTypeRoomName(String typeRoomName) {
        this.typeRoomName = typeRoomName;
    }

    public double getPriceByNight() {
        return priceByNight;
    }

    public void setPriceByNight(double priceByNight) {
        this.priceByNight = priceByNight;
    }

    public double getCountNightsPrice() {
        return countNightsPrice;
    }

    public void setCountNightsPrice(double countNightsPrice) {
        this.countNightsPrice = countNightsPrice;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public int getCountAvailable() {
        return countAvailable;
    }

    public void setCountAvailable(int countAvailable) {
        this.countAvailable = countAvailable;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getPriceByNightToString() {
        return "Le prix / nuit : "+getPriceByNight();
    }

    public String getCountNightsPriceToString() {
        return "Le prix du séjour : "+getCountNightsPrice();

    }

    public String getCountDaysToString() {
        return "Le total de nuits : "+getCountDays();

    }

    public String getQuantityToString() {
        return "La quantité : "+getQuantity();
    }

    public String getCountAvailableToString() {
        return "Disponible : "+getCountAvailable();
    }

    @Override
    public int hashCode() {
        return Objects.hash(typeRoomName, priceByNight, countDays);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof FreeRoomInformation)) {
            return false;
        }
        FreeRoomInformation user = (FreeRoomInformation) obj;
        return  Objects.equals(typeRoomName, user.typeRoomName) &&
                priceByNight == user.priceByNight &&
                countDays == user.countDays /*&&
                countNightsPrice == user.countNightsPrice &&
                countAvailable == user.countAvailable &&
                quantity == user.quantity */;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /*
    public FreeRoomInformation() {
    }

    public FreeRoomInformation(String name,Double price){
        this.typeRoomName = name;
        this.priceValue = price;
    }

    public FreeRoomInformation(String name,Double price, String description){
        this.typeRoomName = name;
        this.priceValue = price;
        this.description = description;
    }

    public FreeRoomInformation(String typeRoomName, String description, String price, String currency, double priceValue, int countAvailable, int bookedQuantity) {
        this.typeRoomName = typeRoomName;
        this.description = description;
        this.price = price;
        this.currency = currency;
        this.priceValue = priceValue;
        this.countAvailable = countAvailable;
        this.bookedQuantity = bookedQuantity;
    }

    public String getTypeRoomName() {
        return typeRoomName;
    }

    public void setTypeRoomName(String typeRoomName) {
        this.typeRoomName = typeRoomName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getPriceValue() {
        return priceValue;
    }

    public void setPriceValue(double priceValue) {
        this.priceValue = priceValue;
    }

    public int getCountAvailable() {
        return countAvailable;
    }

    public String getCountOfRoom() {
        return "Il y a "+getCountAvailable()+" chambres disponible";
    }

    public String getPriceToString(){
        return getPrice()+" "+getPriceValue()+" "+getCurrency();
    }

    public void setCountAvailable(int countAvailable) {
        this.countAvailable = countAvailable;
    }

    public int getBookedQuantity() {
        return bookedQuantity;
    }

    public void setBookedQuantity(int bookedQuantity) {
        this.bookedQuantity = bookedQuantity;
    }*/
}