package net.kaleoweb.newappcpi.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import net.kaleoweb.newappcpi.utilities.User;

import java.util.List;

@Dao
public interface DaoModule {
    @Insert
    void insertUser(User user);
    
    @Query("Select * from User where id = :id")
    User getById(long id);
    
    @Update
    void updateUser(User user);
    
    @Delete
    void deleteUser(User user);
    
    @Query("Select * from User")
    LiveData<List<User>> getAll();
    
    
}
