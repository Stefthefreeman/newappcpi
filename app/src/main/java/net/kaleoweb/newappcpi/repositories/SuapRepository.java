package net.kaleoweb.newappcpi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.dao.SuapDao;
import net.kaleoweb.newappcpi.databases.SuapDatabase;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import java.util.List;

public class SuapRepository {
    
    private final SuapDao suapDao;
    private final LiveData<List<Suapdatas>> suapdatas;
    
    public SuapRepository(Application application){
        SuapDatabase suapDatabase = SuapDatabase.get(application);
        suapDao = suapDatabase.suapDao();
        suapdatas = suapDao.getAllSuap();
        
    }
    
    public LiveData<List<Suapdatas>> getAllBilans(){ return suapdatas;}
    
}
