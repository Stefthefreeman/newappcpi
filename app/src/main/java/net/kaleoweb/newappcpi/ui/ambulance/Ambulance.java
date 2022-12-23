package net.kaleoweb.newappcpi.ui.ambulance;

import android.database.DataSetObserver;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.snackbar.Snackbar;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.adapters.MaterielAdapter;
import net.kaleoweb.newappcpi.dao.MaterielDaoModule;
import net.kaleoweb.newappcpi.databases.MaterielDatabase;
import net.kaleoweb.newappcpi.databinding.AmbulanceBinding;
import net.kaleoweb.newappcpi.utilities.Materiel;

import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

public class Ambulance extends AppCompatActivity {
    private static MaterielDaoModule materielDaoModule;
    private static MaterielDatabase materielDatabase;
    private AppBarConfiguration mAppBarConfiguration;
    private AmbulanceBinding binding;
    private AmblanceViewModel amblanceViewModel;
    private AmblanceViewModel amblanceViewModel1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        binding = AmbulanceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Bundle extras = getIntent().getExtras();
        int j = (int) extras.get("CODE");
        String cat = extras.get("NAME").toString();
        Log.i("Intent", String.valueOf(j));
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(cat);
        
        amblanceViewModel = new ViewModelProvider(this, new MyViewModelFactory(this.getApplication(), j)).get(AmblanceViewModel.class);
        
        MaterielDatabase materielDatabase = MaterielDatabase.get(this);
        materielDaoModule = materielDatabase.materielDaoModule();
        RecyclerView recyclerView = findViewById(R.id.recyclerviewambu);
        final MaterielAdapter adapter = new MaterielAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        amblanceViewModel.getmMatos().observe(this, adapter::setMateriel);
        recyclerView.setAdapter(adapter);
        
        
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
