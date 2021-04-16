///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package dashboard.fxml;
//
//import javafx.beans.property.SimpleStringProperty;
//import javafx.collections.FXCollections;
//import javafx.collections.ObservableList;
//
//import java.util.concurrent.CopyOnWriteArrayList;
//
///**
// *
// * @author alvin
// */
//public class People {
//    private final SimpleStringProperty id, forgetfulness, job, workplace, ageRange, gender,
//            probability, levelOfImmunity, hygieneLevel, immunityAfterInfected, relationshipName, dayOfSuspected, infectedBy, dayOfInfected, dayOfDead, dayOfRecovered, civicAwareness;
//
//    public static ObservableList<People> suspectedPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> infectedPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> deadPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> recoveredPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> dailySuspectedPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> dailyInfectedPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> dailyrecoveredPeople = FXCollections.observableArrayList();
//    public static ObservableList<People> dailyDeadPeople = FXCollections.observableArrayList();
//    public static int totalSuspected=0, totalInfected=0, totalDead=0, totalRecovered=0;
//    public static int dailySuspected=0, dailyInfected=0, dailyDead=0, dailyRecovered=0;
//
//    public People(SimpleStringProperty id, SimpleStringProperty forgetfulness, SimpleStringProperty job, SimpleStringProperty workplace,
//                  SimpleStringProperty ageRange, SimpleStringProperty gender, SimpleStringProperty probability,
//                  SimpleStringProperty levelOfImmunity, SimpleStringProperty hygieneLevel, SimpleStringProperty immunityAfterInfected,
//                  SimpleStringProperty relationshipName, SimpleStringProperty dayOfSuspected, SimpleStringProperty infectedBy,
//                  SimpleStringProperty dayOfInfected, SimpleStringProperty dayOfDead, SimpleStringProperty dayOfRecovered,
//                  SimpleStringProperty civicAwareness) {
//        this.id = id;
//        this.forgetfulness = forgetfulness;
//        this.job = job;
//        this.workplace = workplace;
//        this.ageRange = ageRange;
//        this.gender = gender;
//        this.probability = probability;
//        this.levelOfImmunity = levelOfImmunity;
//        this.hygieneLevel = hygieneLevel;
//        this.immunityAfterInfected = immunityAfterInfected;
//        this.relationshipName = relationshipName;
//        this.dayOfSuspected = dayOfSuspected;
//        this.infectedBy = infectedBy;
//        this.dayOfInfected = dayOfInfected;
//        this.dayOfDead = dayOfDead;
//        this.dayOfRecovered = dayOfRecovered;
//        this.civicAwareness = civicAwareness;
//    }
//
//    public People(SimpleStringProperty id, SimpleStringProperty forgetfulness, SimpleStringProperty job, SimpleStringProperty workplace, SimpleStringProperty ageRange, SimpleStringProperty gender) {
//        this.id = id;
//        this.forgetfulness = forgetfulness;
//        this.job = job;
//        this.workplace = workplace;
//        this.ageRange = ageRange;
//        this.gender = gender;
//        this.probability = new SimpleStringProperty();
//        this.levelOfImmunity = new SimpleStringProperty();
//        this.hygieneLevel = new SimpleStringProperty();
//        this.immunityAfterInfected = new SimpleStringProperty();
//        this.relationshipName = new SimpleStringProperty();
//        this.dayOfDead = new SimpleStringProperty();
//        this.dayOfInfected = new SimpleStringProperty();
//        this.infectedBy = new SimpleStringProperty();
//        this.dayOfRecovered = new SimpleStringProperty();
//        this.dayOfSuspected = new SimpleStringProperty();
//        this.civicAwareness = new SimpleStringProperty();
//    }
//
//    public static void addSuspectedInfo(HumanInfo info){
//        People people = new People(
//                new SimpleStringProperty((String) info.getID()),
//                new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                new SimpleStringProperty((String) info.getJob()),
//                new SimpleStringProperty((String) info.getWorkPlace()),
//                new SimpleStringProperty((String) info.getAgeRange()),
//                new SimpleStringProperty((String) info.getGender()));
////                new SimpleStringProperty((String)Integer.toString(health.getProbability())),
////                new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
////                new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
////                new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
////                new SimpleStringProperty((String) health.getRelationshipName()),
////                new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
////                new SimpleStringProperty((String) health.getInfectedBy()),
////                new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
////                new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
////                new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
////                new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//        suspectedPeople.add(people);
//    }
//
//    private static void setHealth(HumanHealth health , People people){
//        people.setDayOfDead(Integer.toString(health.getDayOfDead()));
//        people.setCivicAwareness(Integer.toString(health.getCivicAwareness()));
//        people.setDayOfInfected(Integer.toString(health.getDayOfInfected()));
//        people.setDayOfSuspected(Integer.toString(health.getDayOfSuspected()));
//        people.setDayOfRecovered(Integer.toString(health.getDayOfRecovered()));
//        people.setImmunityAfterInfected(Integer.toString(health.getImmunityAfterInfected()));
//        people.setInfectedBy(health.getInfectedBy());
//        people.setRelationshipName(health.getRelationshipName());
//        people.setHygieneLevel(Integer.toString(health.getHygieneLevel()));
//        people.setLevelOfImmunity(Integer.toString(health.getLevelOfImmunity()));
//        people.setProbability(Integer.toString(health.getProbability()));
//    }
//
//    public static void addSuspectedHealth(HumanHealth health){
//        for(People people : suspectedPeople){
//            if(people.getId().equals(health.getHumanInfo())){
//                setHealth(health,people);
//            }
//        }
//    }
//
//    public static void addSingleInfected(HumanInfo info, HumanHealth health){
//        People people = new People(
//                        new SimpleStringProperty((String) info.getID()),
//                        new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                        new SimpleStringProperty((String) info.getJob()),
//                        new SimpleStringProperty((String) info.getWorkPlace()),
//                        new SimpleStringProperty((String) info.getAgeRange()),
//                        new SimpleStringProperty((String) info.getGender()),
//                        new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                        new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                        new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                        new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                        new SimpleStringProperty((String) health.getRelationshipName()),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                        new SimpleStringProperty((String) health.getInfectedBy()),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                        new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//
//        infectedPeople.add(people);
//    }
//
//    public static boolean addSingleSuspected(HumanInfo info, HumanHealth health){
//        People people = new People(
//                        new SimpleStringProperty((String) info.getID()),
//                        new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                        new SimpleStringProperty((String) info.getJob()),
//                        new SimpleStringProperty((String) info.getWorkPlace()),
//                        new SimpleStringProperty((String) info.getAgeRange()),
//                        new SimpleStringProperty((String) info.getGender()),
//                        new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                        new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                        new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                        new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                        new SimpleStringProperty((String) health.getRelationshipName()),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                        new SimpleStringProperty((String) health.getInfectedBy()),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                        new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                        new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//
//        boolean a = suspectedPeople.add(people);
//        return a;
//
//    }
//
//    public static void addSuspectedPeople(HumanInfoGenerator humanInfoGenerator,CopyOnWriteArrayList<String> incompleteSuspected){
//        for(HumanInfo info : humanInfoGenerator.humanList){
//            for(HumanHealth health : HumanHealth.suspectedArrayList){
//                if(info.getID().equals(health.getHumanInfo()) && incompleteSuspected.contains(health.getHumanInfo())){
//                    People people = new People(
//                            new SimpleStringProperty((String) info.getID()),
//                            new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                            new SimpleStringProperty((String) info.getJob()),
//                            new SimpleStringProperty((String) info.getWorkPlace()),
//                            new SimpleStringProperty((String) info.getAgeRange()),
//                            new SimpleStringProperty((String) info.getGender()),
//                            new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                            new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                            new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                            new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                            new SimpleStringProperty((String) health.getRelationshipName()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                            new SimpleStringProperty((String) health.getInfectedBy()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                            new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//                    if(!suspectedPeople.contains(people)){
//                        suspectedPeople.add(people);
//                    }
//                }
//            }
//        }
//    }
//
//    public static void addInfectedPeople(HumanInfoGenerator humanInfoGenerator){
//        for(HumanInfo info : humanInfoGenerator.humanList){
//            for(HumanHealth health : HumanHealth.infectedArrayList){
//                if(info.getID().equals(health.getHumanInfo())){
//                    People people = new People(
//                            new SimpleStringProperty((String) info.getID()),
//                            new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                            new SimpleStringProperty((String) info.getJob()),
//                            new SimpleStringProperty((String) info.getWorkPlace()),
//                            new SimpleStringProperty((String) info.getAgeRange()),
//                            new SimpleStringProperty((String) info.getGender()),
//                            new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                            new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                            new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                            new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                            new SimpleStringProperty((String) health.getRelationshipName()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                            new SimpleStringProperty((String) health.getInfectedBy()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                            new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//                    if(!infectedPeople.contains(people)){
//                        infectedPeople.add(people);
//                    }
//                    suspectedPeople.removeIf(p -> p.getId().equals(people.getId()));
//                }
//            }
//        }
//    }
//
//    public static void addRecoveredPeople(HumanInfoGenerator humanInfoGenerator){
//        for(HumanInfo info : humanInfoGenerator.humanList){
//            for(HumanHealth health : HumanHealth.recoveredArrayList){
//                if(info.getID().equals(health.getHumanInfo())){
//                    People people = new People(
//                            new SimpleStringProperty((String) info.getID()),
//                            new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                            new SimpleStringProperty((String) info.getJob()),
//                            new SimpleStringProperty((String) info.getWorkPlace()),
//                            new SimpleStringProperty((String) info.getAgeRange()),
//                            new SimpleStringProperty((String) info.getGender()),
//                            new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                            new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                            new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                            new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                            new SimpleStringProperty((String) health.getRelationshipName()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                            new SimpleStringProperty((String) health.getInfectedBy()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                            new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//                    if(!recoveredPeople.contains(people)){
//                        recoveredPeople.add(people);
//                    }
//                    infectedPeople.removeIf(p -> p.getId().equals(people.getId()));
//                }
//            }
//        }
//    }
//
//    public static void addDeadPeople(HumanInfoGenerator humanInfoGenerator){
//        for(HumanInfo info : humanInfoGenerator.humanList){
//            for(HumanHealth health : HumanHealth.deadArrayList){
//                if(info.getID().equals(health.getHumanInfo())){
//                    People people = new People(
//                            new SimpleStringProperty((String) info.getID()),
//                            new SimpleStringProperty((String)Integer.toString(info.getForgetfulness())),
//                            new SimpleStringProperty((String) info.getJob()),
//                            new SimpleStringProperty((String) info.getWorkPlace()),
//                            new SimpleStringProperty((String) info.getAgeRange()),
//                            new SimpleStringProperty((String) info.getGender()),
//                            new SimpleStringProperty((String)Integer.toString(health.getProbability())),
//                            new SimpleStringProperty((String)Integer.toString(health.getLevelOfImmunity())),
//                            new SimpleStringProperty((String)Integer.toString(health.getHygieneLevel())),
//                            new SimpleStringProperty((String)Integer.toString(health.getImmunityAfterInfected())),
//                            new SimpleStringProperty((String) health.getRelationshipName()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfSuspected())),
//                            new SimpleStringProperty((String) health.getInfectedBy()),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfInfected())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfDead())),
//                            new SimpleStringProperty((String)Integer.toString(health.getDayOfRecovered())),
//                            new SimpleStringProperty((String)Integer.toString(health.getCivicAwareness())));
//                    if(!deadPeople.contains(people)){
//                        deadPeople.add(people);
//                    }
//                    infectedPeople.removeIf(p -> p.getId().equals(people.getId()));
//
//                }
//
//            }
//        }
//    }
//
//    //for set the detail of list view
////    public static String getDetail(String id){
////        for(People i : suspectedPeople){
////            if(i.getId().equals(id)){
////                return "Forgetfulness : " + i.getForgetfulness() + "%" +
////                        "\nJob : " +  i.getJob() +
////                        "\nWorkplace : " + i.getWorkplace() +
////                        "\nAge : " + i.getAgeRange() +
////                        "\nGender : " + i.getGender() +
////                        "\nProbability of getting infected : " + i.getProbability() + "%" +
////                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
////                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
////                        "\nRelationship with infected : " + i.getRelationshipName() +
////                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
////                        "\nContacted with : " + i.getInfectedBy() +
////                        "\nCivic Awareness : " + i.getCivicAwareness() + "%";
////            }
////        }
////        return "";
////    }
//
//    public static String getInfectedDetail(String id){
//        for(People i : infectedPeople){
//            if(i.getId().equals(id)){
//                return "Forgetfulness : " + i.getForgetfulness() + "%" +
//                        "\nJob : " +  i.getJob() +
//                        "\nWorkplace : " + i.getWorkplace() +
//                        "\nAge : " + i.getAgeRange() +
//                        "\nGender : " + i.getGender() +
//                        "\nProbability of getting infected : " + i.getProbability() + "%" +
//                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
//                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
//                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
//                        "\nRelationship with infected : " + i.getRelationshipName() +
//                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
//                        "\nContacted with : " + i.getInfectedBy() +
//                        "\nDay of infected : Day " + i.getDayOfInfected() +
//                        "\nDay of dead : Day " + i.getDayOfDead() +
//                        "\nDay of recovered : Day " + i.getDayOfRecovered() +
//                        "\nCivic Awareness : " + i.getCivicAwareness() + "%";
//            }
//        }
//        return "";
//    }
//
//    public static String getDeadDetail(String id){
//        for(People i : deadPeople){
//            if(i.getId().equals(id)){
//                return "Forgetfulness : " + i.getForgetfulness() + "%" +
//                        "\nJob : " +  i.getJob() +
//                        "\nWorkplace : " + i.getWorkplace() +
//                        "\nAge : " + i.getAgeRange() +
//                        "\nGender : " + i.getGender() +
//                        "\nProbability of getting infected : " + i.getProbability() + "%" +
//                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
//                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
//                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
//                        "\nRelationship with infected : " + i.getRelationshipName() +
//                        "\nContacted with : " + i.getInfectedBy() +
//                        "\nCivic Awareness : " + i.getCivicAwareness() + "%" +
//                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
//                        "\nDay of infected : Day " + i.getDayOfInfected() +
//                        "\nDay of dead : Day " + i.getDayOfDead();
//            }
//        }
//        return "";
//    }
//
//    public static String getRecoveredDetail(String id){
//        for(People i : recoveredPeople){
//            if(i.getId().equals(id)){
//                return "Forgetfulness : " + i.getForgetfulness() + "%" +
//                        "\nJob : " +  i.getJob() +
//                        "\nWorkplace : " + i.getWorkplace() +
//                        "\nAge : " + i.getAgeRange() +
//                        "\nGender : " + i.getGender() +
//                        "\nProbability of getting infected : " + i.getProbability() + "%" +
//                        "\nLevel of immunity : " + i.getLevelOfImmunity() + "%" +
//                        "\nHygiene Level : " + i.getHygieneLevel() + "%" +
//                        "\nImmunity After Infected : " + i.getImmunityAfterInfected() +
//                        "\nRelationship with infected : " + i.getRelationshipName() +
//                        "\nCivic Awareness : " + i.getCivicAwareness() + "%" +
//                        "\nContacted with : " + i.getInfectedBy() +
//                        "\nDay of suspected : Day " + i.getDayOfSuspected() +
//                        "\nDay of infected : Day " + i.getDayOfInfected() +
//                        "\nDay of recovered : Day " + i.getDayOfRecovered();
//
//            }
//        }
//        return "";
//    }
//
//    public String getId() { return id.get(); }
//    public SimpleStringProperty idProperty() { return id; }
//    public void setId(String id) { this.id.set(id); }
//
//    public String getForgetfulness() { return forgetfulness.get(); }
//    public SimpleStringProperty forgetfulnessProperty() { return forgetfulness; }
//    public void setForgetfulness(String forgetfulness) { this.forgetfulness.set(forgetfulness); }
//
//    public String getJob() { return job.get(); }
//    public SimpleStringProperty jobProperty() { return job; }
//    public void setJob(String job) { this.job.set(job); }
//
//    public String getWorkplace() { return workplace.get(); }
//    public SimpleStringProperty workplaceProperty() { return workplace; }
//    public void setWorkplace(String workplace) { this.workplace.set(workplace); }
//
//    public String getAgeRange() { return ageRange.get(); }
//    public SimpleStringProperty ageRangeProperty() { return ageRange; }
//    public void setAgeRange(String ageRange) { this.ageRange.set(ageRange); }
//
//    public String getGender() { return gender.get(); }
//    public SimpleStringProperty genderProperty() { return gender; }
//    public void setGender(String gender) { this.gender.set(gender); }
//
//    public String getProbability() { return probability.get(); }
//    public SimpleStringProperty probabilityProperty() { return probability; }
//    public void setProbability(String probability) { this.probability.set(probability); }
//
//    public String getLevelOfImmunity() { return levelOfImmunity.get(); }
//    public SimpleStringProperty levelOfImmunityProperty() { return levelOfImmunity; }
//    public void setLevelOfImmunity(String levelOfImmunity) { this.levelOfImmunity.set(levelOfImmunity); }
//
//    public String getHygieneLevel() { return hygieneLevel.get(); }
//    public SimpleStringProperty hygieneLevelProperty() { return hygieneLevel; }
//    public void setHygieneLevel(String hygieneLevel) { this.hygieneLevel.set(hygieneLevel); }
//
//    public String getImmunityAfterInfected() { return immunityAfterInfected.get(); }
//    public SimpleStringProperty immunityAfterInfectedProperty() { return immunityAfterInfected; }
//    public void setImmunityAfterInfected(String immunityAfterInfected) { this.immunityAfterInfected.set(immunityAfterInfected); }
//
//    public String getRelationshipName() {
//        return relationshipName.get();
//    }
//    public SimpleStringProperty relationshipNameProperty() { return relationshipName; }
//    public void setRelationshipName(String relationshipName) { this.relationshipName.set(relationshipName); }
//
//    public String getDayOfSuspected() { return dayOfSuspected.get(); }
//    public SimpleStringProperty dayOfSuspectedProperty() { return dayOfSuspected; }
//    public void setDayOfSuspected(String dayOfSuspected) { this.dayOfSuspected.set(dayOfSuspected); }
//
//    public String getInfectedBy() { return infectedBy.get(); }
//    public SimpleStringProperty infectedByProperty() { return infectedBy; }
//    public void setInfectedBy(String infectedBy) { this.infectedBy.set(infectedBy); }
//
//    public String getDayOfInfected() { return dayOfInfected.get(); }
//    public SimpleStringProperty dayOfInfectedProperty() { return dayOfInfected; }
//    public void setDayOfInfected(String dayOfInfected) { this.dayOfInfected.set(dayOfInfected);}
//
//    public String getDayOfDead() { return dayOfDead.get(); }
//    public SimpleStringProperty dayOfDeadProperty() { return dayOfDead; }
//    public void setDayOfDead(String dayOfDead) { this.dayOfDead.set(dayOfDead); }
//
//    public String getDayOfRecovered() { return dayOfRecovered.get();}
//    public SimpleStringProperty dayOfRecoveredProperty() { return dayOfRecovered; }
//    public void setDayOfRecovered(String dayOfRecovered) { this.dayOfRecovered.set(dayOfRecovered); }
//
//    public String getCivicAwareness() { return civicAwareness.get(); }
//    public SimpleStringProperty civicAwarenessProperty() { return civicAwareness; }
//    public void setCivicAwareness(String civicAwareness) { this.civicAwareness.set(civicAwareness); }
//
//
//
//
//}
