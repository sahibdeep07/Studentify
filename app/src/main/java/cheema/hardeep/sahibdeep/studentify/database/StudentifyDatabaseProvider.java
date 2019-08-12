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

    public static StudentifyDao getDao(Context context) {
        return getDatabase(context).studentifyDao();
    }

    private static void destroyInstance() {
        INSTANCE = null;
    }
}
