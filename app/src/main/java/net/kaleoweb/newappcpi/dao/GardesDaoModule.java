package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.User;

import java.util.List;

@Dao
public interface GardesDaoModule {
    
    @Insert
    void insertGardes(Gardes gardes);
    
    @Update
    void updateGardes(Gardes gardes);
    
    @Delete
    void deletegarde(Gardes garde);
    
    @Query("Select * from gardes ORDER BY datesql ASC")
    LiveData<List<Gardes>> getAll();
    
    @Query("Select * from gardes")
    Gardes gettout();
    
    @Query("SELECT COUNT(*) FROM gardes")
    int count();
    
    @Query("DELETE FROM gardes WHERE id = :theid")
    int i(int theid);
    
    @Query("DELETE FROM gardes WHERE 1")
    void p();
    
    
}
