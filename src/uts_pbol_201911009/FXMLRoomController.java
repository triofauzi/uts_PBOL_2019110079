/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author SUPER
 */
public class FXMLRoomController implements Initializable {
    
    boolean editdata=false;

    @FXML
    private TextField txtnumber;
    @FXML
    private TextField txttype;
    @FXML
    private TextField txtprice;
    @FXML
    private Button btnsimpan;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnbtl;
    @FXML
    private TextField txtstatus;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    
     public void execute(RoomModel d){
        if(!d.getRoomnumber().isEmpty()){
          editdata=true;
          txtnumber.setText(d.getRoomnumber());
          txtstatus.setText(d.getStatus());
          txttype.setText(d.getRoomtype());
          txtprice.setText(String.valueOf(d.getPrice()));          
          txtnumber.setEditable(false);
          txttype.requestFocus();         
        }
    }

    @FXML
    private void simpanklik(ActionEvent event) {
        
        RoomModel n=new RoomModel();        
        n.setRoomnumber(txtnumber.getText());
        n.setStatus(txtstatus.getText()); 
        n.setRoomtype(txttype.getText());     
        n.setPrice(Double.parseDouble(txtprice.getText()));
        
        FXMLDocumentController.dtroom.setRoomModel(n);
        if(editdata){
            if(FXMLDocumentController.dtroom.update()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil diubah",ButtonType.OK);
               a.showAndWait();   
               txtnumber.setEditable(true);        
               batalklik(event);                
            } else {               
                Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal diubah",ButtonType.OK);
                a.showAndWait(); 
            }
            }else if(FXMLDocumentController.dtroom.validasi(n.getRoomnumber())<=0){
            if(FXMLDocumentController.dtroom.insert()){
               Alert a=new Alert(Alert.AlertType.INFORMATION,"Data berhasil disimpan",ButtonType.OK);
               a.showAndWait();            
               batalklik(event);
            } else {
               Alert a=new Alert(Alert.AlertType.ERROR,"Data gagal disimpan",ButtonType.OK);
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
    private void batalklik(ActionEvent event) {
         
        txtnumber.setText("");        
        txttype.setText("");
        txtstatus.setText("");
        txtprice.setText("");       
        txtnumber.requestFocus();
    }

   
    
}
