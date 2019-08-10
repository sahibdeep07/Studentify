package cheema.hardeep.sahibdeep.studentify.database;


import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreferencesProvider {

    private static final String INFO_PREFERENCES = "info-preferences";
    private static final String FIRST_LAUNCH_KEY = "key-first-launch";


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

        /**
         * Container ; InfoPreferences
         * key-first-launch : false
         */

        /**
         * first time you start app
         * isFirstLaunch - true
         *
         * User go through the UserInformation Screen
         * On save we saveFirstLaunchCompleted key = false
         *
         *
         * Next time user open the app
         * isFirstLaunch - false
         */
    }
}
