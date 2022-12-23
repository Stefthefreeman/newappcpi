package net.kaleoweb.newappcpi.ui.slideshow;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.adapters.SuapAdapter;
import net.kaleoweb.newappcpi.dao.SuapDao;
import net.kaleoweb.newappcpi.databases.SuapDatabase;
import net.kaleoweb.newappcpi.databinding.FragmentSlideshowBinding;
import net.kaleoweb.newappcpi.forms.StoreConstantes;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import java.util.List;

public class SlideshowFragment extends Fragment {
    
    private SlideshowViewModel slideshowViewModel;
    private FragmentSlideshowBinding binding;
    SuapDao suapDao;
    SuapAdapter adapter;
    
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                new ViewModelProvider(this).get(SlideshowViewModel.class);
        
        binding = FragmentSlideshowBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        SuapDatabase suapDatabase = SuapDatabase.get(getActivity());
        suapDao = suapDatabase.suapDao();
        Button addsuap = root.findViewById(R.id.addsuap);
        addsuap.setOnClickListener(v -> {
            Intent form = new Intent(getActivity(), StoreConstantes.class);
            startActivity(form);
        });
        RecyclerView recyclerView = root.findViewById(R.id.recyclerviewsuap);
        adapter = new SuapAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        slideshowViewModel.getBilans().observe(getViewLifecycleOwner(), adapter::setInters);
        
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(suapdatas -> {
            int suap = suapdatas.getId();
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Supprimer " + suapdatas.getNom());
            builder.setMessage("TA: " + suapdatas.getDiastole() + "/" + suapdatas.getSystole() + "\n" +
                    "POULS : " + suapdatas.getPouls() + "\n");
            builder.setPositiveButton("SUPPRIMER", (dialog, which) -> {
                slideshowViewModel.getBilans().getValue().remove(suapdatas);
                suapDao.d(suap);
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Bilan supprimé!", Toast.LENGTH_LONG).show();
                
            });
            builder.setNegativeButton("ANNULER", (dialog, which) -> dialog.cancel());
            AlertDialog dialog = builder.create();
            dialog.show();
            
        });
        
        return root;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
    @Override
    public void onResume() {
        super.onResume();
        List<Suapdatas> mlist = suapDao.getAllSuapList();
        adapter.onSuapsUp(mlist);
        adapter.notifyDataSetChanged();
        
    }
}