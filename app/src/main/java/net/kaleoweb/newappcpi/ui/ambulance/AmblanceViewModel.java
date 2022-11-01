package net.kaleoweb.newappcpi.ui.ambulance;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import net.kaleoweb.newappcpi.repositories.MaterielRepository;
import net.kaleoweb.newappcpi.utilities.Local;
import net.kaleoweb.newappcpi.utilities.Materiel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AmblanceViewModel extends AndroidViewModel {
    
    private final MaterielRepository materielRepository;
    private final LiveData<List<Materiel>> mMatos;
    
    public AmblanceViewModel(@NonNull @NotNull Application application,int id) {
        super(application);
       
        materielRepository= new MaterielRepository(application,id);
        mMatos = materielRepository.getAllLocal();
    }
    
    public LiveData<List<Materiel>> getmMatos(){
        return mMatos;
    }
}
