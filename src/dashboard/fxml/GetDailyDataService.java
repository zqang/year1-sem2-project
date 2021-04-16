/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;


import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author alvin
 */
public class GetDailyDataService extends Service<ObservableList<HumanHealth>>{
    
    @Override
    protected Task createTask(){
        return new GetDailyDataTask();
    }
    
}
