/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.text.Font;

import static dashboard.fxml.Simulation.*;

/**
 *
 * @author alvin
 */
public class OverallController implements Initializable {
    

    @FXML
    private AnchorPane midAnchorPane;
    @FXML
    private Font x11;
    @FXML
    private Label infectedNumber;
    @FXML
    private Label deadNumber;
    @FXML
    private Label suspectedNumber;
    @FXML
    private Label recoveredNumber;
    @FXML
    private Label day;
    
    
//    static Random random = new Random();
//    static int day1 = 1,hour = 1, randomFirstDayOfInfected = random.nextInt(15)+14, R0 = random.nextInt(10),count=1;
//    
//    static Places places = new Places();
//    static HumanInfoGenerator humanInfoGenerator = new HumanInfoGenerator();
//    static ContactTracer contactTracer = new ContactTracer();
//    static List<HumanHealth> humanHealths = HumanHealth.sortByProbability();
//    static GraphHuman<String,Integer> graphHuman = Project.human;
//    static List<HumanHealth> infected = new CopyOnWriteArrayList<>();
    @FXML
    private Label totalCase;
    @FXML
    private Label dailyRecoveredNumber;
    @FXML
    private Label dailyInfectedNumber;
    @FXML
    private Label dailyDeadNumber;
    @FXML
    private Label dailySuspectedNumber;
    @FXML
    private LineChart<?,? > lineChart;
    @FXML
    private NumberAxis y;
    @FXML
    private CategoryAxis x;
    
    static XYChart.Series suspected = new XYChart.Series<>();
    static XYChart.Series infected = new XYChart.Series<>();
    static XYChart.Series dead = new XYChart.Series<>();
    static XYChart.Series recovered = new XYChart.Series<>();
    
    
        
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lineChart.getData().addAll(suspected,infected,recovered,dead);
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                Runnable updater = new Runnable() {

                    @Override
                    public void run() {

                        int total = HumanHealth.infectedArrayList.size() + HumanHealth.deadArrayList.size() + HumanHealth.recoveredArrayList.size();
                        infectedNumber.setText(Integer.toString(HumanHealth.infectedArrayList.size()));
                        suspectedNumber.setText(Integer.toString(HumanHealth.incompleteSuspectedArrayList.size()));
                        deadNumber.setText(Integer.toString(HumanHealth.deadArrayList.size()));
                        recoveredNumber.setText(Integer.toString(HumanHealth.recoveredArrayList.size()));
                        day.setText(Integer.toString(daily));
                        totalCase.setText(Integer.toString(total));
                        dailyInfectedNumber.setText(Integer.toString(dailyInfected));
                        dailySuspectedNumber.setText(Integer.toString(dailySuspected));
                        dailyRecoveredNumber.setText(Integer.toString(dailyRecovered));
                        dailyDeadNumber.setText(Integer.toString(dailyDead));


                    }
                };

                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                    }

                    // UI update is run on the Application thread
                    Platform.runLater(updater);
                }
            }

        });
        // don't let thread prevent JVM shutdown
        thread.start();
        
    }  

    @FXML
    private void loadDataProcessStart(ActionEvent event) {
        lineChart.getData().clear();
        OverallController.suspected.getData().add(new XYChart.Data(Integer.toString(daily),dailySuspected));
        OverallController.infected.getData().add(new XYChart.Data(Integer.toString(daily),dailyInfected));
        OverallController.dead.getData().add(new XYChart.Data(Integer.toString(daily),dailyDead));
        OverallController.recovered.getData().add(new XYChart.Data(Integer.toString(daily),dailyRecovered));
        lineChart.getData().addAll(suspected,infected,recovered,dead);
       final GetDailyDataService service = new GetDailyDataService();
       Region veil  = new Region();
       veil.setStyle("-fx-background-color: rgba(0,0,0,0.4)");
       veil.setPrefSize(1252,768);
       ProgressIndicator p = new ProgressIndicator();
       p.setMaxSize(300,300);
       p.setStyle("-fx-progress-color: orange;");

       p.progressProperty().bind(service.progressProperty());
       veil.visibleProperty().bind(service.runningProperty());
       p.visibleProperty().bind(service.runningProperty());

       midAnchorPane.getChildren().addAll(veil,p);
       service.start();

        System.out.println("done");
    }
}
