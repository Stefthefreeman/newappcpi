package ui.pharma;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
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
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import net.kaleoweb.newappcpi.MainActivity;
import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.Services.SetPharma;
import net.kaleoweb.newappcpi.Splash;
import net.kaleoweb.newappcpi.adapters.PharmaListAdapter;
import net.kaleoweb.newappcpi.databinding.FragmentPharmaBinding;
import net.kaleoweb.newappcpi.utilities.Pharma;

import java.util.Objects;


public class PharmaFragment extends Fragment {
    
    private PharmaViewModel mViewModel;
    private FragmentPharmaBinding binding;
    Intent myintent;
    private String dateup = null;
    private PharmaListAdapter pharmaListAdapter;
    private int quantite;
    private String dateperemp;
    private RecyclerView recyclerView;
    private View root;
    
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        requireActivity().startService(new Intent(getActivity(), SetPharma.class));
        mViewModel =
                new ViewModelProvider(requireActivity()).get(PharmaViewModel.class);
        
        binding = FragmentPharmaBinding.inflate(inflater, container, false);
        
        root = inflater.inflate(R.layout.fragment_pharma, container, false);
        
        recyclerView = root.findViewById(R.id.pharmacie);
        
        pharmaListAdapter = new PharmaListAdapter(getContext());
      
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        loadinfos();
       
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
            builder.setPositiveButton("Envoyer", (dialogInterface, i) -> {
                try {
                    
                    if (!myqte.getText().toString().isEmpty() && dateup != null) {
                        dateperemp = dateup;
                        quantite = Integer.parseInt(myqte.getText().toString());
                    }
                    if (dateup == null && myqte.getText().toString().isEmpty()) {
                        dateperemp = pharma.getPeremption();
                        quantite = pharma.getRestant();
                    }
                    if (dateup != null && myqte.getText().toString().isEmpty()) {
                        dateperemp = dateup;
                        quantite = pharma.getRestant();
                    }
                    if (dateup == null && !myqte.getText().toString().isEmpty()) {
                        dateperemp = pharma.getPeremption();
                        quantite = Integer.parseInt(myqte.getText().toString());
                    }
                    
                    sendUpdate(pharma.getId(), quantite, dateperemp);
                    
                    pharmaListAdapter.notifyItemChanged(pharma.getId());
                    
                    Toast.makeText(getContext(), pharma.getId() + " - " + quantite + " - " + dateperemp, Toast.LENGTH_LONG).show();
                    
                } catch (Exception e) {
                    e.printStackTrace();
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
        System.out.println("destroyed");
        recyclerView.removeAllViews();
    }
    
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Onresume");
        recyclerView.removeAllViews();
        
        
    }
    public void loadinfos(){
    
    }
    private void sendUpdate(int id, int qte, String peremp) {
        String URL = "https://cpi.agence-creation-sc.com/app/update_pharma.php?id=" + id + "&qte=" + qte + "&peremp=" + peremp;
        Log.i("URL", URL);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        
        
        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    
                    
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