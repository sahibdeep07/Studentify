package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.StudentClassDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;
import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.UserInformation;

@Dao
public interface UserInformationDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUserInformation(UserInformation userInformation);

    @Query("SELECT * FROM UserInformation WHERE student_id =:studentId")
    UserInformation getUserInformation(String studentId);

    @Update
    int updateUserInformation(UserInformation userInformation);

    @Delete
    void deleteUserInformation(UserInformation userInformation);
}
