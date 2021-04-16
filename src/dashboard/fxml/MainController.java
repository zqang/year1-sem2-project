/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author alvin
 */
public class MainController implements Initializable {
    @FXML
    private BorderPane borderPane;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }  

    
    private void loadUI(String ui){
        Parent root = null;
        try{
            root = FXMLLoader.load(getClass().getResource(ui+".fxml"));
            
        } catch (IOException ex){
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE,null,ex);
        }
        borderPane.setCenter(root);
    }
    
    @FXML
    private void clickOverall(MouseEvent event) {
        loadUI("Overall");
    }
    
    @FXML
    private void infected(MouseEvent event) {
        loadUI("Infected");
    }
    
    @FXML
    private void suspected(MouseEvent event) {
        loadUI("Suspected");
    }
    
    @FXML
    private void recovered(MouseEvent event) {
        loadUI("Recovered");
    }
    
    @FXML
    private void dead(MouseEvent event) {
        loadUI("Dead");
    }
    
    @FXML
    private void truth(MouseEvent event) { loadUI("Truth"); }
    
}
