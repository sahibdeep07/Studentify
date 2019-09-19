package cheema.hardeep.sahibdeep.studentify.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;

public class SortingUtil {

    public static List<StudentClass> sortList(List<StudentClass> list, String day) {
        List<StudentClass> sortedlist = new ArrayList<>();
        sortedlist.addAll(list);

        Comparator<StudentClass> dayTimeComparator = (o1, o2) ->
                timeInMiliSeconds(o1.getDayTimeForDay(day)) - timeInMiliSeconds(o2.getDayTimeForDay(day));
        Collections.sort(sortedlist, dayTimeComparator);
        return sortedlist;
    }

    private static int timeInMiliSeconds(DayTime dayTime) {
        SimpleDateFormat primaryFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());
        try {
            return (int) primaryFormat.parse(dayTime.getStartTime()).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }

}