package cheema.hardeep.sahibdeep.studentify.activities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;

public class Studentiffffffy {

    public static void main(String[] args) {

        List<DayTime> dayTimes = new ArrayList<>();
        dayTimes.add(new DayTime("Mon", "02:00 AM", "s"));
        dayTimes.add(new DayTime("Mon", "01:00 PM", "s"));
        dayTimes.add(new DayTime("Mon", "05:00 AM", "s"));
        dayTimes.add(new DayTime("Mon", "01:00 AM", "s"));
        dayTimes.add(new DayTime("Mon", "03:00 PM",  "s"));
        dayTimes.add(new DayTime("Mon", "09:30 PM","s"));

        List<DayTime> amList = new ArrayList<>();
        List<DayTime> pmList = new ArrayList<>();
        for (DayTime dayTime : dayTimes) {
            if(dayTime.getStartTime().contains("AM")) {
                amList.add(dayTime);
            } else {
                pmList.add(dayTime);
            }
        }

        Comparator<DayTime> dayTimeComparator = (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime());
        Collections.sort(amList, dayTimeComparator);
        Collections.sort(pmList, dayTimeComparator);
        Collections.addAll(amList, pmList.toArray(new DayTime[0]));

        for (DayTime dayTime : amList) {
            System.out.println(dayTime.getStartTime());
        }
    }
}
