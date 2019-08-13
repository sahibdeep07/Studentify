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
public interface StudentifyDao {

    /**
     * UserInformation Table Access Methods
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertUserInformation(UserInformation userInformation);

    @Query("SELECT * FROM UserInformation WHERE student_id =:studentId")
    UserInformation getUserInformation(String studentId);

    @Update
    int updateUserInformation(UserInformation userInformation);

    @Delete
    void deleteUserInformation(UserInformation userInformation);

    /**
     * Term Table Access Methods
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTerm(Term term);

    @Query("SELECT * FROM Term")
    List<Term> getAllTerms();

    @Query("SELECT * FROM Term WHERE name =:termName")
    Term getTerm(String termName);

    @Query("SELECT * FROM Term WHERE name =:termName")
    TermDetails getTermWithClasses(String termName);

    @Update
    int updateTerm(Term term);

    @Delete
    void deleteTerm(Term term);

    /**
     * Classes Table Access Methods
     */
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

    /**
     * Classes Table Access Methods
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTask(Task task);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM Task WHERE student_class_id =:studentClassId")
    List<Task> getTasks(int studentClassId);

    @Query("SELECT * FROM Task WHERE student_class_id =:studentClassId AND type=:taskType")
    List<Task> getTaskWithType(int studentClassId, TaskType taskType);

    @Update
    int upddateTask(Task task);

    @Delete
    void deleteTask(Task task);
}
