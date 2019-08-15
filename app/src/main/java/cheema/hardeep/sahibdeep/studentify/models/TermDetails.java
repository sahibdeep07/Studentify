package cheema.hardeep.sahibdeep.studentify.models;


import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.ArrayList;
import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.tables.StudentClass;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;

public class TermDetails {

    @Embedded
    private Term term;

    @Relation(parentColumn = "id", entityColumn = "term_id", entity = StudentClass.class)
    private List<StudentClass> studentClasses = new ArrayList<>();

    public Term getTerm() {
        return term;
    }

    public void setTerm(Term term) {
        this.term = term;
    }

    public List<StudentClass> getStudentClasses() {
        return studentClasses;
    }

    public void setStudentClasses(List<StudentClass> studentClasses) {
        this.studentClasses = studentClasses;
    }
}
