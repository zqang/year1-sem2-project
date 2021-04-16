/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author alvin
 */
public class SuspectedController implements Initializable {
    

    @FXML
    private AnchorPane suspectedAnchorPane;
    @FXML
    private ListView<String> suspectedList;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    @FXML
    private TextArea suspectedDetail;
    
    @FXML
    private AnchorPane detail;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(HumanHealth i : HumanHealth.incompleteSuspectedArrayList){
            data.add(i.getHumanInfo());
        }
        
        suspectedList.getItems().addAll(data);

            //this is use for set details when click 
            suspectedList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                //set the details for each suspected
                detail.setDisable(false);
                suspectedDetail.setText(HumanHealth.getDetail(nv));

           });
    }
      
    
}
