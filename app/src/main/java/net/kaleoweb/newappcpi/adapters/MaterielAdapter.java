package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.Materiel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MaterielAdapter extends RecyclerView.Adapter<MaterielAdapter.MaterielViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Materiel> mMateriels;
    private static OnItemClickListener listener;
    
    public MaterielAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public MaterielViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recylerviewitemambu, parent, false);
        return new MaterielViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull MaterielViewHolder holder, int position) {
        if (mMateriels != null) {
            Materiel current = mMateriels.get(position);
            holder.name.setText(current.getNom());
            holder.number.setText(Integer.toString(current.getQte()));
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("No Word");
        }
        
    }
    
    public void setMateriel(List<Materiel> materiel) {
        mMateriels = materiel;
        notifyDataSetChanged();
    }
    
    @Override
    public int getItemCount() {
        if (mMateriels != null)
            return mMateriels.size();
        else return 0;
    }
    
    static class MaterielViewHolder extends RecyclerView.ViewHolder {
        private final TextView name;
        private final TextView number;
        
        private MaterielViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.desig);
            number = itemView.findViewById(R.id.num);
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mMateriels.get(position));
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Materiel materiel);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        MaterielAdapter.listener = listener;
    }
    
}
