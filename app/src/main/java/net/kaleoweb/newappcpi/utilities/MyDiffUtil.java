package net.kaleoweb.newappcpi.utilities;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DiffUtil;

import java.util.ArrayList;
import java.util.List;

public class MyDiffUtil extends DiffUtil.Callback{
    
    
        
        List<Pharma> oldPharma;
        List<Pharma> newPharma;
        
        public MyDiffUtil(List<Pharma> oldPharma, List<Pharma> newPharma) {
            this.oldPharma = oldPharma;
            this.newPharma = newPharma;
        }
        
        @Override
        public int getOldListSize() {
            return oldPharma !=null ? oldPharma.size() : 0;
        }
        
        @Override
        public int getNewListSize() {
            return newPharma != null ? newPharma.size() : 0;
        }
        
        @Override
        public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
            return true;
        }
        
        @Override
        public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
            int result = Integer.compare(newItemPosition,oldItemPosition);
            
            if (result==0){
                return true;
            }
            return false;
        }
        
        @Nullable
        @Override
        public Object getChangePayload(int oldItemPosition, int newItemPosition) {
            Pharma newContact = newPharma.get(newItemPosition);
            Pharma oldContact = oldPharma.get(oldItemPosition);
            
            Bundle bundle = new Bundle();
            
            if (newContact.getRestant()!= oldContact.getRestant()){
                bundle.putInt("Restant",newContact.getRestant());
            }
            
            if (newContact.getRestant() != oldContact.getRestant()){
                bundle.putInt("mobile",newContact.getRestant());
            }
            
            if (bundle.size()==0){
                return null;
            }
            return bundle;
        }
    
}
