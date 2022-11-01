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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SetPharma extends Service {
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        readStream();
    }
    
    private void readStream() {
        String url = "https://cpi.agence-creation-sc.com/app/pharmafeed.php";
        Log.d("URL", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, jsonRequestListener, errorListener);
        getRequestQueue().add(request);
        
    }
    
    private final Response.Listener<JSONObject> jsonRequestListener = response -> {
        
        //   Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_LONG).show();
        try {
            
            
            Log.i("response", response.toString());
            JSONArray jsonArray = response.getJSONArray("ui/pharma");
            
            ArrayList<SetGardes.MyGardes> listInters = new ArrayList<>();
            
            int longueur = jsonArray.length();
            for (int i = 0; i < longueur; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MyPharma myPharma = new MyPharma();
                myPharma.id = jsonObject.getInt("id");
                myPharma.cat_id = jsonObject.getInt("cat_id");
                myPharma.designation = jsonObject.getString("designation");
                myPharma.dotation = jsonObject.getInt("dotation");
                myPharma.restant = jsonObject.getInt("restant");
                myPharma.peremption = jsonObject.getString("peremption");
                
                
            }
            
            
        } catch (JSONException e) {
            Log.e("JSON", e.getLocalizedMessage());
        }
    };
    
    private final Response.ErrorListener errorListener = error -> {
        //  Toast.makeText(getActivity(),"On a une erreur", Toast.LENGTH_LONG).show();
        Log.d("REQUEST", error.toString());
    };
    
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }
    
    public final static class MyPharma {
        public int id;
        public int cat_id;
        public String designation;
        public int dotation;
        public int restant;
        public String peremption;
        
    }
}
