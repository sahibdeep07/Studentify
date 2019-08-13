package cheema.hardeep.sahibdeep.studentify.models;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

public class StudentClassDetails {

    @Embedded
    private StudentClass studentClass;

    @Relation(parentColumn = "id", entityColumn = "student_class_id", entity = Task.class)
    private List<Task> tasks;

    public StudentClass getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(StudentClass studentClass) {
        this.studentClass = studentClass;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
