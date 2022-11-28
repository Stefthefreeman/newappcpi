package net.kaleoweb.newappcpi;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import net.kaleoweb.newappcpi.Services.SetPharma;
import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.databases.DisposDatabase;
import net.kaleoweb.newappcpi.databases.PharmacieDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.Inventory;
import net.kaleoweb.newappcpi.utilities.User;

public class Splash extends AppCompatActivity {
    
    /**
     * Duration of wait
     **/
   
    private final int SPLASH_DISPLAY_LENGTH = 10000;
    private ProgressBar progressBar;
    private int progressStatus = 0;
    private TextView textView;
    private PharmacieDaoModule pharmacieDaoModule;
    private DisposDao disposDao;
    private TextView notallowed;
    private ImageView warning;
    private Handler handler = new Handler();
    
    /**
     * Called when the activity is first created.
     */
    @SuppressLint("SourceLockedOrientationActivity")
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.splash);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        notallowed = findViewById(R.id.not_allowed);
        progressBar = findViewById(R.id.progressar1);
        textView = findViewById(R.id.textView2);
        warning = findViewById(R.id.warning);
        
        Intent i = getIntent();
        System.out.println("Intent" +i.getStringExtra("notallowed") );
        if (i.getStringExtra("notallowed") != null) {
            notallowed.setVisibility(View.VISIBLE);
            warning.setVisibility(View.VISIBLE);
            notallowed.setText(R.string.notallowed);
            textView.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
    
        } else {
            
            PharmacieDatabase pharmacieDatabase = PharmacieDatabase.get(this);
            pharmacieDaoModule = pharmacieDatabase.pharmacieDaoModule();
            DisposDatabase disposDatabase = DisposDatabase.get(this);
            disposDao = disposDatabase.disposDao();
            try {
        
                if (pharmacieDaoModule.gettout() == null) {
                    this.startService(new Intent(this, SetPharma.class));
                }
               
            } catch (Exception e) {
                e.printStackTrace();
            }
            DaoModule daoModule;
            UserDatabase userDatabase = UserDatabase.get(this);
            daoModule = userDatabase.daoModule();
            User userdatas = daoModule.getById(1);
            if (userdatas == null) {
                Inventory inventory = new Inventory();
                inventory.storelocal(this);
                inventory.storemateriel(this);
            }
            /* New Handler to start the Menu-Activity
             * and close this Splash-Screen after some seconds.*/
    
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
            
                    /* Create an Intent that will start the Menu-Activity. */
            
                    Intent mainIntent = new Intent(Splash.this, MainActivity.class);
                    Splash.this.startActivity(mainIntent);
                    Splash.this.finish();
                }
            }, SPLASH_DISPLAY_LENGTH);
    
            new Thread(new Runnable() {
                public void run() {
                    while (progressStatus < 100) {
                        progressStatus += 1;
                        // Update the progress bar and display the
                        //current value in the text view
                        handler.post(new Runnable() {
                            public void run() {
                        
                                progressBar.setProgress(progressStatus);
                                textView.setText("Chargement " + progressStatus + "%");
                            }
                        });
                        try {
                            // Sleep for 200 milliseconds.
                            //Just to display the progress slowly
                            Thread.sleep(100);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
    }
    
}
