package net.kaleoweb.newappcpi;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.Services.SetGardes;
import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.repositories.GardesRepository;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

public class GetDispos extends AppCompatActivity {
    private DaoModule userDaoModule;
    public String sendate = null;
    ProgressDialog progressDialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.getdispos);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Button send = findViewById(R.id.send);
        UserDatabase userDatabase = UserDatabase.get(this);
        userDaoModule = userDatabase.daoModule();
        User userdatas = userDaoModule.getById(1);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        CalendarView simpleCalendarView = findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), dayOfMonth+ "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
                sendate = year + "-" + (month + 1) + "-" + dayOfMonth;
            }
            
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                senDatas(userdatas.nom, sendate);
                progressDialog = new ProgressDialog(GetDispos.this,
                        R.style.NewDialog);
                progressDialog.setMessage("Envoi en cours...");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Modifications enregistrées", Toast.LENGTH_LONG).show();
                            }
                        }, 3000);
            }
        });
        
        
    }
    
    private void senDatas(String nom, String dtdispo) {
        String URL = "https://cpi.agence-creation-sc.com/app/setdispos.php?user=" + nom + "&dispo=" + dtdispo;
        Log.i("URL", URL);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URL, null, jsonRequestListener, errorListener);
        getRequestQueue().add(request);
        
    }
    
    private final Response.Listener<JSONObject> jsonRequestListener = response -> {
        
        Log.i("response", response.toString());
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
    
    ;
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
