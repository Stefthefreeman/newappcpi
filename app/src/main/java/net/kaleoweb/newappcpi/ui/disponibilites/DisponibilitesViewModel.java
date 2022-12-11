package net.kaleoweb.newappcpi.ui.disponibilites;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.databases.DisposDatabase;
import net.kaleoweb.newappcpi.utilities.Dispos;

import java.util.List;

public class DisponibilitesViewModel extends AndroidViewModel {
    
    private DisposDao disposDao;
    
    private LiveData<List<Dispos>> mdispos;
    private List<Dispos> lesDispos;
    
    public DisponibilitesViewModel(Application application){
        super(application);
    
        DisposDatabase disposDatabase = DisposDatabase.get(application);
        disposDao = disposDatabase.disposDao();
         mdispos = disposDao.getAllDispos();
         lesDispos = disposDao.getAllDisposList();
        
    }
    
    public LiveData<List<Dispos>> getdispos() {
        return mdispos;
    }
    
    public List<Dispos> getDispoList(){ return lesDispos;}
    
}