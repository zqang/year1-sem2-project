package dashboard.fxml;



import java.io.*;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static dashboard.fxml.Simulation.dailySuspected;

public class ContactTracer {
    ArrayList<Event> activityLog;
    List<String> infected, suspected, incompleteSuspected,dead,recovered,different;  //add id only

    //CONSTRUCTOR
    public ContactTracer() {
        activityLog = new ArrayList<>();
        infected= new CopyOnWriteArrayList<>();
        suspected = new CopyOnWriteArrayList<>();
        incompleteSuspected = new CopyOnWriteArrayList<>();
        dead = new CopyOnWriteArrayList<>();
        recovered = new CopyOnWriteArrayList<>();
        different = new CopyOnWriteArrayList<>();
    }

    //record all activity log into the activity log array list
    public void readActivityLog(String file, int day){
        if(day>3){activityLog.clear();}
        try{
            Scanner sc = new Scanner(new File(file));
            while(sc.hasNextLine()){
                String s = sc.nextLine();
                String[] properties = s.split("\\.");
                String id = properties[0];
                int day1 = Integer.parseInt(properties[1]);
                int hour = Integer.parseInt(properties[2]);
                String place = properties[3];
                Event event = new Event(day1, hour, place , id);
                activityLog.add(event);
            }
        }catch(IOException e ){}
    }

    //DISPLAY DATA
    public void showSuspected(){
        for(int i = 0 ; i<suspected.size(); i++){
            System.out.print("Suspected " + i  + " is " + suspected.get(i) + " , ");
        }
        System.out.println();
    }
    public void showIncompleteSuspected(){
        for(int i = 0 ; i<incompleteSuspected.size(); i++){
            System.out.print("Incomplete Suspected " + i  + " is " + incompleteSuspected.get(i) + " , ");
        }
    }
    public void showInfected(){
        for(int i = 0 ; i<infected.size(); i++){
            System.out.print("Infected " + i  + " is " + infected.get(i) + " , ");
        }
        System.out.println();
    }

    //FIND DATA
    private ArrayList<Event> findInfectedInfo(HumanInfo humanInfo, int day){
        String id = humanInfo.getID();
        if(!infected.contains(id)){
            infected.add(id);
            System.out.println("infected added id : " + id);
        }
        ArrayList<Event> infected = new ArrayList<>();
        for(Event e : activityLog){
            if(id.equals(e.getHumanInfo())){
                infected.add(e);
            }
        }

        System.out.println(" total location infected ----- " + infected.size());
        return infected;
    }


    public CopyOnWriteArrayList<String> findRelations(HashMap<String,Integer> relationship, CopyOnWriteArrayList<String> suspect){
        for(String i : relationship.keySet()){
            if(relationship.get(i)<6){
                if(!suspected.contains(i) && !this.infected.contains(i)){
                    suspected.add(i);
                    if(!suspect.contains(i))
                        suspect.add(i);
                }else if(suspected.contains(i) && !this.infected.contains(i) && !suspect.contains(i)){
                    suspect.add(i);
                }
            }
        }
        System.out.println("Relations has been infected : " + suspect.size());
        return suspect;
    }

    public CopyOnWriteArrayList<String> findIncompleteRelations(HashMap<String,Integer> relationship, CopyOnWriteArrayList<String> suspect){
        for(String i : relationship.keySet()){
            if(relationship.get(i)<6){
                if(!incompleteSuspected.contains(i) && !this.infected.contains(i)){
                    incompleteSuspected.add(i);
                    if(!suspect.contains(i))
                        suspect.add(i);
                }else if(incompleteSuspected.contains(i) && !this.infected.contains(i) && !suspect.contains(i)){
                    suspect.add(i);
                }
            }
        }
        System.out.println("Relations has been infected : " + suspect.size());
        return suspect;
    }

    public CopyOnWriteArrayList<String> findSuspected(HumanInfo infectedInfo, int day){
        ArrayList<Event> infected = findInfectedInfo(infectedInfo,day);
        CopyOnWriteArrayList<String> suspect = new CopyOnWriteArrayList<>(); //for the current infected
        if(suspected.contains(infectedInfo.getID())){
            suspected.remove(infectedInfo.getID());
        }
        for(Event e : infected){
            for(Event f : activityLog){
                if(e.getPlace().compareTo(f.getPlace())==0
                        && e.getDay() == f.getDay()
                        && e.getHour() == f.getHour()){
                    if(!suspected.contains(f.getHumanInfo()) && !this.infected.contains(f.getHumanInfo())){
                        suspected.add(f.getHumanInfo());
                        suspect.add(f.getHumanInfo());
                    }
                }
            }
        }
        System.out.println("suspected on one infected id : " + infectedInfo.getID() + " number of suspected : " + suspect.size() );
        return suspect;
    }
    public CopyOnWriteArrayList<String> findIncompleteSuspected(HumanInfo infectedInfo, int day){
        CopyOnWriteArrayList<String> suspect = new CopyOnWriteArrayList<>();
        ArrayList<Event> infected = findInfectedInfo(infectedInfo,day);
        if(incompleteSuspected.contains(infectedInfo.getID())){
            incompleteSuspected.remove(infectedInfo.getID());
        }
        int forgetfulness =(int)((infectedInfo.getForgetfulness() * infected.size()) / 100);
        sortByDate(infected);
        if (forgetfulness > 0) {
            infected.subList(0, forgetfulness).clear();
        }
        for(Event e : infected){
            for(Event f : activityLog){
                if(e.getPlace().compareTo(f.getPlace())==0
                        && e.getDay() == f.getDay()
                        && e.getHour() == f.getHour()){
                    if(!incompleteSuspected.contains(f.getHumanInfo()) && !this.infected.contains(f.getHumanInfo())){
                        suspect.add(f.getHumanInfo());
                        incompleteSuspected.add(f.getHumanInfo());
                    }
                }
            }
        }
        System.out.println("suspected on one infected id : " + infectedInfo.getID() + " number of incomplete suspected : " + suspect.size());
        return suspect;
    }

    //CHECK AND CONFIRM THE DATA AND MAKE RESPONSE
    //check whether the person is recovered then set back the activity log
    public void checkDeadOrRecovered(){
        if(!HumanHealth.deadArrayList.isEmpty()){
            for(HumanHealth h : HumanHealth.deadArrayList){
                infected.remove(h.getHumanInfo());
                dead.add(h.getHumanInfo());
                
            }
        }
        if(!HumanHealth.recoveredArrayList.isEmpty()){
            for(HumanHealth h : HumanHealth.recoveredArrayList){
                infected.remove(h.getHumanInfo());
                recovered.add(h.getHumanInfo());
            }
        }
    }
    public boolean checkIfAllSuspected(){
      return suspected.size()==10000;
    }

    //SORTING METHOD
    public void sortByDate(ArrayList<Event> arrayList) {
        Comparator<Event> compareByDate = Comparator.comparingInt(Event::getDay)
                .thenComparingInt(Event::getHour)
                .thenComparing(Event::getPlace)
                .thenComparing(Event::getHumanInfo);
        arrayList.sort(compareByDate);
    }
}
