package net.kaleoweb.newappcpi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.databases.PharmacieDatabase;
import net.kaleoweb.newappcpi.utilities.Pharma;

import java.util.List;

public class PharmacieRepository {
    
    private final PharmacieDaoModule pharmacieDaoModule;
    
    private final LiveData<List<Pharma>> mList;
    
    public PharmacieRepository(Application application, int id) {
        
        PharmacieDatabase pharmacieDatabase = PharmacieDatabase.get(application);
        pharmacieDaoModule = pharmacieDatabase.pharmacieDaoModule();
        mList = pharmacieDaoModule.getAll();
        
    }
    
    public LiveData<List<Pharma>> getAllLocal() {
        return mList;
    }
    
}
