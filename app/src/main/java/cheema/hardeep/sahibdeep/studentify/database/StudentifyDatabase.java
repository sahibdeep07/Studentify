package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import cheema.hardeep.sahibdeep.studentify.models.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.Task;
import cheema.hardeep.sahibdeep.studentify.models.Term;
import cheema.hardeep.sahibdeep.studentify.models.UserInformation;

@Database(entities = {UserInformation.class, Term.class, StudentClass.class, Task.class}, version = 1)
@TypeConverters(Converters.class)
public abstract class StudentifyDatabase extends RoomDatabase {

    public abstract StudentifyDao studentifyDao();
}
