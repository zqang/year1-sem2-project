package dashboard.fxml;


public class Date {
    private int day;
    private int hour;


    public Date(int day, int hour) {
        this.day = day;
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }
    public void setDay(int day) {
        this.day = day;
    }

    @Override
    public String toString() {
        return "Today is day "+ day +
                " hour " + hour;
    }
}
