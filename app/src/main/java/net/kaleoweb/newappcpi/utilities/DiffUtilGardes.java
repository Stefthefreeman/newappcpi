package net.kaleoweb.newappcpi.utilities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilGardes extends DiffUtil.Callback{
    List<Gardes> oldGardes;
    List<Gardes> newGardes;
    
    public DiffUtilGardes() {
        super();
    }
    public DiffUtilGardes(List<Gardes> oldGardes, List<Gardes> newGardes){
        this.oldGardes = oldGardes;
        this.newGardes = newGardes;
    }
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Gardes anc = oldGardes.get(oldItemPosition);
        Gardes newG = newGardes.get(newItemPosition);
    
    
        Bundle bundle = new Bundle();
        if(!newG.getDtsql().equals(anc.getDtsql())){
            bundle.putString("dt",newG.getDtsql());
        }
    
        return bundle;
    }
    
    @Override
    public int getOldListSize() {
        return 0;
    }
    
    @Override
    public int getNewListSize() {
        return 0;
    }
    
    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
    
    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return false;
    }
}
