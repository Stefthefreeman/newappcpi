package net.kaleoweb.newappcpi.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.databases.DisposDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.Dispos;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GetDisposService extends Service {
    private DisposDao disposDao;
    User userdatas;
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        DisposDatabase disposDatabase = DisposDatabase.get(this);
        disposDao = disposDatabase.disposDao();
        DaoModule daoModule;
        UserDatabase userDatabase = UserDatabase.get(this);
        daoModule = userDatabase.daoModule();
        userdatas = daoModule.getById(1);
        readStream();
        
    }
    
    private void readStream() {
        String url = "https://cpi.agence-creation-sc.com/app/dispofeed.php?name="+userdatas.nom;
        Log.d("URL", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, jsonRequestListener, errorListener);
        getRequestQueue().add(request);
        
    }
    
    private final Response.Listener<JSONObject> jsonRequestListener = response -> {
        
        //   Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_LONG).show();
        try {
            JSONArray jsonArray = response.getJSONArray("dispos");
            int longueur = jsonArray.length();
            for (int i = 0; i < longueur; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MyDispos mydispos = new MyDispos();
                
                mydispos.mydate = jsonObject.getString("date");
                
                storeDispos(mydispos);
            }
            
            
        } catch (JSONException e) {
            Log.e("JSON", e.getLocalizedMessage());
        }
        
    };
    
    private final Response.ErrorListener errorListener = error -> {
      
        Log.d("REQUEST", error.toString());
    };
    
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }
    
    private static class MyDispos{
         public String mydate;
    }
    
    private void storeDispos(MyDispos mydispos){
        Dispos dispos = new Dispos(mydispos.mydate);
        disposDao.insertDispos(dispos);
    }
}
