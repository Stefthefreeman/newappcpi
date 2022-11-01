package ui.pharma;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.utilities.Pharma;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PharmaViewModel extends AndroidViewModel {
    public JsonLiveData postsList;
    private final List<Pharma> mPosts = new ArrayList<>();
    private LiveData<List<Pharma>> mList;
    
    
    public MutableLiveData getRefresh() {
        return refresh;
    }
    
    private MutableLiveData<Integer> refresh = new MutableLiveData<>();
    
    public PharmaViewModel(Application application) {
        super(application);
        if (postsList == null)
            postsList = new JsonLiveData(application);
        
        
    }
    
    public LiveData<List<Pharma>> getPostsList() {
        return postsList;
    }
    
    
    public class JsonLiveData extends MutableLiveData<List<Pharma>> {
        
        private int page = 1;
        
        public JsonLiveData(Context context) {
            readStream();
        }
    }
    
    public void RefreshData() {
        refresh.setValue(0);
        postsList = new JsonLiveData(this.getApplication());
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
            JSONArray jsonArray = response.getJSONArray("pharma");
            int longueur = jsonArray.length();
            for (int i = 0; i < longueur; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                
                String desc = jsonObject.getString("designation");
                int num = jsonObject.getInt("id");
                int Cat_id = jsonObject.getInt("cat_id");
                String Peremption = jsonObject.getString("peremption");
                int Dotation = jsonObject.getInt("dotation");
                int Restant = jsonObject.getInt("restant");
                int bg = jsonObject.getInt("bg");
                Pharma pharma = new Pharma(num, Cat_id, desc, Dotation, Restant, Peremption, bg);
                mPosts.add(pharma);
                postsList.setValue(mPosts);
                
                // Log.i("PHARMACIE",mPosts.toString());
            }
            
            
        } catch (JSONException e) {
            Log.e("JSON", e.getLocalizedMessage());
        }
    };
    
    private final Response.ErrorListener errorListener = error -> {
        //  Toast.makeText(getActivity(),"On a une erreur", Toast.LENGTH_LONG).show();
        Log.d("REQUEST", error.toString());
    };
    
    public class Medoc {
        
        public String desc;
        public int num;
        
    }
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue() {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(getApplication());
        return requestQueue;
    }
    
}

