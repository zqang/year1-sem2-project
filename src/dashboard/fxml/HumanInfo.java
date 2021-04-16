package dashboard.fxml;




public class HumanInfo {
     private int forgetfulness , immunity, WorkNature ;
    private String ID,job , workplace , agerange , Gender,contactNumber ;
    LinkedList<Integer,Integer,String> activity=new LinkedList<>();


    public HumanInfo() {
        this.ID = "" ;
        this.forgetfulness = 0;
        this.immunity = 0 ;
        this.job = "";
        this.workplace = "";
        this.agerange = "";
        this.Gender = "";
        this.WorkNature = 0 ;
    }
    public void setActivity(int a,int b,String c){
        activity.addNode(a,b,c);
    }

    public void deleteActivity(int day){
        activity.deleteDay(day);
        activity.deleteFrontNode();
    }

    //reset the activity during quarantine
    public String showData(String a){
    return activity.showData(a);
    }
    public HumanInfo (String ID , String Gender , String AgeRange , String Job , String forgetfulness , String WorkNature, String immunity ,String contactNumber, String WorkPlace){
        this.ID = ID;
        this.Gender = Gender ;
        this.agerange = AgeRange ;
        this.job = Job ;
        this.contactNumber = contactNumber;
        this.forgetfulness = Integer.parseInt(forgetfulness);
        this.WorkNature = Integer.parseInt(WorkNature);
        this.immunity = Integer.parseInt(immunity);
        this.workplace = WorkPlace ;
    }
    public HumanInfo(String a){
         this.ID = a;
    }
    public void setID(String ID) {
        this.ID = ID;
    }

    public void setForgetfulness(int forgetfulness) {
        this.forgetfulness = forgetfulness;
    }

    public void setImmunity(int immunity) {
        this.immunity = immunity;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public void setWorkPlace(String workPlace) {
        this.workplace = workPlace;
    }

    public void setAgeRange(String agerange) {
        this.agerange = agerange;
    }
    
    public void setGender(String Gender) {
        this.Gender = Gender;
    }
    
    public void setWorkNature(int WorkNature){
        this.WorkNature = WorkNature ;
    }


    public String getID() {
        return ID;
    }

    public int getForgetfulness() {
        return forgetfulness;
    }

    public String getJob() {
        return job;
    }

    public String getWorkPlace() {
        return workplace;
    }

    public String getAgeRange() {
        return agerange;
    }
    
    public String getGender(){
        return Gender ;
    }
    
     public int getWorkNature(){
        return  WorkNature ;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
}
