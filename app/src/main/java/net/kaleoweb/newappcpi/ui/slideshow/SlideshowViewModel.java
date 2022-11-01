package net.kaleoweb.newappcpi.ui.slideshow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import net.kaleoweb.newappcpi.repositories.SuapRepository;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SlideshowViewModel extends AndroidViewModel {

  private final SuapRepository suapRepository;
    private final LiveData<List<Suapdatas>> mDatas;

    public SlideshowViewModel(@NonNull @NotNull Application application) {
        super(application);
        
        suapRepository = new SuapRepository(application);
        mDatas = suapRepository.getAllBilans();
       
    }

    public LiveData<List<Suapdatas>> getBilans() {
        return mDatas;
    }
    
    public void reFreshDatas(){
        LiveData<List<Suapdatas>> suap = suapRepository.getAllBilans();
       
        
    }
}