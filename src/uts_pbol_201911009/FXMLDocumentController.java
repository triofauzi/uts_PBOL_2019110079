/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uts_pbol_201911009;



import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 *
 * @author SUPER
 */
public class FXMLDocumentController implements Initializable {
    
    public static DBRoom dtroom = new DBRoom();
 public static DBCheckin dtbrg = new DBCheckin();
    
    @FXML
    private Label label;
    @FXML
    private Button btnviewroom;
    @FXML
    private Label adminName;
    @FXML
    private Button btnview;
    @FXML
    private Button btncheckin;
    @FXML
    private Button btnviewcekin;
    @FXML
    private Button btncekout;
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void roomviewklik(ActionEvent event) {
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
       
    }

    @FXML
    private void klikview(ActionEvent event) {
         try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLRoomView.fxml"));    
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
       
    }

    @FXML
    private void klikcheckin(ActionEvent event) {
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
       
    }

    @FXML
    private void klikviewcekin(ActionEvent event) {
          try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLCheckinView.fxml"));    
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
    }

    @FXML
    private void klikcekout(ActionEvent event) {
          try{
        FXMLLoader loader=new FXMLLoader(getClass().getResource("FXMLCheckOut.fxml"));    
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
    }
    
}
