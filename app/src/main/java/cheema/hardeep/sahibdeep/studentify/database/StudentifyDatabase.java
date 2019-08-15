package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

@Database(entities = {UserInformation.class, Term.class, StudentClass.class, Task.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class StudentifyDatabase extends RoomDatabase {

    public abstract UserInformationDao userInformationDao();

    public abstract TermDao termDao();

    public abstract StudentClassDao studentClassDao();

    public abstract TaskDao taskDao();
}
