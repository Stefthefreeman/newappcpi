package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.DiffUtilDispos;
import net.kaleoweb.newappcpi.utilities.Dispos;
import net.kaleoweb.newappcpi.utilities.MyDiffUtil;
import net.kaleoweb.newappcpi.utilities.Pharma;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DispoListAdapter extends RecyclerView.Adapter<DispoListAdapter.DispoViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Dispos> mDispo;
    private static DispoListAdapter.OnItemClickListener listener;
    boolean hasStableIds;
    
    public DispoListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public DispoListAdapter.DispoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycler_item_dispo, parent, false);
        return new DispoListAdapter.DispoViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull DispoListAdapter.DispoViewHolder holder, int position) {
        hasStableIds = true;
        if (mDispo != null) {
            Dispos current = mDispo.get(position);
            holder.mdispos.setText(current.getDt());
            
        } else {
            // Covers the case of data not being ready yet.
           
        }
        
    }
    
    @Override
    public int getItemCount() {
        if (mDispo != null)
            return mDispo.size();
        else return 0;
    }
    
    public static void setDispos(List<Dispos> g) {
        mDispo = g;
    }
    
    
    static class DispoViewHolder extends RecyclerView.ViewHolder {
        
        private final TextView mdispos;
        
        
        private DispoViewHolder(View itemView) {
            super(itemView);
            
            mdispos = itemView.findViewById(R.id.datedispo);
            
            
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mDispo.get(position));
                        
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Dispos dispos);
    }
    
    public void setOnItemClickListener(DispoListAdapter.OnItemClickListener listener) {
        DispoListAdapter.listener = listener;
    }
    
    
    public void deleteItem(int position) {
        
        mDispo.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mDispo.size());
    }
    
    @Override
    public long getItemId(int position) {
        return mDispo.get(position).getId();
    }
    
    public void onDispoUp(List<Dispos> newDispo){
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtilDispos(mDispo,newDispo));
        diffResult.dispatchUpdatesTo(this);
        mDispo.clear();
        mDispo.addAll(newDispo);
        
        
    }
}
    

        
