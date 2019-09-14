package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cheema.hardeep.sahibdeep.studentify.models.DayTime;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

public class Converters {

    @TypeConverter
    public static List<String> fromString(String value) {
        Type listType = new TypeToken<ArrayList<String>>() {}.getType();
        return new Gson().fromJson(value, listType);
    }

    @TypeConverter
    public static String fromList(List<String> list) {
        Gson gson = new Gson();
        return gson.toJson(list);
    }

    @TypeConverter
    public static Date toDate(Long dateLong) {
        return dateLong == null ? null : new Date(dateLong);
    }

    @TypeConverter
    public static Long fromDate(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static TaskType toTaskType(String name) {
        return TaskType.valueOf(name);
    }

    @TypeConverter
    public static String toInteger(TaskType taskType) {
        return taskType.name();
    }

    @TypeConverter
    public static List<DayTime> toDayTime(String value) {
        Type dayTimeType = new TypeToken<ArrayList<DayTime>>() {}.getType();
        return new Gson().fromJson(value, dayTimeType);
    }

    @TypeConverter
    public static String fromDayTime(List<DayTime> dayTimes) {
        return new Gson().toJson(dayTimes);
    }
}