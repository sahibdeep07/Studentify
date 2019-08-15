package cheema.hardeep.sahibdeep.studentify.database;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesProvider {

    private static final String EMPTY = "";
    private static final String INFO_PREFERENCES = "info-preferences";
    private static final String FIRST_LAUNCH_KEY = "key-first-launch";
    private static final String STUDENT_ID_KEY = "key-student-id";


    private static SharedPreferences getInfoPreferences(Context context) {
        return context.getSharedPreferences(INFO_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static void saveFirstLaunchCompleted(Context context) {
        getInfoPreferences(context)
                .edit()
                .putBoolean(FIRST_LAUNCH_KEY, false)
                .apply();
    }

    public static boolean isFirstLaunch(Context context) {
        return getInfoPreferences(context).getBoolean(FIRST_LAUNCH_KEY, true);
    }

    public static void saveUserId(Context context, String studentId) {
        getInfoPreferences(context)
                .edit()
                .putString(STUDENT_ID_KEY, studentId)
                .apply();
    }

    public static String getStudentId(Context context) {
        return getInfoPreferences(context).getString(STUDENT_ID_KEY, EMPTY);
    }
}
