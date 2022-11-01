package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.Local;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class LocalListAdapter extends RecyclerView.Adapter<LocalListAdapter.LocalViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Local> mLocal;
    private static OnItemClickListener listener;
    
    public LocalListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public LocalViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.basicrecyclerviewitem, parent, false);
        return new LocalViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull LocalViewHolder holder, int position) {
        if (mLocal != null) {
            Local current = mLocal.get(position);
            holder.name.setText(current.getThename());
            
            
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No Word");
        }
        
    }
    
    public void setLocal(List<Local> local) {
        mLocal = local;
        notifyDataSetChanged();
    }
    
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mLocal != null)
            return mLocal.size();
        else return 0;
    }
    
    static class LocalViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        
        private LocalViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.desig);
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mLocal.get(position));
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Local local);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        LocalListAdapter.listener = listener;
    }
    
}
        
    
    

