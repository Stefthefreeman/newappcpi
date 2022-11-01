package net.kaleoweb.newappcpi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.dao.LocalDaoModule;
import net.kaleoweb.newappcpi.databases.LocalDatabase;
import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.Local;

import java.util.List;

public class LocalRepository {
    
    private final LocalDaoModule localDaoModule;
    private final LiveData<List<Local>> mLocal;
    
    public LocalRepository(Application application){
    
        LocalDatabase localDatabase = LocalDatabase.get(application);
        localDaoModule = localDatabase.localDaoModule();
        mLocal = localDaoModule.getAll();
        
    }
    
    public LiveData<List<Local>> getAllLocal() {
        return mLocal;
    }
}
