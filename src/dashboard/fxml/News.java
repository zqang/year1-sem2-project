package dashboard.fxml;

public class News {
    private final String[] newsCompanyName = {"The Star","New York Times", "New Straits Times","China Press"};
    private final String[] reportBeforeInfected = {""};

    private String report;
    private int date;
    private String newsCompany;

    //daily
    private int numberOfSuspected;
    private int numberOfInfected;
    private int infectedPlace;
    private int numberOfRecovered;
    private int numberOfDead;
    private int rNaught;


    //total
    private int totalNumberOfSuspected;
    private int totalNumberOfInfected;
    private int totalNumberOfRecovered;
    private int totalNumberOfDead;



    public News(String report, int date) {
        this.report = report;
        this.date = date;
    }

    public void takeReport(){
        String[] reportWhenNoCorona = {""};
        String reportWhenCorona;
    }



    public String getReport() { return report; }
    public void setReport(String report) { this.report = report; }

    public int getDate() { return date; }
    public void setDate(int date) { this.date = date; }

    public String getNewsCompany() { return newsCompany; }
    public void setNewsCompany(String newsCompany) { this.newsCompany = newsCompany; }
}
