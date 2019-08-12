package cheema.hardeep.sahibdeep.studentify.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "student_class_id")
    @ForeignKey(entity = StudentClass.class,
            parentColumns = {"id"},
            childColumns = {"student_class_id"},
            onDelete = ForeignKey.CASCADE)
    private int studentClassId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "type")
    private TaskType type;

    @ColumnInfo(name = "date_time")
    private Date dateTime;

    @ColumnInfo(name = "notes")
    private String notes;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentClassId() {
        return studentClassId;
    }

    public void setStudentClassId(int studentClassId) {
        this.studentClassId = studentClassId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
