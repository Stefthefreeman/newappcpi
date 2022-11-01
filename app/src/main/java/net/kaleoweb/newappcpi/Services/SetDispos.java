package net.kaleoweb.newappcpi.Services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONObject;

public class SetDispos extends Service {
    private DaoModule userDaoModule;
    
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        try {
            
            UserDatabase userDatabase = UserDatabase.get(this);
            userDaoModule = userDatabase.daoModule();
            User userdatas = userDaoModule.getById(1);
            
            if (userdatas != null) {
                senDatas(userdatas.nom, userdatas.prenom);
            }
            Log.d("Nom", userdatas.nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }
    
    private void senDatas(String nom, String dtdispo) {
        String URL = "https://cpi.agence-creation-sc.com/app/setdispos.php?name=" + nom + "dispo=" + dtdispo;
        
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
                    
                    @Override
                    public void onResponse(JSONObject response) {
                        // textView.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {
                    
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        
                    }
                });
        
        
    }
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }
}
