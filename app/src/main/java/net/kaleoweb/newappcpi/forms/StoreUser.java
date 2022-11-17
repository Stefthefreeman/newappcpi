package net.kaleoweb.newappcpi.forms;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.MainActivity;
import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.Splash;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.User;

import java.util.Objects;


public class StoreUser extends AppCompatActivity {
    UserDatabase userDatabase;
    final static String TAG = "StoreUser";
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.soreuser);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        EditText nom = findViewById(R.id.nameagent);
        EditText prenom = findViewById(R.id.surnameagent);
        EditText centre = findViewById(R.id.centreaff);
        Spinner spinner = (Spinner) findViewById(R.id.levels_spinner);
// Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Button valider = findViewById(R.id.button);
        
        doDb();
        
        valider.setOnClickListener((view -> {
            String nameagent = nom.getText().toString().trim();
            String surname = prenom.getText().toString().trim();
            String moncentre = centre.getText().toString().trim();
            String mygrade = spinner.getSelectedItem().toString();
            if (nameagent.isEmpty() || surname.isEmpty() || moncentre.isEmpty() || mygrade.isEmpty()) {
                
                Toast.makeText(this, "Tout les champs doivent être remplis", Toast.LENGTH_LONG).show();
            } else {
                verifyUser(nameagent, surname, moncentre, mygrade);
                
            }
        }));
        
    }
    
    private void doDb() {
        userDatabase = UserDatabase.get(this);
    }
    
    private void testDb(String nom, String prenom, String centre, String grade) {
        
        Log.d(TAG, "Debut test DB");
        User user1 = new User();
        user1.nom = nom;
        user1.prenom = prenom;
        user1.centre = centre;
        user1.grade = grade;
        
        userDatabase.daoModule().insertUser(user1);
        
        
        Log.d(TAG, "Fin test DB");
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
    
    private void verifyUser(String nom, String prenom, String centre, String grade) {
        
        String URL = "https://cpi.agence-creation-sc.com/app/verify_user.php?nom=" + nom.toUpperCase().trim();
        Log.i("URL", URL);
        RequestQueue queue = Volley.newRequestQueue(this);
        
        
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        System.out.println("reponse" + response);
                        goahead(response, nom, prenom, centre, grade);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
        
    }
    
    public void goahead(String reponse, String nom, String prenom, String centre, String grade) {
        if (reponse.equals("OK")) {
            testDb(nom,prenom,centre,grade);
            Toast.makeText(this, "Enregistrement reussi!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, Splash.class);
            intent.putExtra("notallowed","true");
            startActivity(intent);
        }
        
    }
    
}
