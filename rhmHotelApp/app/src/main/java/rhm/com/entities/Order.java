package rhm.com.entities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import rhm.com.adapters.ClientInfoAdapter;
import rhm.com.adapters.OrderListAdapter;

/**
 * Created by alvin.ondzounga on 02/07/2017.
 */

public class Order implements Serializable {
    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate.toString();
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public Chambre getProduct() {
        return product;
    }

    public void setProduct(Chambre product) {
        this.product = product;
    }

    public String getMotive() {
        return motive;
    }

    public void setMotive(String motive) {
        this.motive = motive;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getProductPrice() {
        return getProduct().getPrice();
    }


    public Double getTotaleOderPrice() {
        return TotaleOderPrice;
    }

    public void setTotaleOderPrice(Double totaleOderPrice) {
        TotaleOderPrice = totaleOderPrice;
    }

    public Double getBookingFee() {
        return bookingFee;
    }

    public void setBookingFee(Double bookingFee) {
        this.bookingFee = bookingFee;
    }

    private String motive;
    private String orderDate;
    private Chambre product;
    private int quantity;
    private Double TotaleOderPrice;
    private Double bookingFee;

    public Order(String orderDate, Chambre product, int quantity) {
        this.orderDate = orderDate;
        this.product = product;
        this.quantity = quantity;
    }
    public Order(){

    }

    public void calculateTotalOrderPrice(){
        this.TotaleOderPrice = (this.quantity * this.product.getPrice());
    }
    public Double getTotalOrderPrice(){
        return (this.quantity * this.product.getPrice());
    }
    public void calculateBookingFee(){
        this.bookingFee = (this.TotaleOderPrice * 20)/100;
    }
}
