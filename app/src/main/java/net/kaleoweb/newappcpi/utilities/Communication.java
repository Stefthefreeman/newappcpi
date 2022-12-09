package net.kaleoweb.newappcpi.utilities;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class Communication {
    
    private static final String CONST_URL= "https://cpi.agence-creation-sc.com/app/";
   // Context context = null;
    
    public Communication() {
    }
    
    public void CommunicationwithServer(String URL, Context context) {
        
        System.out.println(CONST_URL+URL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, CONST_URL+URL, null, jsonRequestListener, errorListener);
        getRequestQueue(context).add(request);
        
    }
    
    private final Response.Listener<JSONObject> jsonRequestListener = response -> {
        
        Log.i("response", response.toString());
    };
    
    private final Response.ErrorListener errorListener = error -> {
        //  Toast.makeText(getActivity(),"On a une erreur", Toast.LENGTH_LONG).show();
        Log.d("REQUEST", error.toString());
    };
    
    RequestQueue requestQueue;
    
    RequestQueue getRequestQueue(Context c) {
        if (requestQueue == null)
            requestQueue = Volley.newRequestQueue(c);
        return requestQueue;
    }
}
