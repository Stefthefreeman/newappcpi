package net.kaleoweb.newappcpi.ui.ambulance;

import android.app.Application;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class MyViewModelFactory implements ViewModelProvider.Factory{
    
    private final Application mApplication;
    private final int mParam;
    
    
    public MyViewModelFactory(Application application, int param) {
        mApplication = application;
        mParam = param;
    }
    
    
    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        return (T) new AmblanceViewModel(mApplication, mParam);
    }
}
