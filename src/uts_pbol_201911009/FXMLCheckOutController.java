/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author SUPER
 */
public class FXMLCheckOutController implements Initializable {

   
    @FXML
    private Button btncekout;
    @FXML
    private Button btnquit;
    @FXML
    private TableView<CustModel> tbvchekout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showdata();// TODO
    }    

     public void showdata(){
        ObservableList<CustModel> data=FXMLDocumentController.dtbrg.Load();
        if(data!=null){            
            tbvchekout.getColumns().clear();            
            tbvchekout.getItems().clear();
            
            TableColumn col=new TableColumn("ID CUSTOMER");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("idcust"));
            tbvchekout.getColumns().addAll(col);
            
            col=new TableColumn("NAMA");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("nama"));
            tbvchekout.getColumns().addAll(col);
            
            col=new TableColumn("TELPON");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("telpon"));
            tbvchekout.getColumns().addAll(col);
            
           col=new TableColumn("EMAIL");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("email"));
            tbvchekout.getColumns().addAll(col);
            
            col=new TableColumn("ROOM TYPE");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("type"));
            tbvchekout.getColumns().addAll(col);

            col=new TableColumn("ROOM NUMBER");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("roomnumber"));
            tbvchekout.getColumns().addAll(col);            
             tbvchekout.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvchekout.getScene().getWindow().hide();;
        }                
    }

   
    @FXML
    private void cekoutklik(ActionEvent event) {
          CustModel s= new CustModel();       
        s=tbvchekout.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"CHECK OUT",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtbrg.delete(s.getIdcust())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"BERHASIL CHECK OUT", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"GAGAL CHECK OUT", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
               
        }    
    }

    @FXML
    private void quitklik(ActionEvent event) {
         btnquit.getScene().getWindow().hide();
    }
    
}
