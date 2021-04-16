/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 *
 * @author alvin
 */
public class InfectedController implements Initializable {

    @FXML
    private AnchorPane infectedAnchorPane;
    @FXML
    private ListView<String> infectedList;
    
   
    
    @FXML
    private AnchorPane detail;
    @FXML
    private TextArea infectedDetail;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(HumanHealth i : HumanHealth.infectedArrayList){
            data.add(i.getHumanInfo());
        }
        
        infectedList.getItems().addAll(data);
        infectedList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                //set the details for each suspected
                detail.setDisable(false);
                infectedDetail.setText(HumanHealth.getInfectedDetail(nv));

           });
    }  
    
        static class ListOfPeople extends ListCell<String>{
        @Override
        public void updateItem(String item, boolean empty){
            super.updateItem(item,empty);
           
            Text text = new Text();
            if(item != null){
                text.setText(item);
                setGraphic(text);
            }else{
                setGraphic(null);
            }
        }
    }
    
}
