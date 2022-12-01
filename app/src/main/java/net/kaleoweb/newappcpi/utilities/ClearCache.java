package net.kaleoweb.newappcpi.utilities;

import android.content.Context;
import android.util.Log;

import java.io.File;

public class ClearCache {
    
    public boolean clearCache(Context context) {
        try {
            
            // create an array object of File type for referencing of cache files
            File[] files = context.getCacheDir().listFiles();
            
            // use a for etch loop to delete files one by one
            for (File file : files) {
                
                /* you can use just [ file.delete() ] function of class File
                 * or use if for being sure if file deleted
                 * here if file dose not delete returns false and condition will
                 * will be true and it ends operation of function by return
                 * false then we will find that all files are not delete
                 */
                if (!file.delete()) {
                    return false;         // not success
                }
            }
            
            // if for loop completes and process not ended it returns true
            return true;      // success of deleting files
            
        } catch (Exception e) {}
        
        // try stops deleting cache files
        return false;       // not success
    }
}
