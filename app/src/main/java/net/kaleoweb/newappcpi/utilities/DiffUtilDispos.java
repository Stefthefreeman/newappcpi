package net.kaleoweb.newappcpi.utilities;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.List;

public class DiffUtilDispos extends DiffUtil.Callback {
    
    List<Dispos> oldDispos;
    List<Dispos> newDispos;
    
    public DiffUtilDispos() {
        super();
    }
    
    public DiffUtilDispos(List<Dispos> oldDispos, List<Dispos> newDispos) {
        this.oldDispos = oldDispos;
        this.newDispos = newDispos;
        
        
    }
    
    @Nullable
    @Override
    public Object getChangePayload(int oldItemPosition, int newItemPosition) {
        Dispos newDispo = newDispos.get(newItemPosition);
        Dispos oldDispo = oldDispos.get(oldItemPosition);
    
        Bundle bundle = new Bundle();
         if(!newDispo.getDt().equals(oldDispo.getDt())){
             bundle.putString("dt",newDispo.getDt());
         }
         
         return bundle;
    }
    
    @Override
    public int getOldListSize() {
        return oldDispos != null ? oldDispos.size() : 0;
    }
    
    @Override
    public int getNewListSize() {
        return newDispos != null ? newDispos.size() : 0;
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
