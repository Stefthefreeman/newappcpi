package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.Pharma;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class PharmaListAdapter extends RecyclerView.Adapter<PharmaListAdapter.PharmaViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Pharma> mPharma;
    private static PharmaListAdapter.OnItemClickListener listener;
    
    public PharmaListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public PharmaListAdapter.PharmaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerviewpharma, parent, false);
        return new PharmaViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull PharmaViewHolder holder, int position) {
        if (mPharma != null) {
            Pharma current = mPharma.get(position);
            if (current.getBg() == 1) {
                holder.interItemView.setBackgroundColor(Color.GREEN);
            }
            holder.interItemView.setText(current.getDescription());
            holder.interItemView2.setText("Date de peremption: " + current.getPeremption());
            holder.interItemView4.setText("Dotation: " + current.getDotation());
            holder.interItemView5.setText("Restant: " + current.getRestant());
            // holder.interItemView6.setText(getItemCount());
            
        } else {
            // Covers the case of data not being ready yet.
            holder.interItemView.setText("No Word");
        }
        
    }
    
    /*  public void setInters(List<PharmaViewModel.JsonLiveData.MyPharma> inter){
          mPharma = inter;
          
          
      }*/
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mPharma != null)
            return mPharma.size();
        else return 0;
    }
    
    public void setPharma(List<Pharma> g) {
        mPharma = g;
    }
    
    static class PharmaViewHolder extends RecyclerView.ViewHolder {
        private final TextView interItemView;
        private final TextView interItemView2;
        private final TextView interItemView4;
        private final TextView interItemView5;
        private final TextView interItemView6;
        
        
        private PharmaViewHolder(View itemView) {
            super(itemView);
            interItemView = itemView.findViewById(R.id.nomitem);
            interItemView2 = itemView.findViewById(R.id.peremption);
            interItemView4 = itemView.findViewById(R.id.dotation);
            interItemView5 = itemView.findViewById(R.id.restant);
            interItemView6 = itemView.findViewById(R.id.divers);
            
            
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mPharma.get(position));
                        
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Pharma pharma);
    }
    
    public void setOnItemClickListener(PharmaListAdapter.OnItemClickListener listener) {
        PharmaListAdapter.listener = listener;
    }
}
