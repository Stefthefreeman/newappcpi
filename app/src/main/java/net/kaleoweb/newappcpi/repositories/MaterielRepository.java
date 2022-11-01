package net.kaleoweb.newappcpi.repositories;

import android.app.Application;

import androidx.lifecycle.LiveData;
import net.kaleoweb.newappcpi.dao.MaterielDaoModule;
import net.kaleoweb.newappcpi.databases.MaterielDatabase;
import net.kaleoweb.newappcpi.utilities.Materiel;
import java.util.List;

public class MaterielRepository {
    
    private final MaterielDaoModule materielDaoModule;
    private final LiveData<List<Materiel>> mList;
    
    public MaterielRepository(Application application, int id){
    
        MaterielDatabase materielDatabase = MaterielDatabase.get(application);
        materielDaoModule = materielDatabase.materielDaoModule();
        mList = materielDaoModule.getloc(id);
        
    }
    public LiveData<List<Materiel>> getAllLocal() {
        return mList;
    }
}
