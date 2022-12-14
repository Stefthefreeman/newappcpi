package net.kaleoweb.newappcpi.ui.numbersutils;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import net.kaleoweb.newappcpi.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NumbersUtils#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NumbersUtils extends Fragment {
    
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageButton codis;
    private ImageButton csp;
    public NumbersUtils() {
        // Required empty public constructor
    }
    
    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NumbersUtils.
     */
    // TODO: Rename and change types and number of parameters
    public static NumbersUtils newInstance(String param1, String param2) {
        NumbersUtils fragment = new NumbersUtils();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View root = inflater.inflate(R.layout.fragment_numbers_utils, container, false);
       
       codis = root.findViewById(R.id.appelcodis);
       csp = root.findViewById(R.id.appelpertuis);
       
       codis.setOnClickListener(view -> {
           startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0490899047")));
       });
       
       csp.setOnClickListener(view -> {
           startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "0490817150")));
       });
       
        return root;
    }
}