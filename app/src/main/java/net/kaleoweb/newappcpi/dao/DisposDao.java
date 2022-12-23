package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.Dispos;
import net.kaleoweb.newappcpi.utilities.Pharma;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import java.util.List;

@Dao
public interface DisposDao {
    
    @Insert
    void insertDispos(Dispos dispos);
    
    @Delete
    void deleteDispos(Dispos dispos);
    
    @Update
    void updateDispos(Dispos dispos);
    
    @Query("Select * from dispos")
    LiveData<List<Dispos>> getAllDispos();
    
    @Query("Select * from dispos")
    List<Dispos> getAllDisposList();
    
    
    @Query("SELECT COUNT(*) FROM dispos")
    int count();
    
    @Query("DELETE  FROM dispos WHERE id = :d")
    int d(int d);
    
    @Query("Select * from dispos")
    Dispos gettout();
    
    @Query("Select dt from dispos WHERE id = :dd")
    String getDate(int dd);
    
    @Query("Select dtsql from dispos WHERE id = :dd")
    String getDateSql(int dd);
    
    @Query("UPDATE dispos SET dt = :dt, dtsql = :dtsql WHERE id = :dd")
    int upDispo(int dd,String dt,String dtsql);
    
    
    
}
