package cheema.hardeep.sahibdeep.studentify.activities;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;

public class Studentiffffffy {

    public static void main(String[] args) {
        List<StudentClassDummy> studentClasses = new ArrayList<>();

        List<DayTime> dayTimesClass1 = new ArrayList<>();
        dayTimesClass1.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass1.add(new DayTime("Wed", "10:00 AM", "s"));
        dayTimesClass1.add(new DayTime("Tue", "05:00 AM", "s"));
        dayTimesClass1.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 1", dayTimesClass1));

        List<DayTime> dayTimesClass2 = new ArrayList<>();
        dayTimesClass2.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass2.add(new DayTime("Wed", "01:00 PM", "s"));
        dayTimesClass2.add(new DayTime("Tue", "01:00 AM", "s"));
        dayTimesClass2.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 2", dayTimesClass2));

        List<DayTime> dayTimesClass3 = new ArrayList<>();
        dayTimesClass3.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass3.add(new DayTime("Wed", "11:00 AM", "s"));
        dayTimesClass3.add(new DayTime("Tue", "01:00 AM", "s"));
        dayTimesClass3.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 3", dayTimesClass3));


        List<DayTime> dayTimesClass4 = new ArrayList<>();
        dayTimesClass4.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass4.add(new DayTime("Wed", "09:00 AM", "s"));
        dayTimesClass4.add(new DayTime("Tue", "01:00 AM", "s"));
        dayTimesClass4.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 4", dayTimesClass4));

        List<DayTime> dayTimesClass5 = new ArrayList<>();
        dayTimesClass5.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass5.add(new DayTime("Wed", "10:30 AM", "s"));
        dayTimesClass5.add(new DayTime("Tue", "01:00 AM", "s"));
        dayTimesClass5.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 5", dayTimesClass5));


        List<DayTime> dayTimesClass6 = new ArrayList<>();
        dayTimesClass6.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimesClass6.add(new DayTime("Wed", "03:30 PM", "s"));
        dayTimesClass6.add(new DayTime("Tue", "01:00 AM", "s"));
        dayTimesClass6.add(new DayTime("Fri", "01:00 AM", "s"));
        studentClasses.add(new StudentClassDummy("Class 6", dayTimesClass6));


        String selectedDate = "Wed";
        Comparator<StudentClassDummy> dayTimeComparator = (o1, o2) ->
                timeInMiliSeconds(o1.getDayTimeForDay(selectedDate)) - timeInMiliSeconds(o2.getDayTimeForDay(selectedDate));
        Collections.sort(studentClasses, dayTimeComparator);
        for (StudentClassDummy studentClass : studentClasses) {
            System.out.println(studentClass.name + " | " + studentClass.getDayTimeForDay(selectedDate).getStartTime());
        }
    }

    //CRUCIAL FUNCTION: Read and Understand
    private static int timeInMiliSeconds(DayTime dayTime) {
        SimpleDateFormat primaryFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        try {
            return (int) primaryFormat.parse(dayTime.getStartTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

    static class StudentClassDummy{
        String name;
        List<DayTime> dayTimes;

        public StudentClassDummy(String name, List<DayTime> dayTimes) {
            this.name = name;
            this.dayTimes = dayTimes;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<DayTime> getDayTimes() {
            return dayTimes;
        }

        public void setDayTimes(List<DayTime> dayTimes) {
            this.dayTimes = dayTimes;
        }

        //CRUCIAL FUNCTION: Read and Understand
        public DayTime getDayTimeForDay(String day) {
            for (DayTime dayTime : dayTimes) {
                if(dayTime.getDay().equals(day))
                    return dayTime;
            }
            return null;
        }
    }
}
