/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.application.Platform;

/**
 *
 * @author alvin
 */
public class Simulation {
    
    static Random random = new Random();
    static int daily = 1,hour = 1, randomFirstDayOfInfected = random.nextInt(15)+1, R0 = random.nextInt(10),count=1;
    static Date date = new Date(daily, hour);
    static Places places = new Places();
    static HumanInfoGenerator humanInfoGenerator = new HumanInfoGenerator();
    static ContactTracer contactTracer = new ContactTracer();
    static List<HumanHealth> humanHealths = HumanHealth.sortByProbability();
    static GraphHuman<String,Integer> graphHuman = Project.human;
    static List<HumanHealth> infected = new CopyOnWriteArrayList<>();
    static int dailySuspected=0,dailyInfected=0,dailyRecovered=0,dailyDead=0;

    
    public static void start()throws Exception{
       
        Project.place(places);
        humanInfoGenerator.generateInfo(places);
        Project.human(places);
        HumanHealth.addHumanFromHumanInfoGenerator(humanInfoGenerator);
        HumanHealth.generateHealthList("HealthList.txt");

        //need to add a person to suspected array list first
        HumanHealth firstSuspected =  HumanHealth.addFirstSuspected();
        infected.add(firstSuspected);
    }
}

        
