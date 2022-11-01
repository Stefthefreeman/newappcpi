package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.Local;
import net.kaleoweb.newappcpi.utilities.Materiel;

import java.util.List;

@Dao
public interface LocalDaoModule {
    @Insert
    void insertLocal(Local local);
    
    @Update
    void updateLocal(Local local);
    
    @Delete
    void deleteLocal(Local local);
    
    @Query("Select * from localisation")
    LiveData<List<Local>> getAll();
    
    
    @Query("Select * from localisation")
    Local gettout();
    
    @Query("SELECT COUNT(*) FROM localisation")
    int count();
}
