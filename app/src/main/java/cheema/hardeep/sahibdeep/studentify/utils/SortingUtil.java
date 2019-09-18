package cheema.hardeep.sahibdeep.studentify.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;

public class SortingUtil {
    public static List<StudentClass> sortList(List<StudentClass> list){
        List<StudentClass> sortedlist = new ArrayList<>();
        sortedlist.addAll(list);

        List<DayTime> dayTimes = new ArrayList<>();
        for (StudentClass studentClass1: sortedlist) {
            dayTimes.addAll(studentClass1.getDayTimes());
        }
        List<DayTime> amList = new ArrayList<>();
        List<DayTime> pmList = new ArrayList<>();
        for (DayTime dayTime : dayTimes) {
            if(dayTime.getStartTime().contains("AM")) {
                amList.add(dayTime);
            } else {
                pmList.add(dayTime);
            }
        }
        dayTimes.clear();

        Comparator<DayTime> dayTimeComparator = (o1, o2) -> o1.getStartTime().compareTo(o2.getStartTime());
        Collections.sort(amList, dayTimeComparator);
        Collections.sort(pmList, dayTimeComparator);
        Collections.addAll(amList, pmList.toArray(new DayTime[0]));
//
        for (DayTime dayTime : amList) {
            dayTimes.add(dayTime);
        }

        for (StudentClass studentClass : sortedlist){
            studentClass.
        }
//        int i = 0;
//
//        for (StudentClass studentClass : sortedlist) {
//            for (DayTime daytime: dayTimes) {
//                List<DayTime> tempDayTime= new ArrayList<>();
//                daytime = dayTimes.get(i);
//                tempDayTime.add(daytime);
//                studentClass.setDayTimes(tempDayTime);
//                if(i<2) i+=1;
//                tempDayTime.clear();
//            }
//        }


        return sortedlist;
    }
}
