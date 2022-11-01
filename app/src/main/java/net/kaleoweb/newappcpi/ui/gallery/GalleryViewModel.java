package net.kaleoweb.newappcpi.ui.gallery;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import net.kaleoweb.newappcpi.repositories.LocalRepository;
import net.kaleoweb.newappcpi.utilities.Local;

import java.util.List;

public class GalleryViewModel extends AndroidViewModel {
    private final LocalRepository localRepository;
    private final LiveData<List<Local>> mLocal;
    

    public GalleryViewModel(Application application) {
        super(application);
        
        localRepository = new LocalRepository(application);
        mLocal = localRepository.getAllLocal();
    }

    public LiveData<List<Local>> getListLocal() {
        return mLocal;
    }
}