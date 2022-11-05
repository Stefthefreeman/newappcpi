package net.kaleoweb.newappcpi.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.Materiel;
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
    
    @Query("Select * from pharmacie")
    LiveData<List<Pharma>> getAll();
    
    @Query("Select * from pharmacie WHERE id = :id")
    LiveData<List<Pharma>> getById(int id);
    
    @Query("Select * from pharmacie")
    Pharma gettout();
    
    @Query("SELECT COUNT(*) FROM pharmacie")
    int count();
    
    
}
