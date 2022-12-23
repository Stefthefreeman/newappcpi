package net.kaleoweb.newappcpi.utilities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilsSuap extends DiffUtil.Callback {
    List<Suapdatas> oldSuap;
    List<Suapdatas> newSuap;
    
    public DiffUtilsSuap() {
        super();
    }
    public DiffUtilsSuap(List<Suapdatas> oldSuap, List<Suapdatas> newSuap){
        this.oldSuap = oldSuap;
        this.newSuap = newSuap;
    }
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Suapdatas anc = oldSuap.get(oldItemPosition);
        Suapdatas newG = newSuap.get(newItemPosition);
        
        
        Bundle bundle = new Bundle();
        if(!newG.getNom().equals(anc.getNom())){
            bundle.putString("nom",newG.getNom());
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

