package net.kaleoweb.newappcpi.repositories;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.databases.GardesDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.Suapdatas;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GardesRepository {
    
    private final GardesDaoModule daoModule;
    private final DaoModule userDaoModule;
    private final LiveData<List<Gardes>> mGardes;
    
    
    public GardesRepository(Application application) {
        GardesDatabase gardesDatabase = GardesDatabase.getInstance(application);
        UserDatabase userDatabase = UserDatabase.get(application);
        userDaoModule = userDatabase.daoModule();
        User userdatas = userDaoModule.getById(1);
        daoModule = gardesDatabase.gardesDaoModule();
        mGardes = daoModule.getAll();
        
        
        if (mGardes == null) {
            readStream(userdatas.grade, userdatas.nom, userdatas.prenom, application);
        }
        if (userdatas != null) {
            readStream(userdatas.grade, userdatas.nom, userdatas.prenom, application);
        }
        //if(mGardes!=null && userdatas!= null){}
    }
    
    
    private void readStream(String grade, String nom, String prenom, Application application) {
        String url = "https://cpi.agence-creation-sc.com/app/send_data.php?user=" + grade + "-" + nom + "-" + ucfirst(prenom.replace("é", "e"));
        Log.d("URL", url);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, jsonRequestListener, errorListener);
        getRequestQueue(application).add(request);
        
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
                StoreGardes(myGardes,longueur);
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
        public String chef;
        public String pltt;
        public String equi1;
        public String equi2;
        public String memo;
        public int jysuis;
        public String datesql;
        
    }
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue(Application application) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(application);
        return requestQueue;
    }
    
    public void StoreGardes(MyGardes myGardes, int longueur) {
        Gardes insertdatas = new Gardes(myGardes.datesql, myGardes.chef, myGardes.pltt, myGardes.equi1, myGardes.equi2, myGardes.memo, myGardes.jysuis, myGardes.datesql);
        
        int total = daoModule.count();
        Log.i("TOTAL", Integer.toString(total));
        Log.i("LONGUEUR", Integer.toString(longueur));
        
        // daoModule.deletegarde(insertdatas);
        if (total < longueur) {
           /* try{
            daoModule.deletegarde(daoModule.gettout());}
            catch (Exception e) {e.printStackTrace();}*/
            daoModule.insertGardes(insertdatas);
        }
        
        
        System.out.println(myGardes.datesql);
    }
    
    /**
     * Met le premier caractere d'une chaine en majuscule, le reste en minscule
     *
     * @param chaine
     * @return Chaine
     * @author Stef
     */
    public static String ucfirst(String chaine) {
        return chaine.substring(0, 1).toUpperCase() + chaine.substring(1).toLowerCase();
    }
    
   
}
