package net.kaleoweb.newappcpi.ui.gallery;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.ui.ambulance.Ambulance;
import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.adapters.LocalListAdapter;
import net.kaleoweb.newappcpi.databinding.FragmentGalleryBinding;
import net.kaleoweb.newappcpi.utilities.Local;

public class GalleryFragment extends Fragment {

    private GalleryViewModel galleryViewModel;
    private FragmentGalleryBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        galleryViewModel =
                new ViewModelProvider(this).get(GalleryViewModel.class);

        binding = FragmentGalleryBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        RecyclerView recyclerView = root.findViewById(R.id.recyclerviewgall);
        final LocalListAdapter adapter = new LocalListAdapter(getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        galleryViewModel.getListLocal().observe(getViewLifecycleOwner(), adapter::setLocal);
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new LocalListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Local local) {
                Log.i("chef",local.getThename());
                Intent intent = new Intent(getContext(), Ambulance.class);
                intent.putExtra("CODE",local.getLocid());
                intent.putExtra("NAME",local.getThename());
                startActivity(intent);
            }
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}