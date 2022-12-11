package net.kaleoweb.newappcpi;

import static androidx.fragment.app.FragmentManager.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListUpdateCallback;

import net.kaleoweb.newappcpi.Services.SetPharma;
import net.kaleoweb.newappcpi.adapters.PharmaListAdapter;
import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.databases.PharmacieDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.databinding.ActivityMainBinding;
import net.kaleoweb.newappcpi.forms.StoreUser;
import net.kaleoweb.newappcpi.utilities.Inventory;
import net.kaleoweb.newappcpi.utilities.MyDiffUtil;
import net.kaleoweb.newappcpi.utilities.Pharma;
import net.kaleoweb.newappcpi.utilities.User;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    
    private AppBarConfiguration mAppBarConfiguration;
    private ActivityMainBinding binding;
    private FirebaseAuth mAuth;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mAuth = FirebaseAuth.getInstance();
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        this.stopService(new Intent(this,SetPharma.class));
     /*  SharedPreferences mgr = getSharedPreferences("Mes preferences",0);
       SharedPreferences.Editor edt = mgr.edit();
       edt.putString("nom","Stef");
       edt.commit();
       
       String nom = mgr.getString("nom","inconnu");*/
        //  Toast.makeText(this, nom, Toast.LENGTH_SHORT).show();
        
        setSupportActionBar(binding.appBarMain.toolbar);
       
        DrawerLayout drawer = binding.drawerLayout;
        NavigationView navigationView = binding.navView;
        View v = navigationView.getHeaderView(0);
        TextView username = v.findViewById(R.id.username);
        TextView affectation = v.findViewById(R.id.nomitem);
        DaoModule daoModule;
        UserDatabase userDatabase = UserDatabase.get(this);
        daoModule = userDatabase.daoModule();
    
        User userdatas = daoModule.getById(1);
        if (userdatas == null) {
            // Toast.makeText(this,"VOUS DEVEZ VOUS ENREGISTRER POUR UTILISER L'APPLICATION",Toast.LENGTH_LONG).show();
            Intent intent = new Intent(this, StoreUser.class);
            startActivity(intent);
            
        } else {
            username.setText(userdatas.grade + " " + userdatas.nom + " " + userdatas.prenom);
            affectation.setText(userdatas.centre);
        }
        
        userDatabase.close();
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow, R.id.pharmacie,R.id.nav_pharma,R.id.nav_dispos,R.id.nav_calls)
                .setDrawerLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);
    }
    
    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
/*        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            //reload();
        }else{
            Intent i = new Intent(this,FirebaseUIActivity.class);
            startActivity(i);
        }*/
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        
        int id = item.getItemId();
        switch (id) {
            
            case R.id.action_settings:
               /* Intent change = new Intent(this, StoreUser.class);
                this.startActivity(change);
                myAlertDialog();*/
                break;
            default:
                return super.onOptionsItemSelected(item);
            
        }
        return true;
    }
    
    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }
    
    public void myAlertDialog() {
        
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        
        alertDialog.show();
    }
    
}