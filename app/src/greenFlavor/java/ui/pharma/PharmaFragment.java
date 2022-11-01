package ui.pharma;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.adapters.PharmaListAdapter;
import net.kaleoweb.newappcpi.databinding.FragmentPharmaBinding;


public class PharmaFragment extends Fragment {
    
    private PharmaViewModel mViewModel;
    private FragmentPharmaBinding binding;
    Intent myintent;
    private String dateup = null;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel =
                new ViewModelProvider(requireActivity()).get(PharmaViewModel.class);
        
        binding = FragmentPharmaBinding.inflate(inflater, container, false);
        
        View root = inflater.inflate(R.layout.fragment_pharma, container, false);
        
        RecyclerView recyclerView = root.findViewById(R.id.pharmacie);
        
        final PharmaListAdapter pharmaListAdapter = new PharmaListAdapter(getContext());
        
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        
        mViewModel.getPostsList().observe(getViewLifecycleOwner(), pharmaListAdapter::setPharma);
        
        recyclerView.setAdapter(pharmaListAdapter);
        
        pharmaListAdapter.setOnItemClickListener(pharma -> {
            Log.i("desc", pharma.getDescription());
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            LayoutInflater layoutInflater = getActivity().getLayoutInflater();
            
            View dialogView = layoutInflater.inflate(R.layout.custom_alert, null);
            TextView produit = dialogView.findViewById(R.id.product);
            produit.setText(pharma.getDescription());
            EditText myqte = dialogView.findViewById(R.id.qte);
            CalendarView calendarView = dialogView.findViewById(R.id.calendarView);
            calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
                String curDate = String.valueOf(dayOfMonth);
                String Year = String.valueOf(year);
                String Month = String.valueOf(month + 1);
                dateup = Year + "-" + Month + "-" + curDate;
                Log.i("date", Year + "/" + Month + "/" + curDate);
            });
            builder.setView(dialogView);
            builder.setPositiveButton("Envoyer", new DialogInterface.OnClickListener() {
                @SuppressLint("NotifyDataSetChanged")
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    sendUpdate(pharma.getId(), myqte.getText().toString(), dateup);
                    Toast.makeText(getContext(), pharma.getId() + " - " + myqte.getText().toString() + " - " + dateup, Toast.LENGTH_LONG).show();
                    pharmaListAdapter.notifyDataSetChanged();
                }
            });
            builder.setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
        
        return root;
    }
    
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
    
    private void sendUpdate(int id, String qte, String peremp) {
        String URL = "https://cpi.agence-creation-sc.com/app/update_pharma.php?id=" + id + "&qte=" + qte + "&peremp=" + peremp;
        Log.i("URL", URL);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(getActivity(), response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // textView.setText("That didn't work!");
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
    
}