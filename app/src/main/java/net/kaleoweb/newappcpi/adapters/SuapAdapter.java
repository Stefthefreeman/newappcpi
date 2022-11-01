package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.Gardes;
import net.kaleoweb.newappcpi.utilities.Suapdatas;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SuapAdapter extends RecyclerView.Adapter<SuapAdapter.InterViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Suapdatas> mSuap;
    private static OnItemClickListener listener;
    
    public SuapAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public InterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item_suap, parent, false);
        return new InterViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull InterViewHolder holder, int position) {
        if (mSuap != null) {
            Suapdatas current = mSuap.get(position);
            holder.name.setText("Nom: " + current.getNom());
            holder.tension.setText("TA: " + current.getDiastole() + "/" + current.getSystole() + " TEMP: " + current.getTemperature());
            holder.pouls.setText("Pouls: " + current.getPouls() + " FR: " + current.getFreqrespi());
            
            
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No Word");
        }
        
    }
    
    public void setInters(List<Suapdatas> inter) {
        mSuap = inter;
        
        notifyDataSetChanged();
    }
    
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mSuap != null)
            return mSuap.size();
        else return 0;
    }
    
    static class InterViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView tension;
        private final TextView pouls;
        private final ImageView imageView;
        
        private InterViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameof);
            tension = itemView.findViewById(R.id.tension);
            pouls = itemView.findViewById(R.id.pouls);
            imageView = itemView.findViewById(R.id.imageView3);
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mSuap.get(position));
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Suapdatas suapdatas);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        SuapAdapter.listener = listener;
    }
}
