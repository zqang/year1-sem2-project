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
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author alvin
 */
public class TruthController implements Initializable {

    @FXML
    private ListView<String> listView;
    @FXML
    private Label totalNumber;
    @FXML
    private AnchorPane detail;
    @FXML
    private TextArea Detail;
    
    ObservableList<String> data = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        for(HumanHealth i : HumanHealth.differentArrayList){
            data.add(i.getHumanInfo());
        }

            totalNumber.setText(Integer.toString(HumanHealth.differentArrayList.size()));
        
        listView.getItems().addAll(data);
        listView.getSelectionModel().selectedItemProperty().addListener((obs, ov, nv) -> {
                //set the details for each suspected
                detail.setDisable(false);
                Detail.setText(HumanHealth.getDetail(nv));

           });
    }
}
