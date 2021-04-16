/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dashboard.fxml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javafx.concurrent.Task;
import javafx.scene.chart.XYChart;

import static dashboard.fxml.Simulation.*;

/**
 *
 * @author alvin
 */
public class GetDailyDataTask extends Task<Void> {
    
    @Override
    protected Void call() throws Exception {
        //we can make db connection here and fetch database records // or perform other task as well
        
        updateProgress(0,500);
        
        
        System.out.println(date.toString());
        humanInfoGenerator.printDaily(places,daily,contactTracer);
        int n=0;
//        People.dailySuspected = 0;
        dailySuspected=0;
        if(daily>3){count++;}
        Thread.sleep(500);
        updateProgress(30,500); //6% progress
        if(randomFirstDayOfInfected >= daily){
            
            updateProgress(100,500); //20% progress
            int infectedSize = infected.size();
//            People.dailyInfected = infectedSize;
            dailyInfected = infectedSize;


            List<HumanHealth> cloneInfected = new CopyOnWriteArrayList<>(infected);
            infected.clear();
            System.out.println("infected size " + infectedSize);

            contactTracer.readActivityLog("Data"+count+".txt",daily);
            Thread.sleep(500);
            updateProgress(150,500); //30% progress
            System.out.println("health suspected number :  " + HumanHealth.suspectedArrayList.size());
            long time = System.nanoTime();
            for(int i = 0; i<infectedSize; i++){ //choose the highest probability of suspected list
                HumanHealth health = cloneInfected.get(i);
                HumanInfo info = humanInfoGenerator.getHumanInfo(health.getHumanInfo());


                //calculate relationship
                ArrayList<String> relations =  graphHuman.getAdjacent(health.getHumanInfo());
                HashMap<String,Integer> relationship = new HashMap<>();
                for(String s : relations){
                    int weight = graphHuman.getWeight(health.getHumanInfo(),s);
                    relationship.put(s,weight);
                }


                HumanHealth.calculateImmunityOfInfection(info,daily); //calculate immunity of infection
                CopyOnWriteArrayList<String> suspected = new CopyOnWriteArrayList<>();
                CopyOnWriteArrayList<String> incomplete = new CopyOnWriteArrayList<>();
                if(!contactTracer.checkIfAllSuspected()){
                    incomplete = contactTracer.findIncompleteSuspected(info,daily); //forgetful will remove some of the event in activity log
                    suspected = contactTracer.findSuspected(info,daily); //add suspected and infected in contact tracer
                }
                CopyOnWriteArrayList<String> suspect = contactTracer.findRelations(relationship,suspected);
                CopyOnWriteArrayList<String> incompleteSuspect = contactTracer.findIncompleteRelations(relationship,incomplete);



                if(suspect.size()!=0){
                    CopyOnWriteArrayList<HumanHealth> suspectedList = HumanHealth.addSuspectedFromContactTracer(suspect,info,daily,relationship);
                    for(int j = 0; j<R0; j++){
                        if(suspectedList.size()>j)
                            infected.add(suspectedList.get(j));
                    }
                }

                if(incompleteSuspect.size() !=0){
                    System.out.println("incomplete has done");
                    HumanHealth.addIncompleteSuspected(incompleteSuspect);
                }


                //Government start MCO, set activity log and r0 and naughty citizens
                if(Government.isStart(HumanHealth.infectedArrayList.size())){
                    System.out.println("MCO has started");
                    R0 = Government.start(daily);
                    Government.findPersonNotInfected();
                    Government.findPlaceNotInfected();
                }else {
                    R0 = random.nextInt(10);
                }

            }
            //Government end MCO
            if(Government.isEnd(HumanHealth.infectedArrayList.size())) {
                System.out.println("MCO has ended");
                Government.end(daily);
            }
            System.out.println("time of for loop is " + ((System.nanoTime()-time)/1000000));
            updateProgress(200,500); //40% progress



            HumanHealth.calculateDailyImmunity(); //calculate the immunity of infection daily
            HumanHealth.checkDeadAndRecovered(daily);
            contactTracer.checkDeadOrRecovered();
            updateProgress(300,500);//60% progress 
            humanHealths = HumanHealth.sortByProbability(); //sort the probability of suspected from highest to lowest
            HumanHealth.removeAfterQuarantineEnd(daily, contactTracer.suspected);
            if(infected.isEmpty()) {
                System.out.println("infected is empty");
                int a = random.nextInt(2);
                if (a == 1){
                    if(!humanHealths.isEmpty()){
                        infected.add(humanHealths.get(0));
                        System.out.println("infected added from random because infected size is 0");
                    }
                }
            }
        }     
        
        updateProgress(350,500); //70% progress
        daily++;
        date.setDay(daily);
        System.out.println("health infected number : " + HumanHealth.infectedArrayList.size());
        System.out.println("health suspected number :  " + HumanHealth.suspectedArrayList.size());
        System.out.println("health insuspected number :  " + HumanHealth.incompleteSuspectedArrayList.size());
        Thread.sleep(200);
        updateProgress(500,500);

        return null;
    }
    
}
