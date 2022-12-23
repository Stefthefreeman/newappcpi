package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import net.kaleoweb.newappcpi.utilities.Suapdatas;
import java.util.List;

@Dao
public interface SuapDao {
    
    @Insert
    void insertSuap(Suapdatas suapdatas);
    
    @Delete
    void deleteSupa(Suapdatas suapdatas);
    
    @Update
    void updateSuap(Suapdatas suapdatas);
    
    @Query("Select * from suap")
    LiveData<List<Suapdatas>> getAllSuap();
    
    @Query("Select * from suap")
    List<Suapdatas> getAllSuapList();
    
    @Query("SELECT COUNT(*) FROM suap")
    int count();
    
    @Query("DELETE  FROM suap WHERE id = :d")
    int d(int d);
}
