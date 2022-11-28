package net.kaleoweb.newappcpi;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.PorterDuff;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.firebase.ui.auth.data.model.Resource;


public class MyDialog extends Dialog {
    LinearLayout ll;
    ProgressBar spinner;
    
    @Override
    public void show() {
        super.show();
        
        
    }
    
    @SuppressLint("ResourceAsColor")
    public MyDialog(@NonNull Context context) {
        super(context);
        
        
        ViewGroup.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ll = new LinearLayout(context);
        spinner = new ProgressBar(context, null, android.R.attr.progressBarStyleLarge);
        spinner.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        spinner.setIndeterminate(true);
        spinner.setClickable(false);
        this.getWindow().setBackgroundDrawableResource(R.color.fui_transparent);
        ll.setGravity(Gravity.CENTER);
        ll.addView(spinner);
        
        this.addContentView(ll, layoutParams);
        
    }
    
    
}
