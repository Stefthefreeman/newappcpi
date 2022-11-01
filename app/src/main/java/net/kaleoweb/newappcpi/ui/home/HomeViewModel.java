package net.kaleoweb.newappcpi.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.databases.GardesDatabase;
import net.kaleoweb.newappcpi.repositories.GardesRepository;
import net.kaleoweb.newappcpi.utilities.Gardes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends AndroidViewModel {
    private final GardesDaoModule daoModule;
    private final LiveData<List<Gardes>> mgardes;
    
    
    public HomeViewModel(Application application) {
        super(application);
        GardesDatabase gardesDatabase = GardesDatabase.getInstance(application);
        daoModule = gardesDatabase.gardesDaoModule();
        mgardes = daoModule.getAll();
        
        
    }
    
    public LiveData<List<Gardes>> getList() {
        return mgardes;
    }
    
}