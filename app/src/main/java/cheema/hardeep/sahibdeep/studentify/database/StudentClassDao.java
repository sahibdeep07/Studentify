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

    @Query("SELECT * FROM StudentClass WHERE id =:id")
    StudentClass getStudentClass(int id);

    @Query("SELECT * FROM StudentClass WHERE term_id =:termId")
    List<StudentClass> getStudentClasses(int termId);

    @Query("SELECT * FROM StudentClass WHERE days LIKE :days")
    List<StudentClass> getStudentClassesWithDay(String days);

    @Query("SELECT * FROM StudentClass WHERE days LIKE :day")
    List<StudentClassDetails> getStudentClassesWithTasks(String day);

    @Update
    int updateStudentClass(StudentClass studentClass);

    @Query("UPDATE StudentClass SET total_homework = total_homework + :value WHERE id =:classId")
    int updateStudentClassTotalHomework(int classId, int value);

    @Query("UPDATE StudentClass SET total_test = total_test + :value WHERE id =:classId")
    int updateStudentClassTotalTest(int classId, int value);

    @Query("UPDATE StudentClass SET finished_homework = finished_homework + 1 WHERE id =:classId")
    int updateStudentClassFinishedHomework(int classId);

    @Query("UPDATE StudentClass SET completed_test = completed_test + 1 WHERE id =:classId")
    int updateStudentClassCompletedTest(int classId);

    @Delete
    void deleteStudentClass(StudentClass studentClass);

    @Query("DELETE FROM StudentClass")
    void deleteAll();
}
