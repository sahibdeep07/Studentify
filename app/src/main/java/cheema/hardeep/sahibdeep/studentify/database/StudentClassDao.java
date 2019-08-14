package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.StudentClassDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;

@Dao
public interface StudentClassDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertStudentClass(StudentClass studentClass);

    @Query("SELECT * FROM StudentClass")
    List<StudentClass> getAllStudentClasses();

    @Query("SELECT * FROM StudentClass WHERE term_id =:termId")
    List<StudentClass> getStudentClasses(int termId);

    @Query("SELECT * FROM StudentClass WHERE days LIKE :days")
    List<StudentClass> getStudentClassesWithDay(String days);

    @Query("SELECT * FROM StudentClass WHERE days LIKE :day")
    List<StudentClassDetails> getStudentClassesWithTaks(String day);

    @Update
    int updateStudentClass(StudentClass studentClass);

    @Delete
    void deleteStudentClass(StudentClass studentClass);
}
