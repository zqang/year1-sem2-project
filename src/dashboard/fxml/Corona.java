package dashboard.fxml;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

public class Corona{
    //change the activity log to generate more day
    //change grocer and veterinarian and barber worknature to group 2
    //set the worknature of students and senior residents to 2
    //add a getHumanInfo method in human info generator
    //change the setweight and to string method in the edge human class
    //change the friend print method to arraylist and change the human method in project to take from arraylist
    //change the forgetfulness to percentage in human info generator
    //change the activitylog to implement MCO and add suspected and infected

    public static void main(String[] args) throws Exception{
        Random random = new Random();
        int day=1, hour = 1, randomFirstDayOfInfected = random.nextInt(15)+14, R0 = random.nextInt(10),count=1;
        Date date = new Date(day, hour);

        Places places = new Places();
        Project.place(places);
        HumanInfoGenerator humanInfoGenerator =new HumanInfoGenerator();
        humanInfoGenerator.generateInfo(places);
        Project.human(places);

        ContactTracer contactTracer = new ContactTracer();
        HumanHealth.addHumanFromHumanInfoGenerator(humanInfoGenerator);
        HumanHealth.generateHealthList("HealthList.txt");

        List<HumanHealth> humanHealths = HumanHealth.sortByProbability(); //return suspectedArrayList
        GraphHuman<String,Integer> graphHuman = Project.human;
        List<HumanHealth> infected = new CopyOnWriteArrayList<>();

        //need to add a person to suspected array list first
        HumanHealth firstSuspected =  HumanHealth.addFirstSuspected();
        infected.add(firstSuspected);

        while(day<31){
            System.out.println(date.toString());
            humanInfoGenerator.printDaily(places,day,contactTracer);
            if(day>3){count++;}
            if(randomFirstDayOfInfected == day){
                int infectedSize = infected.size();

                List<HumanHealth> cloneInfected = new CopyOnWriteArrayList<>(infected);
                infected.clear();
                System.out.println("infected size " + infectedSize);

                contactTracer.readActivityLog("Data"+count+".txt",day);




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
                    HumanHealth.calculateImmunityOfInfection(info,day); //calculate immunity of infection




                    CopyOnWriteArrayList<String> suspected = new CopyOnWriteArrayList<>();
                    if(!contactTracer.checkIfAllSuspected()){
                        contactTracer.findIncompleteSuspected(info,day); //forgetful will remove some of the event in activity log
                        suspected = contactTracer.findSuspected(info,day); //add suspected and infected in contact tracer
                    }
                    CopyOnWriteArrayList<String> suspect = contactTracer.findRelations(relationship,suspected);



                    //add suspected from suspected in contact tracer
                    //also calculate the probability of infection
                    //calculate relationship
                    if(suspect.size()!=0){
//                        People.addSuspectedPeople(humanInfoGenerator,suspect);
                        CopyOnWriteArrayList<HumanHealth> suspectedList = HumanHealth.addSuspectedFromContactTracer(suspect,info,day,relationship);
                        for(int j = 0; j<R0; j++){
                            if(suspectedList.size()>j)
                                infected.add(suspectedList.get(j));  //got possibility not added
                        }
                    }


                    //project.controller.Government start MCO, set activity log and r0 and naughty citizens
                    if(Government.isStart(HumanHealth.infectedArrayList.size())){
                        System.out.println("MCO has started");
                        R0 = Government.start(day);
                        Government.findPersonNotInfected();
                        Government.findPlaceNotInfected();
                    }else {
                        R0 = random.nextInt(10);
                    }

                    //project.controller.Government end MCO
                    if(Government.isEnd(HumanHealth.infectedArrayList.size())){
                        System.out.println("MCO has ended");
                        Government.end(day);
                    }
                }


//                contactTracer.addQuarantine("Data1.txt", humanInfoGenerator,day); //change activity log
//                contactTracer.addInfected("Data1.txt", humanInfoGenerator,day); //change activity log


                HumanHealth.calculateDailyImmunity(); //calculate the immunity of infection daily
                HumanHealth.checkDeadAndRecovered(day);
                contactTracer.checkDeadOrRecovered();
                humanHealths = HumanHealth.sortByProbability(); //sort the probability of suspected from highest to lowest
                randomFirstDayOfInfected++;
                HumanHealth.removeAfterQuarantineEnd(day, contactTracer.suspected);
                if(infected.size() == 0) {
                    int a = random.nextInt(1);
                    if (a == 1){
                        if(humanHealths.size()!=0)
                            infected.add(humanHealths.get(0));
                    }
                }

//                People.addInfectedPeople(humanInfoGenerator);
//                People.addDeadPeople(humanInfoGenerator);
//                People.addRecoveredPeople(humanInfoGenerator);
            }


            Thread.sleep(3000);
            day++;
            date.setDay(day);
        }
        contactTracer.showSuspected();
        contactTracer.showInfected();
        System.out.println(HumanHealth.suspectedArrayList.size());
        System.out.println(HumanHealth.infectedArrayList.size());
        System.out.println(HumanHealth.recoveredArrayList.size());
        System.out.println(HumanHealth.deadArrayList.size());
    }
}
