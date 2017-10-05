package rhm.com.entities;

import java.io.Serializable;

/**
 * Created by alvin.ondzounga on 10/06/2017.
 */

public class Chambre implements Serializable{
    private int idRoom;
    private int number;
    private int photo;
    private String building;
    private String typeRoom;
    private double PriceRoom;

    public Chambre(int idRoom,int num,String building,String type){
        setIdRoom(idRoom);
        setNumber(num);
        setBuilding(building);
        setTypeRoom(type);
    }

    public Chambre(int num,String type, double price, int photo){
        setNumber(num);
        setPriceRoom(price);
        setTypeRoom(type);
        setPhoto(photo);
    }

    public Chambre(String type, double price){
        setTypeRoom(type);
        setPriceRoom(price);
    }

    public int getIdRoom(){
        return this.idRoom;
    }
    public int getNumber(){
        return this.number;
    }
    public int getPhoto(){
        return this.photo;
    }
    public void setPhoto(int p){
        this.photo = p;
    }
    public void setIdRoom(int id){
        this.idRoom = id;
    }
    public String getBuilding(){
        return this.building;
    }
    public String getTypeRoom(){
        return this.typeRoom;
    }
    public void setNumber( int num){
        this.number = num;
    }
    public void setBuilding(String buildingName){
        this.building = buildingName;
    }
    public void setTypeRoom(String t){
        this.typeRoom = t;
    }
    public double getPrice(){
        return this.PriceRoom;
    }
    public void setPriceRoom(double price){
        this.PriceRoom = price;
    }
    public String getPriceIntoString(){
        return Double.toString(getPrice());
    }

}
