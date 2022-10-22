/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

/**
 *
 * @author SUPER
 */
public class RoomModel {
    private String roomnumber, roomtype, status;
    private double price;
    

    
    public String getRoomnumber(){
        return this.roomnumber;
    }
    public void setRoomnumber(String roomnumber){
        this.roomnumber = roomnumber;
    }
    
    public String getRoomtype(){
        return this.roomtype;
    }
    public void setRoomtype(String roomtype){
        this.roomtype = roomtype;
    }
    public String getStatus(){
        return this.status;
    }
    public void setStatus(String status){
        this.status = status;
    }
    
    public Double getPrice(){
        return this.price;
    }
    public void setPrice (Double price){
        this.price = price;
    }
    
    
}
