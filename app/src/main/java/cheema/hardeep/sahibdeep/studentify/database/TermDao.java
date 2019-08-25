package cheema.hardeep.sahibdeep.studentify.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import cheema.hardeep.sahibdeep.studentify.models.TermDetails;
import cheema.hardeep.sahibdeep.studentify.models.tables.Term;

@Dao
public interface TermDao {

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

    @Query("DELETE FROM Term")
    void deleteAll();
}
