package net.kaleoweb.newappcpi;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.databases.DisposDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.CleanDate;
import net.kaleoweb.newappcpi.utilities.Communication;
import net.kaleoweb.newappcpi.utilities.Dispos;
import net.kaleoweb.newappcpi.utilities.User;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class GetDispos extends AppCompatActivity {
    private DaoModule userDaoModule;
    public String sendate = null;
    ProgressDialog progressDialog;
    private DisposDao disposDao;
    CleanDate cleanDate;
    private String tt;
    private String dte;
    private String dlt;
    private int appid;
    int test;
    List<Dispos> mList;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.getdispos);
        Intent i = getIntent();
        dte = i.getStringExtra("date");
        dlt = i.getStringExtra("delete");
        test = i.getIntExtra("manip", 0);
        appid = i.getIntExtra("id", 0);
        System.out.println("TEST" + test);
        //   System.out.println(dlt);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        Button send = findViewById(R.id.send);
        UserDatabase userDatabase = UserDatabase.get(this);
        DisposDatabase disposDatabase = DisposDatabase.get(this);
        disposDao = disposDatabase.disposDao();
        userDaoModule = userDatabase.daoModule();
        User userdatas = userDaoModule.getById(1);
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.FRANCE);
        CalendarView simpleCalendarView = findViewById(R.id.simpleCalendarView); // get the reference of CalendarView
        simpleCalendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView view, int year, int month, int dayOfMonth) {
                //Toast.makeText(getApplicationContext(), dayOfMonth+ "/" + (month+1) + "/" + year, Toast.LENGTH_LONG).show();
               
                sendate = year + "-" + (month + 1) + "-" + dayOfMonth;
                cleanDate = new CleanDate();
                tt = cleanDate.returndate(sendate);
                System.out.println("CLEANDATE : " +tt);
            }
            
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
                Dispos dispos = new Dispos(tt,sendate);
                
                switch (test) {
                    case 0:
                    case 1:
                            disposDao.insertDispos(dispos);
                            senDatas(userdatas.nom, sendate, test, "", appid,getApplicationContext());
                        break;
                    
                    case 2:
                        String mydd = disposDao.getDateSql(appid);
                        System.out.println("ID " + appid);
                        senDatas(userdatas.nom, mydd, test, sendate, appid,getApplicationContext());
                        disposDao.upDispo(appid, tt,sendate);
                        
                        break;
                    case 3:
                       /* senDatas(userdatas.nom, dte, test, "", appid);
                        disposDao.deleteDispos(dispos);*/
                }
                
                
                progressDialog = new ProgressDialog(GetDispos.this,
                        R.style.NewDialog);
                progressDialog.setMessage("Envoi en cours...");
                progressDialog.show();
                new android.os.Handler().postDelayed(
                        new Runnable() {
                            public void run() {
                                
                                progressDialog.dismiss();
                                Toast.makeText(getApplicationContext(), "Modifications enregistrées", Toast.LENGTH_LONG).show();
                                onBackPressed();
                            }
                        }, 3000);
            }
        });
        
        
    }
    
    public void senDatas(String nom, String dtdispo, int manip, String newdispo, int appid, Context context) {
        Communication communication =new Communication();
        String URL = null;
        switch (manip) {
            case 0:
            case 1:
                 communication.CommunicationwithServer("setdispos.php?user=" + nom + "&dispo=" + dtdispo + "&action=" + manip + "&app_id=" + appid+"",context);;
                break;
            case 2:
                communication.CommunicationwithServer("setdispos.php?user=" + nom + "&dispo=" + dtdispo + "&action=" + manip + "&new=" + newdispo + "&app_id=" + appid+"",context);
                break;
            case 3:
                communication.CommunicationwithServer("setdispos.php?dispo=" + dtdispo + "&action=" + manip+"",context);
                break;
            
        }
        
       
        
    }
    
   
    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    private Boolean checkIfExist(@NonNull List<Dispos> myList, @NonNull String hdate) {
        
        int i = 0;
        
        while(i < myList.size()) {
            System.out.println(myList.get(i).getDtsql() + hdate);
            if (!myList.get(i).getDtsql().equals(hdate))
            
            return true;
            i++;
        }
        return false;
    }
}
