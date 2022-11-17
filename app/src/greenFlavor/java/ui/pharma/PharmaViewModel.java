package ui.pharma;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.MainActivity;
import net.kaleoweb.newappcpi.Services.SetPharma;
import net.kaleoweb.newappcpi.Splash;
import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.databases.PharmacieDatabase;
import net.kaleoweb.newappcpi.utilities.Pharma;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PharmaViewModel extends AndroidViewModel {
    
    private PharmacieDaoModule pharmacieDaoModule;
    
    private LiveData<List<Pharma>> mPharmacie;
    
    
    public PharmaViewModel(Application application) {
        super(application);
        PharmacieDatabase pharmacieDatabase = PharmacieDatabase.get(application);
        pharmacieDaoModule = pharmacieDatabase.pharmacieDaoModule();
        mPharmacie = pharmacieDaoModule.getAll();
        
    }
    
    public LiveData<List<Pharma>> getPostsList() {
        return mPharmacie;
    }
    
    public void refresh() {
        mPharmacie = pharmacieDaoModule.getAll();
    }
    
    public void updatePharma() {
        pharmacieDaoModule.delete();
    }
    
    @Override
    protected void onCleared() {
        super.onCleared();
    }
}

