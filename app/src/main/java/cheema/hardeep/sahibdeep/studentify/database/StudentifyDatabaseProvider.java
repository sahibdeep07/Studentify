package cheema.hardeep.sahibdeep.studentify.database;

import android.content.Context;

import androidx.room.Room;

public class StudentifyDatabaseProvider {

    private static final String STUDENTIFY_DATABASE_NAME = "studentify-db";

    private static StudentifyDatabase INSTANCE;

    private static StudentifyDatabase getDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(context.getApplicationContext(),
                            StudentifyDatabase.class, STUDENTIFY_DATABASE_NAME)
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static UserInformationDao getUserInformationDao(Context context) {
        return getDatabase(context).userInformationDao();
    }

    public static TermDao getTermDao(Context context) {
        return getDatabase(context).termDao();
    }

    public static StudentClassDao getStudentClassDao(Context context) {
        return getDatabase(context).studentClassDao();
    }

    public static TaskDao getTaskDao(Context context) {
        return getDatabase(context).taskDao();
    }

    private static void destroyInstance() {
        INSTANCE = null;
    }
}
