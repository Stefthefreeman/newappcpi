package net.kaleoweb.newappcpi.ui.disponibilites;

import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Button;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.GetDispos;
import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.Services.GetDisposService;
import net.kaleoweb.newappcpi.Services.SetPharma;
import net.kaleoweb.newappcpi.adapters.DispoListAdapter;
import net.kaleoweb.newappcpi.dao.DaoModule;
import net.kaleoweb.newappcpi.dao.DisposDao;
import net.kaleoweb.newappcpi.databases.DisposDatabase;
import net.kaleoweb.newappcpi.databases.UserDatabase;
import net.kaleoweb.newappcpi.utilities.Communication;
import net.kaleoweb.newappcpi.utilities.DiffUtilDispos;
import net.kaleoweb.newappcpi.utilities.Dispos;
import net.kaleoweb.newappcpi.utilities.User;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;


public class DisponibilitesFragment extends Fragment {
    
    private DisponibilitesViewModel mViewModel;
    
    private DispoListAdapter dispoListAdapter;
    private DisposDao disposDao;
    Communication communication = new Communication();
    CardView cardView;
    List<Dispos> mylist;
    
    public static DisponibilitesFragment newInstance() {
        return new DisponibilitesFragment();
    }
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_dispnibilites, container, false);
        
        mViewModel = new ViewModelProvider(this).get(DisponibilitesViewModel.class);
        
        RecyclerView mrecycler = root.findViewById(R.id.disporecyclerview);
        Button adddispo = root.findViewById(R.id.btnaddispo);
        cardView = root.findViewById(R.id.card);
        
        UserDatabase userDatabase = UserDatabase.get(getActivity());
        
        DaoModule daoModule = userDatabase.daoModule();
        
        User user = daoModule.getById(1);
        DisposDatabase disposDatabase = DisposDatabase.get(getActivity());
        disposDao = disposDatabase.disposDao();
        
        List<Dispos> mylist = disposDao.getAllDisposList();
        
       if (mylist.size() == 0) {
          //  requireActivity().startService(new Intent(getActivity(), GetDisposService.class));
            cardView.setVisibility(View.VISIBLE);
        }
        Intent modif = new Intent(getActivity(), GetDispos.class);
        dispoListAdapter = new DispoListAdapter(getContext());
        
        mViewModel.getdispos().observe(getViewLifecycleOwner(), DispoListAdapter::setDispos);
        mrecycler.setAdapter(dispoListAdapter);
        dispoListAdapter.setOnItemClickListener(dispos -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
            
            builder.setIcon(R.drawable.calendrier)
                    .setTitle("Modifier/Supprimer")
                    .setMessage("Disponibilité du :\n\n" + dispos.getDt());
            
            builder.setPositiveButton("Modifier", (dialogInterface, i) -> {
                
                modif.putExtra("date", dispos.getDt());
                modif.putExtra("manip", 2);
                modif.putExtra("id", dispos.getId());
                startActivity(modif);
                dispoListAdapter.notifyDataSetChanged();
                
            });
            builder.setNeutralButton("Supprimer", (dialogInterface, i) -> {
                
                disposDao.deleteDispos(dispos);
                mViewModel.getdispos().getValue().remove(dispos);
                if(mViewModel.getdispos().getValue().size() == 0){
                    cardView.setVisibility(View.VISIBLE);
                }
                communication.CommunicationwithServer("setdispos.php?user="+user.nom+"&dispo=" + dispos.getDtsql() + "&action=" + String.valueOf(3), getContext());
                
                dispoListAdapter.notifyDataSetChanged();
            });
            builder.setNegativeButton("Annuler", (dialogInterface, i) -> {
            
            });
            
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
            
            
        });
        
        
        adddispo.setOnClickListener(view -> {
            Intent i = new Intent(getActivity(), GetDispos.class);
            startActivity(i);
        });
        return root;
    }
    
    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onStart() {
        super.onStart();
        dispoListAdapter.notifyDataSetChanged();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        List<Dispos> mDispos = disposDao.getAllDisposList();
        if (mDispos.size() != 0) {
            dispoListAdapter.onDispoUp(mDispos);
            cardView.setVisibility(View.GONE);
        }
        
    }
    
    @Override
    public void onDestroy() {
        super.onDestroy();
        
    }
    
}