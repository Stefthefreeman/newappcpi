package ui.pharma;


import android.content.DialogInterface;
import android.os.Build;
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
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.adapters.PharmaListAdapter;
import net.kaleoweb.newappcpi.dao.PharmacieDaoModule;
import net.kaleoweb.newappcpi.databases.PharmacieDatabase;
import net.kaleoweb.newappcpi.databinding.FragmentPharmaBinding;
import net.kaleoweb.newappcpi.utilities.Pharma;

import java.util.List;


public class PharmaFragment extends Fragment {
    
    private PharmaViewModel mViewModel;
    public FragmentPharmaBinding binding;
    private String dateup = null;
    private PharmaListAdapter pharmaListAdapter;
    private int quantite;
    private String dateperemp;
    private RecyclerView recyclerView;
    private PharmacieDaoModule pharmacieDaoModule;
    private LifecycleRegistry lifecycleRegistry;
    
    private int bg;
    
    
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        
        View root = inflater.inflate(R.layout.fragment_pharma, container, false);
        recyclerView = root.findViewById(R.id.pharmacie);
        binding = FragmentPharmaBinding.inflate(getLayoutInflater());
        mViewModel = new ViewModelProvider(requireActivity()).get(PharmaViewModel.class);
        
        pharmaListAdapter = new PharmaListAdapter(getContext());
        mViewModel.getPostsList().observe(getViewLifecycleOwner(), pharmaListAdapter::setPharma);
        recyclerView.setAdapter(pharmaListAdapter);
        
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                // this method is called
                // when the item is moved.
                return false;
            }
            
            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // this method is called when we swipe our item to right direction.
                
                
                // below line is to get the position
                // of the item at that position.
                int position = viewHolder.getAdapterPosition();
                
                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                Pharma deletedCourse = mViewModel.getPostsList().getValue().remove(position);
                
                // below line is to notify our item is removed from adapter.
                pharmaListAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
                try {
                    // below line is to display our snackbar with action.
                    Snackbar.make(recyclerView, deletedCourse.getDescription(), Snackbar.LENGTH_LONG).setAction("ANNULER", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            // adding on click listener to our action of snack bar.
                            // below line is to add our item to array list with a position.
                            mViewModel.getPostsList().getValue().add(position, deletedCourse);
                            
                            // below line is to notify item is
                            // added to our adapter class.
                            pharmaListAdapter.notifyItemInserted(position);
                        }
                    }).show();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(recyclerView);
        
        
       
        
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
                    PharmacieDatabase pharmacieDatabase = PharmacieDatabase.get(getActivity());
                    pharmacieDaoModule = pharmacieDatabase.pharmacieDaoModule();
                    Pharma pharma2 = pharmacieDaoModule.gettout();
                    
                    if (quantite <= pharma2.getDotation() / 2) {
                        bg = 0;
                    } else {
                        bg = 1;
                    }
                    pharmacieDaoModule.up(quantite, dateperemp, bg, pharma.getId());
                    System.out.println();
                    sendUpdate(pharma.getWebid(), quantite, dateperemp);
                    List<Pharma> pharma3 = pharmacieDaoModule.getup();
                    pharmaListAdapter.onGo(pharma3);
                    pharmaListAdapter.notifyDataSetChanged();
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
        
    }
   
    
    @Override
    public void onResume() {
        super.onResume();
        System.out.println("Onresume");
        pharmaListAdapter.notifyDataSetChanged();
    }
    
    private void runOnupdate(){
    
        pharmaListAdapter.notifyDataSetChanged();
      
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