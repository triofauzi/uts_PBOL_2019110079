/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author SUPER
 */
public class DBCheckin {
    
     private CustModel dt=new CustModel();    
    public CustModel getCustModel(){ return(dt);}
    public void setCustModel(CustModel s){ dt=s;}
    
    public ObservableList<CustModel>  Load() {
        try {
            ObservableList<CustModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
             ResultSet rs = con.statement.executeQuery("Select idcust, nama, telpon, type, roomnumber, email from customer");
int i = 1;
            while (rs.next()) {
                CustModel d=new CustModel();
                d.setTelpon(rs.getString("telpon"));                
                d.setIdcust(rs.getString("idcust"));  
                d.setNama(rs.getString("nama"));
                d.setType(rs.getString("type"));
                d.setRoomnumber(rs.getString("roomnumber"));
                d.setEmail(rs.getString("email"));  
                tableData.add(d);                
                i++;            
            }
            con.tutupKoneksi();            
            return tableData;
        } catch (Exception e) {            
            e.printStackTrace();            
            return null;        
        }
    }  
    
     public int validasi(String nomor) {
        int val = 0;
        try {         
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from customer where idcust = '" + nomor + "'");
            while (rs.next()) {                
                val = rs.getInt("jml");            
            }            
            con.tutupKoneksi();
        } catch (SQLException e) {            
            e.printStackTrace();        
        }
        return val;
    }
    
      public boolean insert() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {       
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into customer ( idcust, nama, telpon,type, roomnumber,email) values (?,?,?,?,?,?)");
            con.preparedStatement.setString(1, getCustModel().getIdcust());           
            con.preparedStatement.setString(2, getCustModel().getNama());
            con.preparedStatement.setString(3, getCustModel().getTelpon());
            con.preparedStatement.setString(4, getCustModel().getType());
            con.preparedStatement.setString(5, getCustModel().getRoomnumber());
            con.preparedStatement.setString(6, getCustModel().getEmail());
             con.preparedStatement.executeUpdate();
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
     }
      
     public boolean delete(String nomor) {
    boolean berhasil = false;        
    Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();;
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from customer where idcust  = ? ");
            con.preparedStatement.setString(1, nomor);
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }
    } 
     
     public boolean update() {
        boolean berhasil = false;        
        Koneksi con = new Koneksi();
        try {            
            con.bukaKoneksi();
            con.preparedStatement = con.dbKoneksi.prepareStatement("update customer set idcust = ?, telpon = ?, type = ?, roomnumber = ? ,email= ? where nama = ? ");
            con.preparedStatement.setString(1, getCustModel().getIdcust());           
            con.preparedStatement.setString(2, getCustModel().getTelpon()); 
            con.preparedStatement.setString(3, getCustModel().getType());
            con.preparedStatement.setString(4, getCustModel().getRoomnumber());
            con.preparedStatement.setString(5, getCustModel().getEmail());
             con.preparedStatement.setString(6, getCustModel().getNama());
            con.preparedStatement.executeUpdate();            
            berhasil = true;
        } catch (Exception e) {            
            e.printStackTrace();            
            berhasil = false;
        } finally {            
            con.tutupKoneksi();            
            return berhasil;        
        }    
        }
     
      
}
