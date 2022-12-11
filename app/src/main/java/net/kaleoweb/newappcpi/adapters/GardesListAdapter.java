package net.kaleoweb.newappcpi.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import net.kaleoweb.newappcpi.R;
import net.kaleoweb.newappcpi.utilities.Gardes;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Random;

public class GardesListAdapter extends RecyclerView.Adapter<GardesListAdapter.InterViewHolder> {
    
    private final LayoutInflater mInflater;
    private static List<Gardes> mGardes;
    private static OnItemClickListener listener;
    
    
    public GardesListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }
    
    @NotNull
    @Override
    public InterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, parent, false);
        return new InterViewHolder(itemView);
    }
    
    @Override
    public void onBindViewHolder(@NotNull InterViewHolder holder, int position) {
        if (mGardes != null) {
            int[] images = {R.drawable.ourccf, R.drawable.vsavnuit, R.drawable.cnd, R.drawable.sdis, R.drawable.ccfm
                           , R.drawable.pmp, R.drawable.pompiersp};
            Random random = new Random();
            Gardes current = mGardes.get(position);
            holder.interItemView.setText(current.getDate());
            holder.interItemView2.setText(current.getChef());
            holder.interItemView4.setText(current.getConducteur());
            holder.interItemView5.setText(current.getEquipier1());
            holder.interItemView6.setText(current.getEquipier2());
            holder.interItemView3.setText(current.getConsignes());
            Log.i("PRESENCE", Integer.toString(current.getPresence()));
            if (current.getPresence() == 0) {
                holder.imageView.setVisibility(View.GONE);
            }
            holder.headerImgView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            holder.headerImgView.setImageResource(images[random.nextInt(images.length)]);
            
        } else {
            // Covers the case of data not being ready yet.
            holder.interItemView.setText("No Word");
        }
        
    }
    
    public void setInters(List<Gardes> inter) {
        mGardes = inter;
        
        
    }
    
    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mGardes != null)
            return mGardes.size();
        else return 0;
    }
    
    static class InterViewHolder extends RecyclerView.ViewHolder {
        private final TextView interItemView;
        private final TextView interItemView2;
        private final TextView interItemView3;
        private final TextView interItemView4;
        private final TextView interItemView5;
        private final TextView interItemView6;
        private final ImageView imageView;
        private final ImageView headerImgView;
        
        private InterViewHolder(View itemView) {
            super(itemView);
            interItemView = itemView.findViewById(R.id.nomitem);
            interItemView2 = itemView.findViewById(R.id.peremption);
            interItemView4 = itemView.findViewById(R.id.dotation);
            interItemView5 = itemView.findViewById(R.id.restant);
            interItemView6 = itemView.findViewById(R.id.divers);
            interItemView3 = itemView.findViewById(R.id.consignes);
            imageView = itemView.findViewById(R.id.imageView3);
            headerImgView = itemView.findViewById(R.id.headerImgView);
            itemView.setOnClickListener(new View.OnClickListener() {
                
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(mGardes.get(position));
                        
                    }
                }
            });
        }
    }
    
    public interface OnItemClickListener {
        void onItemClick(Gardes gardes);
    }
    
    public void setOnItemClickListener(OnItemClickListener listener) {
        GardesListAdapter.listener = listener;
    }
}
