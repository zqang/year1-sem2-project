package dashboard.fxml;

public class Event {
    private int day;
    private int hour;
    private String place;
    private String humanInfo;

    public Event(int day, int hour, String place, String humanInfo) {
        this.day = day;
        this.hour = hour;
        this.place = place;
        this.humanInfo = humanInfo;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getHour() {
        return hour;
    }

    public String getPlace() {
        return place;
    }

    public String getHumanInfo() {
        return humanInfo;
    }

}
