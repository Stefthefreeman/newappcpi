package net.kaleoweb.newappcpi.Services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.databases.GardesDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.repositories.GardesRepository;
import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SetGardes extends Service {
    private GardesDaoModule daoModule;
    private DaoModule userDaoModule;
    private LiveData<List<Gardes>> mGardes;
    Context context = getApplication();
    private MutableLiveData<List<Gardes>> mutableLiveData = new MutableLiveData<>();
    
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        
        return null;
    }
    
    @Override
    public void onCreate() {
        super.onCreate();
        
        try {
            GardesDatabase gardesDatabase = GardesDatabase.getInstance(this);
            daoModule = gardesDatabase.gardesDaoModule();
            UserDatabase userDatabase = UserDatabase.get(this);
            userDaoModule = userDatabase.daoModule();
            User userdatas = userDaoModule.getById(1);
            mGardes = daoModule.getAll();
            if (mGardes == null) {
                readStream(userdatas.grade, userdatas.nom, userdatas.prenom);
            }
            if (userdatas != null) {
                
                readStream(userdatas.grade, userdatas.nom, userdatas.prenom);
                
            }
            Log.d("Nom", userdatas.nom);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
    private void readStream(String grade, String nom, String prenom) {
        String url = "https://cpi.agence-creation-sc.com/app/send_data.php?user=" + grade.trim() + "-" + nom.trim() + "-" + ucfirst(prenom.replace("é", "e").trim());
        Log.d("URL", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, jsonRequestListener, errorListener);
        getRequestQueue().add(request);
        
    }
    
    private final Response.Listener<JSONObject> jsonRequestListener = response -> {
        
        //   Toast.makeText(getActivity(),response.toString(), Toast.LENGTH_LONG).show();
        try {
            
            
            Log.i("response", response.toString());
            JSONArray jsonArray = response.getJSONArray("planif");
            
            ArrayList<MyGardes> listInters = new ArrayList<>();
            
            int longueur = jsonArray.length();
            for (int i = 0; i < longueur; i++) {
                
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                MyGardes myGardes = new MyGardes();
                myGardes.chef = jsonObject.getString("equipe");
                myGardes.pltt = jsonObject.getString("PLTT");
                myGardes.equi1 = jsonObject.getString("equi1");
                myGardes.equi2 = jsonObject.getString("equi2");
                myGardes.memo = jsonObject.getString("memo");
                myGardes.jysuis = jsonObject.getInt("jysuis");
                myGardes.datesql = jsonObject.getString("date");
                myGardes.mydate = jsonObject.getString("datesql");
                StoreGardes(myGardes, longueur);
            }
            
            
        } catch (JSONException e) {
            Log.e("JSON", e.getLocalizedMessage());
        }
    };
    
    private final Response.ErrorListener errorListener = error -> {
        //  Toast.makeText(getActivity(),"On a une erreur", Toast.LENGTH_LONG).show();
        Log.d("REQUEST", error.toString());
    };
    
    public final static class MyGardes {
        public String mydate;
        public String chef;
        public String pltt;
        public String equi1;
        public String equi2;
        public String memo;
        public int jysuis;
        public String datesql;
        
    }
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(this);
        return requestQueue;
    }
    
    public void StoreGardes(MyGardes myGardes, int longueur) {
        Gardes insertdatas = new Gardes(myGardes.datesql, myGardes.chef, myGardes.pltt, myGardes.equi1, myGardes.equi2, myGardes.memo, myGardes.jysuis, myGardes.mydate);
        
        int total = daoModule.count();
        Log.i("TOTAL", Integer.toString(total));
        Log.i("LONGUEUR", Integer.toString(longueur));
        if (total < longueur) {
            // daoModule.p();
            daoModule.insertGardes(insertdatas);
        }
    }
    
    /**
     * Met le premier caractere d'une chaine en majuscule, le reste en minscule
     *
     * @param chaine
     * @return chaine
     */
    public static String ucfirst(String chaine) {
        return chaine.substring(0, 1).toUpperCase() + chaine.substring(1).toLowerCase();
    }
}
