package net.kaleoweb.newappcpi.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import net.kaleoweb.newappcpi.utilities.Pharma;

import java.util.List;

@Dao
public interface PharmacieDaoModule {
    
    
    @Insert
    void insertPharma(Pharma pharma);
    
    @Update
    void updatePharma(Pharma pharma);
    
    @Delete
    void deletePharma(Pharma pharma);
    
    @Query("delete from pharmacie")
    int delete();
    
    @Query("UPDATE pharmacie SET restant = :restant, peremption = :peremp , bg = :bg WHERE id = :id")
     int up(int restant,String peremp,int bg,int id);
    
    @Query("Select * from pharmacie")
    LiveData<List<Pharma>> getAll();
    
    @Query("Select * from pharmacie WHERE id = :id")
    LiveData<List<Pharma>> getById(int id);
    
    @Query("Select * from pharmacie")
    Pharma gettout();
    
    @Query("Select * from pharmacie")
    List<Pharma> getup();
    
    @Query("SELECT COUNT(*) FROM pharmacie")
    int count();
    
    
}
