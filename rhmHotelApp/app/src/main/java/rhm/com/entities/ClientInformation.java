package rhm.com.entities;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.sql.Date;

/**
 * Created by alvin.ondzounga on 24/06/2017.
 */

public class ClientInformation {
    private String name;
    private String firstName;
    private Date birthday;
    private String email;
    private String phone;
    private String country;
    private String city;
    private String address;
    private String postalCode;
    private String password;
    private int leftIcon;
    private int rightIcon;
    private String info;
    private String title;

    public ClientInformation(){
    }
    public ClientInformation(String name, String firstName, Date birthday, String email, String phone, String country, String city, String address, String postalCode, String password, String token, Date tokenDate) {
        this.name = name;
        this.firstName = firstName;
        this.birthday = birthday;
        this.email = email;
        this.phone = phone;
        this.country = country;
        this.city = city;
        this.address = address;
        this.postalCode = postalCode;
        this.password = password;
    }

    public ClientInformation(String name, String firstName, String email, String phone) {
        this.name = name;
        this.firstName = firstName;
        this.email = email;
        this.phone = phone;
    }

    public ClientInformation(String key,String titre,String nometprenom,int leftPhoto,int righPhoto){
        this.title = titre;
        this.info = nometprenom;
        if(leftPhoto != 0)
            this.leftIcon = leftPhoto;
        if(righPhoto != 0)
            this.rightIcon = righPhoto;
    }

    public ClientInformation(String titre,String nometprenom,int l,int r){
        this.title = titre;
        this.info = nometprenom;
        if(l != 0)
            this.leftIcon = l;
        if(r != 0)
            this.rightIcon = r;
    }

    public int getLeftIcon() {
        return leftIcon;
    }

    public void setLeftIcon(int leftIcon) {
        this.leftIcon = leftIcon;
    }

    public int getRightIcon() {
        return rightIcon;
    }

    public void setRightIcon(int rightIcon) {
        this.rightIcon = rightIcon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName(){
        return getName()+ " "+getFirstName();
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}