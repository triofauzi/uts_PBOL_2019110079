/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SUPER
 */
public class FXMLRoomViewController implements Initializable {

    @FXML
    private TextField searchroom;
    @FXML
    private Button btnaddroom;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnawal;
    @FXML
    private Button btnakhir;
    @FXML
    private TableView<RoomModel> tbvroom;
    
    
    private Connection connection;

    private Koneksi dbConnection;

    private PreparedStatement pst;

    public static final ObservableList<RoomModel> rooms = FXCollections.observableArrayList();

    public static final List<RoomModel> roomList = new ArrayList<>();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();
    }    
    
    public void showdata(){
        ObservableList<RoomModel> data=FXMLDocumentController.dtroom.Load();
        if(data!=null){            
            tbvroom.getColumns().clear();            
            tbvroom.getItems().clear();
            
            TableColumn col=new TableColumn("ROOM NUMBER");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomnumber"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("ROOM TYPE");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomtype"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("STATUS");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("status"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("PRICE");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("price"));
            tbvroom.getColumns().addAll(col);
            
             tbvroom.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvroom.getScene().getWindow().hide();;
        }                
    }

    @FXML
    private void klikaddroom(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLRoom.fxml"));    
        Parent root = (Parent)loader.load();        
        Scene scene = new Scene(root);        
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);        
        stg.setIconified(false);        
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   
            e.printStackTrace();   }
        showdata();        
        awalklik(event);

    }

    @FXML
    private void updateklik(ActionEvent event) {
        RoomModel s= new RoomModel();
        s=tbvroom.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLRoom.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLRoomController isidt=(FXMLRoomController)loader.getController();
        isidt.execute(s);                
        Scene scene = new Scene(root);
        Stage stg=new Stage();
        stg.initModality(Modality.APPLICATION_MODAL);
        stg.setResizable(false);
        stg.setIconified(false);
        stg.setScene(scene);
        stg.showAndWait();
        } catch (IOException e){   e.printStackTrace();   }
        showdata();  
        awalklik(event);

    }

    @FXML
    private void hapusklik(ActionEvent event) {
        RoomModel s= new RoomModel();       
        s=tbvroom.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtroom.delete(s.getRoomnumber())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
           awalklik(event);       
        }    
         
    }

    @FXML
    private void quitklik(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void awalklik(ActionEvent event) {
          tbvroom.getSelectionModel().selectFirst();        
      tbvroom.requestFocus();
    }

    @FXML
    private void akhirklik(ActionEvent event) {
         tbvroom.getSelectionModel().selectLast();        
        tbvroom.requestFocus();
    }

    @FXML
    private void cariDataroom(KeyEvent event) {
         RoomModel s = new RoomModel();
        String key = searchroom.getText();
        if(key!=""){
        ObservableList<RoomModel> data=FXMLDocumentController.dtroom. CariRoom(key,key);
        if(data!=null){            
           tbvroom.getColumns().clear();            
            tbvroom.getItems().clear();
            
            TableColumn col=new TableColumn("ROOM NUMBER");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomnumber"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("ROOM TYPE");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("roomtype"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("STATUS");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("status"));
            tbvroom.getColumns().addAll(col);
            
            col=new TableColumn("PRICE");
            col.setCellValueFactory(new PropertyValueFactory<RoomModel, String>("price"));
            tbvroom.getColumns().addAll(col);
            tbvroom.setItems(data);
            
        }else {
            Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvroom.getScene().getWindow().hide();;
        }            
            } else{
               showdata();
            }  
    }

    
   
}
