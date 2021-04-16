package dashboard.fxml;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;

import static dashboard.fxml.Simulation.*;

public class HumanHealth {

    //FIELD
    //field for human health

    private int workNature,civicAwareness,forgetfulness;
    private String humanInfo,ageRange,occupation,hospital,workplace,gender,contactNumber;
    private int dayOfSuspected,dayOfInfected,dayOfDead,dayOfRecovered;

    //not in constructor and gain from calculate
    private int probability,
            levelOfImmunity,
            hygieneLevel,
            immunityAfterInfected,
            relationship,
            timeOfSuspected;
    private String relationshipName,infectedBy;



    //STATIC ARRAY LIST
    public static List<HumanHealth>
            healthArrayList = new CopyOnWriteArrayList<>(),
            healthArrayListRemovable = new CopyOnWriteArrayList<>(),
            suspectedArrayList = new CopyOnWriteArrayList<>(),
            incompleteSuspectedArrayList = new CopyOnWriteArrayList<>(),
            differentArrayList = new CopyOnWriteArrayList<>(),
            infectedArrayList =  new CopyOnWriteArrayList<>(),
            deadArrayList = new CopyOnWriteArrayList<>(),
            recoveredArrayList = new CopyOnWriteArrayList<>();



    //Constructor
    public HumanHealth(int workNature, int forgetfulness, String humanInfo, String ageRange, String occupation, String workplace, String gender, int civicAwareness,String contactNumber) {
        this.workNature = workNature;
        this.forgetfulness = forgetfulness;
        this.humanInfo = humanInfo;
        this.ageRange = ageRange;
        this.occupation = occupation;
        this.workplace = workplace;
        this.gender = gender;
        this.civicAwareness = civicAwareness;
        this.contactNumber = contactNumber;
    }

    //NON-STATIC METHOD
    //use in calculateProbabilityOfInfection and calculateImmunityAfterInfected
    private void calculateLevelOfImmunity(){
        Random random = new Random();
        //calculate the level of immunity by multiplying ageRange with ownImmunity
        //higher level of immunity means higher probability to get infected
        int ownImmunity = random.nextInt(21) +30; //range of 10(high immunity) - 90(low immunity)
        //probability of ageRange to get infected
        double SENIOR_RESIDENT = 0.7;
        double ADULT = 0.6;
        double CHILDREN = 0.5;
        switch (ageRange) {
            case "Children" : levelOfImmunity = (int) (ownImmunity * CHILDREN); break;
            case "Adult" : levelOfImmunity = (int) (ownImmunity * ADULT); break;
            case "Senior Residents" : levelOfImmunity = (int) (ownImmunity * SENIOR_RESIDENT); break;
        } //calculate the ageRange and add them with own immunity then set to level of immunity
    }
    private void calculateHygieneLevel(){
        Random random = new Random();
        //calculate the hygiene level by multiplying occupation with own hygiene
        int ownHygiene = random.nextInt(11) +20; //range of 1(high hygiene) - 100(low hygiene)

        //probability of occupation to get infected
        HashMap<String, Integer> occupation = new HashMap<>();
        int numberOfOccupation = 26;
        double hygiene = 0;
        String[] occupations = {"Student", "Retired", "Farmer", "Fisherman", "Teacher", "Grocer", "Postman", "Doctor", "Nurse",
                "Construction worker", "Veterinarian", "Cook", "Police", "Barber", "Electrical Technician", "Baker", "Craftman",
                "Carpenter", "Grab Driver", "Vendor", "Hotel worker", "Bank officer", "Security Guard", "Waiter", "Engineer", "Factory worker"};
        double[] occupationProbability = new double[numberOfOccupation];
        for(int i =0; i<numberOfOccupation; i++){
            final int n = random.nextInt(11)+20;
            occupationProbability[i] = n;
            occupation.put(occupations[i],n);
        } //put the occupation string as key and occupation probability as value in the hashmap
        for(String s : occupation.keySet()){
            if(s.compareTo(this.occupation)==0){
                hygiene = occupation.get(s);
            }
        } //set the hygiene level based on the type of occupation from hashmap
        //probabilty of workNature to get infected
        double HIGH_EXPOSURE_OCCUPATION = 0.75;
        double LOW_EXPOSURE_OCCUPATION = 0.25;

        switch (workNature){
            case 1 : hygieneLevel = ownHygiene + (int)(hygiene * LOW_EXPOSURE_OCCUPATION); break;
            case 2 : hygieneLevel = ownHygiene + (int)(hygiene * HIGH_EXPOSURE_OCCUPATION); break;
        } //calculate the work nature based on two group


        //when MCO start and ppl start to wear mask so own hygiene will increase by 8 if civic awareness greater than 40
        if(Government.isIsStart()){
            if(civicAwareness>40) {
                hygieneLevel -= 8;
            }
        }
    }

    //calculate the relationship take from the hashmap consist of relations and weight
    //use in static method calculateProbabilityOfInfection
    public void calculateRelationship(HashMap<String,Integer> relationship, CopyOnWriteArrayList<String> suspected){
        //probability of Relationship to get infected
        int FRIEND = 40;
        int FAMILY = 60;

            for(String relations : relationship.keySet()){
                if(suspected.contains(relations) && humanInfo.equals(relations)){
                    switch (relationship.get(relations)){
                        case 0 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Grandchild";
                        }
                        break;
                        case 1 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Child";
                        }
                        break;
                        case 2 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Sibling";
                        }
                        break;
                        case 3 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Spouse";
                        }
                        break;
                        case 4 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Parent";
                        }
                        break;
                        case 5 : {
                            this.relationship = FAMILY;
                            this.relationshipName = "Grandparent";
                        }
                        break;
                        case 6 : {
                            this.relationship = FRIEND;
                            this.relationshipName = "Friend";
                        }
                        break;
                    }
                }
            }

    }

    //to calculate immunity of a person after infected
    //use in static method calculateImmunityOfInfection
    private void calculateImmunityAfterInfected() {
        calculateLevelOfImmunity();
        calculateHygieneLevel();
        immunityAfterInfected =levelOfImmunity + (int)(hygieneLevel * 0.75) + (int)(20*0.25);
    }
    //to calculate the probability of infection of a person
    //USE IN STATIC METHOD CALCULATEPROBABILITYOFINFECTION
    public void calculateProbabilityOfInfection(){

        calculateLevelOfImmunity();
        calculateHygieneLevel();
        //relationship has been calculated in the static method
        //[(Immunity x Age ) + (Occupation x Hygiene x 0.75) + (Relationship x 0.25 )]
        //own immunity range 30-50, age range 0.5-0.7, occupation range 0.25-0.75, hygiene range 20-30, relationship range 20-60
        probability = levelOfImmunity + (int)(hygieneLevel * 0.75) + (int)(relationship * 0.25) ;
    }



    //STATIC METHOD
    //INPUT
    //read the humanList in humanInfoGenerator and add them to the healthArrayList
    public static void addHumanFromHumanInfoGenerator(HumanInfoGenerator humanInfoGenerator){
        Random random = new Random();
        for(HumanInfo h : humanInfoGenerator.humanList){
            HumanHealth health = new HumanHealth(h.getWorkNature(),h.getForgetfulness(),h.getID(),h.getAgeRange(),h.getJob(),h.getWorkPlace(),h.getGender(),random.nextInt(101),h.getContactNumber());
            healthArrayList.add(health);
            healthArrayListRemovable.add(health);
        }
    }

    public static void generateHealthList(String file){
        try{
            PrintWriter fileWriter = new PrintWriter(new File(file));
            for(HumanHealth h : healthArrayList){
                fileWriter.println(h.toString());
            }
            fileWriter.close();
        }catch(IOException e){}

    }

    //add the first infected to the suspected list
    public static HumanHealth addFirstSuspected(){
        Random random = new Random();
        HumanHealth health = healthArrayListRemovable.get(random.nextInt(10000));
        suspectedArrayList.add(health);
        healthArrayListRemovable.remove(health);
        return health;
    }

    //add suspected from contact tracer to suspectedArrayList
    //calculate the probability of infection and set the day of suspected and set infected by
    public static CopyOnWriteArrayList<HumanHealth> addSuspectedFromContactTracer(CopyOnWriteArrayList<String> suspected, HumanInfo infected, int day, HashMap<String,Integer> relationship){
        CopyOnWriteArrayList<HumanHealth> suspect = new CopyOnWriteArrayList<>();
        Comparator<HumanHealth> compareByProbability = Comparator.comparingInt(HumanHealth::getProbability)
                .thenComparingInt(HumanHealth::getLevelOfImmunity).thenComparingInt(HumanHealth::getHygieneLevel);
        for(HumanHealth h : healthArrayListRemovable){
            for(String i : suspected){
                if(h.getHumanInfo().equals(i)){
                    if(!suspectedArrayList.contains(h)){
                        h.calculateRelationship(relationship,suspected);
                        h.calculateProbabilityOfInfection();
                        h.setDayOfSuspected(day);
                        h.setInfectedBy(infected.getID());
                        h.setTimeOfSuspected(1);
                        suspectedArrayList.add(h);
                        suspect.add(h);
                        healthArrayListRemovable.remove(h);
                    }else{
                        for(HumanHealth g : suspectedArrayList){
                            if(g.getHumanInfo().equals(h.getHumanInfo())){
                                g.calculateRelationship(relationship, suspected);
                                g.calculateProbabilityOfInfection();
                                g.setInfectedBy(g.getInfectedBy() + "," + infected.getID());
                                g.setTimeOfSuspected(g.getTimeOfSuspected()+1);
                                suspect.add(g);
                            }
                        }
                    }
                }
            }
        }
        suspect.sort(compareByProbability.reversed());
        return suspect;
    }

    public static void addIncompleteSuspected(CopyOnWriteArrayList<String> suspected){
        for(HumanHealth d : suspectedArrayList){
            if(!suspected.contains(d.getHumanInfo()) && !differentArrayList.contains(d) && !incompleteSuspectedArrayList.contains(d)){
                differentArrayList.add(d);
            }
            if(suspected.contains(d.getHumanInfo()) && !incompleteSuspectedArrayList.contains(d)){
                incompleteSuspectedArrayList.add(d);
                dailySuspected++;
            }
        }
    }


    //CALCULATE THE DATA
    //calculate the immunity of infected for first time
    public static void calculateImmunityOfInfection(HumanInfo infected, int day){
        for(HumanHealth h : suspectedArrayList){
            if(infected.getID().equals(h.getHumanInfo())){
                if(h.getProbability()==0){ //means person does not calculate probability before
                    h.calculateImmunityAfterInfected();
                    System.out.println("calculate " + infected.getID() + " for the first time");
                    infectedArrayList.add(h);
                    h.setDayOfInfected(day);
                    suspectedArrayList.remove(h);
                    incompleteSuspectedArrayList.remove(h);

                }else{  //means person has been suspected and have calculated probability
                    h.setImmunityAfterInfected(h.getProbability());
                    h.setDayOfInfected(day);
                    infectedArrayList.add(h);
                    suspectedArrayList.remove(h);
                    incompleteSuspectedArrayList.remove(h);

                }
            }
        }

    }

    //calculate the immunity of infected continuously
    public static void calculateDailyImmunity(){
        Random random = new Random();
        for(HumanHealth h : infectedArrayList){
            int dailyRandom = random.nextInt( 25 + 50 ) - 50;
            int immunity = dailyRandom + h.getImmunityAfterInfected();
            h.setImmunityAfterInfected(immunity);
            System.out.println("daily immunity for " + h.getHumanInfo() + " is " + immunity);
        }
    }


    //CHECK THE DATA AND MAKE RESPONSE
    //check the immunity if negative or more than 100
    public static void checkDeadAndRecovered(int day){
        int dead = 0,recovered = 0;
        for(HumanHealth h : infectedArrayList){
            if(h.getImmunityAfterInfected()<=0){  //confirm the infected is dead
                h.setDayOfDead(day);
                infectedArrayList.remove(h);
                deadArrayList.add(h);
                healthArrayList.remove(h);
                healthArrayListRemovable.remove(h);
                dead++;
                System.out.println(h.getHumanInfo()+ " has dead on day " + h.getDayOfDead());
            }else if(h.getImmunityAfterInfected()>=100){ //confirm the infected is recovered
                h.setDayOfRecovered(day);
                //set the immunity increase because immune system become stronger
                if(h.getLevelOfImmunity()-30>=0)
                    h.setLevelOfImmunity(h.getProbability()-30);
                else
                    h.setLevelOfImmunity(h.getProbability()-10);
                infectedArrayList.remove(h);
                recoveredArrayList.add(h);
                healthArrayListRemovable.add(h);
                recovered++;
                System.out.println(h.getHumanInfo() + " has recovered on day " + h.getDayOfRecovered());
            }
        }
        dailyDead = dead;
        dailyRecovered = recovered;
    }
    //check for the quarantine period if longer than 14 day then removed it from suspected
    /**** i remove the suspected in contact tracer also *******/
    public static void removeAfterQuarantineEnd(int day, List<String> suspected){
        for(HumanHealth h : suspectedArrayList){
            int count = day - h.getDayOfSuspected();
            if(count>14){
                suspectedArrayList.remove(h);
                incompleteSuspectedArrayList.remove(h);
                healthArrayListRemovable.add(h);
                suspected.remove(h.getHumanInfo());
                System.out.println(count + " count ");
                System.out.println("suspected " + h.getHumanInfo() + " quarantine has ended ");
            }
        }
    }



    //SORTER
    public static List<HumanHealth> sortByProbability(){
        Comparator<HumanHealth> compareByProbability = Comparator.comparingInt(HumanHealth::getProbability)
                .thenComparingInt(HumanHealth::getLevelOfImmunity).thenComparingInt(HumanHealth::getHygieneLevel);
        suspectedArrayList.sort(compareByProbability);
        return suspectedArrayList;
    }



    //GETTER AND SETTER METHOD
    public String getAgeRange() { return ageRange; }

    public int getProbability() { return probability; }

    public int getCivicAwareness() { return civicAwareness; }

    public int getLevelOfImmunity() { return levelOfImmunity; }
    public void setLevelOfImmunity(int levelOfImmunity) { this.levelOfImmunity = levelOfImmunity; }

    public String getHumanInfo() { return humanInfo; }

    public String getOccupation() { return occupation; }
    public void setOccupation(String occupation) { this.occupation = occupation; }

    public int getHygieneLevel() {return hygieneLevel;}

    public int getDayOfSuspected() { return dayOfSuspected; }
    public void setDayOfSuspected(int dayOfSuspected) { this.dayOfSuspected = dayOfSuspected; }

    public int getDayOfInfected() { return dayOfInfected; }
    public void setDayOfInfected(int dayOfInfected) { this.dayOfInfected = dayOfInfected; }

    public String getHospital() { return hospital; }
    public void setHospital(String hospital) { this.hospital = hospital; }

    public int getDayOfDead() { return dayOfDead; }
    public void setDayOfDead(int dayOfDead) { this.dayOfDead = dayOfDead; }

    public int getDayOfRecovered() { return dayOfRecovered; }
    public void setDayOfRecovered(int dayOfRecovered) { this.dayOfRecovered = dayOfRecovered;}

    public int getImmunityAfterInfected() { return immunityAfterInfected; }
    public void setImmunityAfterInfected(int immunityAfterInfected) { this.immunityAfterInfected = immunityAfterInfected; }

    public String getRelationshipName() { return relationshipName; }

    public String getInfectedBy() { return infectedBy; }
    public void setInfectedBy(String infectedBy) { this.infectedBy = infectedBy; }

    public int getTimeOfSuspected() { return timeOfSuspected; }
    public void setTimeOfSuspected(int timeOfSuspected) { this.timeOfSuspected = timeOfSuspected; }

    public int getForgetfulness() { return forgetfulness; }
    public void setForgetfulness(int forgetfulness) { this.forgetfulness = forgetfulness; }

    public String getWorkplace() { return workplace; }
    public void setWorkplace(String workplace) { this.workplace = workplace; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public String getContactNumber() { return contactNumber; }
    public void setContactNumber(String contactNumber) { this.contactNumber = contactNumber; }

    @Override
    public String toString() {
        return workNature + " " + civicAwareness + " " + humanInfo + " " + ageRange + " " + occupation + " "
                + forgetfulness + " " + workplace + " " + gender
                + hospital + " " + dayOfSuspected + " " + dayOfInfected + " " + dayOfDead + " " + dayOfRecovered + " "
                + probability + " " + levelOfImmunity + " " + hygieneLevel + " " + immunityAfterInfected + " " + relationship
                + " " + relationshipName + " " + infectedBy;
    }

    public static String getDetail(String id){
        for(HumanHealth i : suspectedArrayList){
            if(i.getHumanInfo().equals(id)){
                return "Forgetfulness : " + i.getForgetfulness() + "%" +
                        "\nJob : " +  i.getOccupation() +
                        "\nContact Number : " + i.getContactNumber() +
                        "\nWorkplace : " + i.getWorkplace() +
                        "\nAge : " + i.getAgeRange() +
                        "\nGender : " + i.getGender() +
                        "\nProbability of getting infected : " + i.getProbability() + "%" +
                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
                        "\nRelationship with infected : " + i.getRelationshipName() +
                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
                        "\nContacted with : " + i.getInfectedBy() +
                        "\nCivic Awareness : " + i.getCivicAwareness() + "%";
            }
        }
        return "";
    }

    public static String getInfectedDetail(String id){
        for(HumanHealth i : infectedArrayList){
            if(i.getHumanInfo().equals(id)){
                return "Forgetfulness : " + i.getForgetfulness() + "%" +
                        "\nJob : " +  i.getOccupation() +
                        "\nContact Number : " + i.getContactNumber() +
                        "\nWorkplace : " + i.getWorkplace() +
                        "\nAge : " + i.getAgeRange() +
                        "\nGender : " + i.getGender() +
                        "\nProbability of getting infected : " + i.getProbability() + "%" +
                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
                        "\nRelationship with infected : " + i.getRelationshipName() +
                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
                        "\nContacted with : " + i.getInfectedBy() +
                        "\nDay of infected : Day " + i.getDayOfInfected() +
                        "\nDay of dead : Day " + i.getDayOfDead() +
                        "\nDay of recovered : Day " + i.getDayOfRecovered() +
                        "\nCivic Awareness : " + i.getCivicAwareness() + "%";
            }
        }
        return "";
    }

    public static String getDeadDetail(String id){
        for(HumanHealth i : deadArrayList){
            if(i.getHumanInfo().equals(id)){
                return "Forgetfulness : " + i.getForgetfulness() + "%" +
                        "\nJob : " +  i.getOccupation() +
                        "\nContact Number : " + i.getContactNumber() +
                        "\nWorkplace : " + i.getWorkplace() +
                        "\nAge : " + i.getAgeRange() +
                        "\nGender : " + i.getGender() +
                        "\nProbability of getting infected : " + i.getProbability() + "%" +
                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
                        "\nRelationship with infected : " + i.getRelationshipName() +
                        "\nContacted with : " + i.getInfectedBy() +
                        "\nCivic Awareness : " + i.getCivicAwareness() + "%" +
                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
                        "\nDay of infected : Day " + i.getDayOfInfected() +
                        "\nDay of dead : Day " + i.getDayOfDead();
            }
        }
        return "";
    }

    public static String getRecoveredDetail(String id){
        for(HumanHealth i : recoveredArrayList){
            if(i.getHumanInfo().equals(id)){
                return "Forgetfulness : " + i.getForgetfulness() + "%" +
                        "\nJob : " +  i.getOccupation() +
                        "\nContact Number : " + i.getContactNumber() +
                        "\nWorkplace : " + i.getWorkplace() +
                        "\nAge : " + i.getAgeRange() +
                        "\nGender : " + i.getGender() +
                        "\nProbability of getting infected : " + i.getProbability() + "%" +
                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
                        "\nRelationship with infected : " + i.getRelationshipName() +
                        "\nCivic Awareness : " + i.getCivicAwareness() + "%" +
                        "\nContacted with : " + i.getInfectedBy() +
                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
                        "\nDay of infected : Day " + i.getDayOfInfected() +
                        "\nDay of recovered : Day " + i.getDayOfRecovered();

            }
        }
        return "";
    }
}
