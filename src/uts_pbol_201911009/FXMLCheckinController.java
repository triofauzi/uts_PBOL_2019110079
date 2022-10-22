/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.net.URL;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import static javafx.collections.FXCollections.observableList;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author SUPER
 */
public class FXMLCheckinController implements Initializable {
 boolean editdata=false;
    @FXML
    private TextField txtname;
    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txtphone;
    
    @FXML
    private Button btnsubmit;
    
    @FXML
    private ComboBox comboboxtype;
    ObservableList<String>list = FXCollections.observableArrayList("Triple Room","Double Room","Single Room");
    @FXML
    private ComboBox comboboxnumber;
    ObservableList<String>listnumber = FXCollections.observableArrayList("0001","0002","0003","0004","0005");
    private DatePicker txtcekin;
    private DatePicker txtcekout;
    @FXML
    private TextField txtemail;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnbatal;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboboxnumber.setItems(listnumber);
         comboboxtype.setItems(list);
        // TODO
    }    
     public void execute(CustModel d){
        if(!d.getIdcust().isEmpty()){
          editdata=true;
          txtnumber.setText(d.getIdcust());
          txtname.setText(d.getNama());
          txtphone.setText(d.getTelpon());
          txtemail.setText(d.getEmail());
         comboboxnumber.setItems (FXCollections.observableArrayList(d.getRoomnumber()));
         comboboxtype.setItems (FXCollections.observableArrayList(d.getType())); 
        
         
          
          txtnumber.setEditable(false);
          txtname.requestFocus();         
        }
    }
   
    @FXML
    private void kliksubmit(ActionEvent event) {
        
        CustModel n=new CustModel();        
        n.setIdcust(txtnumber.getText());
        n.setNama(txtname.getText());     
        n.setTelpon(txtphone.getText());
        n.setEmail(txtemail.getText());
        n.setRoomnumber((String) comboboxnumber.getSelectionModel().getSelectedItem());
        n.setType((String) comboboxtype.getSelectionModel().getSelectedItem());       
         
        FXMLDocumentController.dtbrg.setCustModel(n);
        if(editdata){
            if(FXMLDocumentController.dtbrg.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Berhasil Check In",ButtonType.OK);
               a.showAndWait();   
               txtnumber.setEditable(true);        
              // btnbatalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Check In Gagal",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtbrg.validasi(n.getIdcust())<=0){
                    if(FXMLDocumentController.dtbrg.insert()){
       Alert a=new Alert(Alert.AlertType.INFORMATION,"Berhasil Check In",ButtonType.OK);
               a.showAndWait();            
               //btnbatalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Check In Gagal",ButtonType.OK);
               a.showAndWait();            
            }
        }else{Alert a=new Alert(Alert.AlertType.ERROR,"Data sudah ada",ButtonType.OK);
            a.showAndWait();
            txtnumber.requestFocus();
        }
    }

    @FXML
    private void klikquit(ActionEvent event) {
        btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void klikbatal(ActionEvent event) {
          
        txtnumber.setText("");        
        txtname.setText("");
        txtphone.setText("");       
        txtemail.setText("");
    }
    
    }

    
    

