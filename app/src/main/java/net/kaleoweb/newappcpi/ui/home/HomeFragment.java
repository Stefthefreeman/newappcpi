package net.kaleoweb.newappcpi.ui.home;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.Services.SetGardes;
import net.kaleoweb.newappcpi.adapters.GardesListAdapter;
import net.kaleoweb.newappcpi.dao.GardesDaoModule;
import net.kaleoweb.newappcpi.databases.GardesDatabase;
import net.kaleoweb.newappcpi.databinding.FragmentHomeBinding;
import net.kaleoweb.newappcpi.utilities.Gardes;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    private GardesListAdapter adapter;
    Intent myintent;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
       myintent = new Intent(getActivity(), SetGardes.class);
        requireActivity().startService(myintent);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        RecyclerView recyclerView = root.findViewById(R.id.recyclerview);
        adapter = new GardesListAdapter(getContext());
       
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                LinearLayoutManager.VERTICAL);
        recyclerView.addItemDecoration(dividerItemDecoration);
        homeViewModel.getList().observe(getViewLifecycleOwner(), adapter::setInters);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new GardesListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Gardes gardes) {
                Log.i("chef",gardes.getConducteur());
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("GARDE du " +gardes.getDate());
                builder.setIcon(R.drawable.ambulance);
                builder.setMessage("CHEF DE GARDE :" + gardes.getChef()+"\n"+"CONDUCTEUR PLTT:" +gardes.getConducteur()+"\n"+"EQUIPIER 1:" +gardes.getEquipier1()+"\n"+"EQUIPIER 2:" +gardes.getEquipier2());
                builder.setPositiveButton("FERMER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                    }
                });
                builder.setNegativeButton("SUPPRIMER", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        GardesDatabase gardesDatabase = GardesDatabase.getInstance(getContext());
                        GardesDaoModule gardesDaoModule = gardesDatabase.gardesDaoModule();
                        gardesDaoModule.i(gardes.getId());
                        homeViewModel.getList().getValue().remove(gardes);
                        adapter.notifyDataSetChanged();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    
        return root;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        Log.i("onresume","Datasetchanged");
        adapter.notifyDataSetChanged();
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
        requireActivity().stopService(myintent);
    }
}