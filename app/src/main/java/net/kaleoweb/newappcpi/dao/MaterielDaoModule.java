package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.Materiel;

import java.util.List;

@Dao
public interface MaterielDaoModule {
    @Insert
    void insertMateriel(Materiel materiel);
    
    @Update
    void updateMateriel(Materiel materiel);
    
    @Delete
    void deleteMateriel(Materiel materiel);
    
    @Query("Select * from materiel")
    LiveData<List<Materiel>> getAll();
    
    @Query("Select * from materiel WHERE localisation = :id")
    LiveData<List<Materiel>> getloc(int id);
    
    @Query("Select * from materiel")
    Materiel gettout();
    
    @Query("SELECT COUNT(*) FROM materiel")
    int count();
}
