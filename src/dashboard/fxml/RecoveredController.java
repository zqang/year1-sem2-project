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
public class RecoveredController implements Initializable{

    @FXML
    private AnchorPane recoveredAnchorPane;
    @FXML
    private ListView<String> recoveredList;
    @FXML
    private AnchorPane detail;
    @FXML
    private TextArea recoveredDetail;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(HumanHealth i : HumanHealth.recoveredArrayList){
            data.add(i.getHumanInfo());
        }
        
        recoveredList.getItems().addAll(data);
        recoveredList.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                //set the details for each suspected
                detail.setDisable(false);
                recoveredDetail.setText(HumanHealth.getRecoveredDetail(nv));

           });
    }
    
}
