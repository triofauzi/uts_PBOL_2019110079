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
public class DBRoom {
     private RoomModel dt=new RoomModel();    
    public RoomModel getRoomModel(){ return(dt);}
    public void setRoomModel(RoomModel s){ dt=s;}
    
    public ObservableList<RoomModel>  Load() {
        try {
            ObservableList<RoomModel> tableData=FXCollections.observableArrayList();
            Koneksi con = new Koneksi();            
            con.bukaKoneksi();
            con.statement = con.dbKoneksi.createStatement();
            ResultSet rs = con.statement.executeQuery("Select roomnumber, roomtype,status, price from room");
            int i = 1;
            while (rs.next()) {
                RoomModel d=new RoomModel();
                d.setRoomnumber(rs.getString("roomnumber"));                
                d.setRoomtype(rs.getString("roomtype")); 
                d.setStatus(rs.getString("status"));
                d.setPrice(rs.getDouble("price"));               
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
            ResultSet rs = con.statement.executeQuery(  "select count(*) as jml from room where roomnumber = '" + nomor + "'");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("insert into room (roomnumber,roomtype, price, status) values (?,?,?,?)");
            con.preparedStatement.setString(1, getRoomModel().getRoomnumber());           
            con.preparedStatement.setString(2, getRoomModel().getRoomtype());
             con.preparedStatement.setDouble(3, getRoomModel().getPrice()); 
            con.preparedStatement.setString(4, getRoomModel().getStatus());                 
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("delete from room where roomnumber  = ? ");
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
            con.preparedStatement = con.dbKoneksi.prepareStatement("update room set roomtype = ?, price = ?,status = ?  where  roomnumber = ? ");
             con.preparedStatement.setString(1, getRoomModel().getRoomtype());       
            con.preparedStatement.setDouble(2, getRoomModel().getPrice()); 
            con.preparedStatement.setString(3, getRoomModel().getStatus());
             con.preparedStatement.setString(4, getRoomModel().getRoomnumber());
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
    
    public ObservableList<RoomModel>  CariRoom(String kode, String nama) {
        try {    
            ObservableList<RoomModel> 	tableData;
            tableData = FXCollections.observableArrayList();
            Koneksi con = new Koneksi(); 
            con.bukaKoneksi();
            con.statement = (Statement) con.dbKoneksi.createStatement();
            ResultSet rs = (ResultSet) con.statement.executeQuery("select roomnumber,roomtype,price,status from room WHERE roomnumber LIKE '" + kode + "%' OR roomtype LIKE '" + nama + "%'");
        int i = 1;
        while(rs.next()){
            RoomModel d = new RoomModel();
            d.setRoomnumber(rs.getString("roomnumber"));                
                d.setRoomtype(rs.getString("roomtype")); 
               
                d.setPrice(rs.getDouble("price"));   
                d.setStatus(rs.getString("status"));        
            tableData.add(d);
            i++;
        }
        con.tutupKoneksi();
        return tableData;
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }
    }

    
}
