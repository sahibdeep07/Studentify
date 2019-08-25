package cheema.hardeep.sahibdeep.studentify.models;

public class ScheduleDay {

    private int date;

    private String day;

    public ScheduleDay(int date, String day) {
        this.date = date;
        this.day = day;
    }

    public int getDate() {
        return date;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }
}
