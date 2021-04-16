package dashboard.fxml;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Government {
    //start mco when the infected more than 100
    //check everyday
    //two week make announcement whether continue or not
    //set day of mco
    //set quarantine period
    //change the activity log to one person go out every family
    //find out which place not affected by mco
    //find out which person not affected by mco
    //increase hygiene level because wear mask


    public static final String[] placeNotAffected = {
            "Market","House","Hospital","Restaurant","Police Station","Bank","Grocery","Clinic"
    };

    public static final String[] jobNotAffected = {
            "Doctor","Nurse","Postman","Grocer","Police","Security Guard","Bank Officer"
    };
    private static int dayOfMCOStart;
    private static int dayOfMCOEnd;

    private static boolean isStart;
    private static boolean isEnd;

    private static CopyOnWriteArrayList<String> naughtyCitizens = new CopyOnWriteArrayList<>();
    private static CopyOnWriteArrayList<String> places = new CopyOnWriteArrayList<>();

    public static boolean isStart(int numberOfInfected){
        isStart= numberOfInfected > 400;
        return isStart;
    }

    public static boolean isEnd(int numberOfInfected){
        if(isStart){
            isEnd= numberOfInfected < 50;
        }
        return isEnd;
    }

    public static int start(int day){
        Random random = new Random();
        int r0 = random.nextInt(4);
        dayOfMCOStart = day;
        return r0;
    }

    public static void end(int day){
        dayOfMCOEnd = day;
    }


    public static void findPersonNotInfected(){
        for(HumanHealth health : HumanHealth.suspectedArrayList){
            if(health.getCivicAwareness() < 50){
                naughtyCitizens.add(health.getHumanInfo());
            }
        }
    }

    public static boolean checkIfIsNaughtyCitizens(String id){
        return naughtyCitizens.contains(id);
    }

    public static boolean checkIfIsJobNotAffected(String job){
        for (String s : jobNotAffected) {
            if(s.equals(job))
                return true;
        }
        return false;
    }

    public static void findPlaceNotInfected(){
        places.addAll(Arrays.asList(placeNotAffected));
    }

    public static boolean isIsStart() { return isStart; }
    public static CopyOnWriteArrayList<String> getNaughtyCitizens() { return naughtyCitizens; }

}
