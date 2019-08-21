package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.tables.Task;
import cheema.hardeep.sahibdeep.studentify.models.tables.TaskType;

@Dao
public interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertTask(Task task);

    @Query("SELECT * FROM Task")
    List<Task> getAllTasks();

    @Query("SELECT * FROM Task WHERE id =:id")
    Task getTask(int id);

    @Query("SELECT * FROM Task WHERE student_class_id =:studentClassId")
    List<Task> getTasks(int studentClassId);

    @Query("SELECT * FROM Task WHERE student_class_id =:studentClassId AND type=:taskType")
    List<Task> getTaskWithType(int studentClassId, TaskType taskType);

    @Update
    int upddateTask(Task task);

    @Delete
    void deleteTask(Task task);

    @Query("DELETE FROM Task")
    void deleteAll();
}
