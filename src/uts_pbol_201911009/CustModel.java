/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.sql.Date;
import javafx.scene.control.DatePicker;

/**
 *
 * @author SUPER
 */
public class CustModel {
    
     private String idcust,nama, telpon, type,roomnumber,email;
 
    
    public String getIdcust() {
        return idcust;    }
    
    public void setIdcust(String idcust) {        
        this.idcust = idcust;        }
    
    public String getNama() {
        return nama;        }
    
    public void setNama(String nama) {        
        this.nama = nama;        }
    
    public String getTelpon() {
        return telpon;    
    }
    
    public void setTelpon(String telpon) {        
        this.telpon= telpon;    
    }
    
    public String getType() {
        return type;    
    }
    
    public void setType(String type) {        
        this.type = type;    
    }
     public String getRoomnumber() {
        return roomnumber;    
    }
    
    public void setRoomnumber(String roomnumber) {        
        this.roomnumber =roomnumber;    
    }
   
    public String getEmail() {
        return email;    }
    
    public void setEmail(String email) {        
        this.email = email;        }

   
    
    
}
