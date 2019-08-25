package cheema.hardeep.sahibdeep.studentify.models.tables;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;
import java.util.List;

@Entity
public class StudentClass {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int id;

    @ColumnInfo(name = "term_id")
    private int termId;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "professor_name")
    private String professorName;

    @ColumnInfo(name = "room_number")
    private String roomNumber;

    @ColumnInfo(name = "days")
    private List<String> days;

    @ColumnInfo(name = "start_time")
    private Date startTime;

    @ColumnInfo(name = "end_time")
    private Date endTime;

    @ColumnInfo(name = "total_homework")
    private int totalHomework;

    @ColumnInfo(name = "finished_homework")
    private int finishedHomework = 0;

    @ColumnInfo(name = "total_test")
    private int totalTest;

    @ColumnInfo(name = "completed_test")
    private int completedTest = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTermId() {
        return termId;
    }

    public void setTermId(int termId) {
        this.termId = termId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfessorName() {
        return professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public List<String> getDays() {
        return days;
    }

    public void setDays(List<String> days) {
        this.days = days;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getTotalHomework() {
        return totalHomework;
    }

    public void setTotalHomework(int totalHomework) {
        this.totalHomework = totalHomework;
    }

    public int getFinishedHomework() {
        return finishedHomework;
    }

    public void setFinishedHomework(int finishedHomework) {
        this.finishedHomework = finishedHomework;
    }

    public int getTotalTest() {
        return totalTest;
    }

    public void setTotalTest(int totalTest) {
        this.totalTest = totalTest;
    }

    public int getCompletedTest() {
        return completedTest;
    }

    public void setCompletedTest(int completedTest) {
        this.completedTest = completedTest;
    }
}
