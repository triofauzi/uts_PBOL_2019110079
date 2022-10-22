/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
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
public class FXMLCheckinViewController implements Initializable {

    @FXML
    private TableView<CustModel> tbvchekin;
    @FXML
    private Button btnupdate;
    @FXML
    private Button btnquit;
    @FXML
    private Button btnhapus;
    @FXML
    private Button btnadd;

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
            tbvchekin.getColumns().clear();            
            tbvchekin.getItems().clear();
            
            TableColumn col=new TableColumn("ID CUSTOMER");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("idcust"));
            tbvchekin.getColumns().addAll(col);
            
            col=new TableColumn("NAMA");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("nama"));
            tbvchekin.getColumns().addAll(col);
            
            col=new TableColumn("TELPON");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("telpon"));
            tbvchekin.getColumns().addAll(col);
            
           col=new TableColumn("EMAIL");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("email"));
            tbvchekin.getColumns().addAll(col);
            
            col=new TableColumn("ROOM TYPE");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("type"));
            tbvchekin.getColumns().addAll(col);

            col=new TableColumn("ROOM NUMBER");
            col.setCellValueFactory(new PropertyValueFactory<CustModel, String>("roomnumber"));
            tbvchekin.getColumns().addAll(col);            
             tbvchekin.setItems(data);
    }else {  Alert a=new Alert(Alert.AlertType.ERROR,"Data kosong",ButtonType.OK);
            a.showAndWait();
            tbvchekin.getScene().getWindow().hide();;
        }                
    }

    @FXML
    private void updateklik(ActionEvent event) {
        CustModel s= new CustModel();
        s=tbvchekin.getSelectionModel().getSelectedItem();
        try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLCheckin.fxml"));    
        Parent root = (Parent)loader.load();
        FXMLCheckinController isidt=(FXMLCheckinController)loader.getController();
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
        
    }

    @FXML
    private void quitklik(ActionEvent event) {
         btnquit.getScene().getWindow().hide();
    }

    @FXML
    private void hapusklik(ActionEvent event) {
         CustModel s= new CustModel();       
        s=tbvchekin.getSelectionModel().getSelectedItem();
        Alert a=new Alert(Alert.AlertType.CONFIRMATION,"Mau dihapus?",ButtonType.YES,ButtonType.NO);
        a.showAndWait();
        if(a.getResult()==ButtonType.YES){
           if(FXMLDocumentController.dtbrg.delete(s.getIdcust())){
               Alert b=new Alert(Alert.AlertType.INFORMATION,"Data berhasil dihapus", ButtonType.OK);
               b.showAndWait();
           } else {
               Alert b=new Alert(Alert.AlertType.ERROR,"Data gagal dihapus", ButtonType.OK);
               b.showAndWait();               
           }    
           showdata();           
               
        }    
    }

    @FXML
    private void addklik(ActionEvent event) {
          try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLCheckin.fxml"));
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
        
    }
    
}
