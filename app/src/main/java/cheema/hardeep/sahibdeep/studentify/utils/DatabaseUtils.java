package cheema.hardeep.sahibdeep.studentify.utils;

import android.content.Context;

import cheema.hardeep.sahibdeep.studentify.database.SharedPreferencesProvider;
import cheema.hardeep.sahibdeep.studentify.database.StudentifyDatabaseProvider;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

public class DatabaseUtils {

    private static String getUserId(Context context) {
        return SharedPreferencesProvider.getStudentId(context);
    }

    public static UserInformation getUserInformation(Context context) {
        return StudentifyDatabaseProvider.getUserInformationDao(context).getUserInformation(getUserId(context));
    }

    public static Term getUserTerm(Context context) {
        return StudentifyDatabaseProvider.getTermDao(context).getTerm(getUserInformation(context).getTermName());
    }
}
