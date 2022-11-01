package net.kaleoweb.newappcpi.forms;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import net.kaleoweb.newappcpi.MainActivity;
import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.databases.SuapDatabase;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import java.util.Objects;


public class StoreConstantes extends AppCompatActivity {
    
    SuapDatabase suapDatabase;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        setContentView(R.layout.fragment_store_constantes);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        setTitle("NOUVEAU BILAN");
        
        EditText identite = findViewById(R.id.nomvictime);
        EditText diastole = findViewById(R.id.diastole);
        EditText systole = findViewById(R.id.systole);
        EditText pouls = findViewById(R.id.poulsradial);
        EditText glycemie = findViewById(R.id.laglycemie);
        EditText temperature = findViewById(R.id.temperature);
        EditText freqresp = findViewById(R.id.respi);
        Button go = findViewById(R.id.gotostore);
        
        go.setOnClickListener(v -> {
            dodb();
            String name = identite.getText().toString();
            String d = diastole.getText().toString();
            String s = systole.getText().toString();
            String p = pouls.getText().toString();
            String g = glycemie.getText().toString();
            String t = temperature.getText().toString();
            String f = freqresp.getText().toString();
            
            insertnewsuappdatas(name, d, s, p, g, t, f);
            
        });
    }
    
    private void dodb() {
        suapDatabase = SuapDatabase.get(this);
    }
    
    private void insertnewsuappdatas(String identite, String diastole, String systole, String pouls, String glycemie, String temp, String freq) {
        
        Suapdatas suapdatas = new Suapdatas();
        
        suapdatas.nom = identite;
        suapdatas.diastole = diastole;
        suapdatas.systole = systole;
        suapdatas.pouls = pouls;
        suapdatas.glycemie = glycemie;
        suapdatas.temperature = temp;
        suapdatas.freqrespi = freq;
        if (identite.isEmpty() || diastole.isEmpty() || systole.isEmpty() || pouls.isEmpty() || glycemie.isEmpty() || temp.isEmpty() || freq.isEmpty()) {
            Toast.makeText(this, "Tous les champs doivent être remplis !", Toast.LENGTH_SHORT).show();
        } else {
            suapDatabase.suapDao().insertSuap(suapdatas);
            Handler handler = new Handler();
            handler.postDelayed(() -> {
                Intent intent = new Intent(StoreConstantes.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("NewBilan", 1);
                finish();
            }, 2000);
        }
        Log.i("Suap", suapdatas.toString());
        
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
}
