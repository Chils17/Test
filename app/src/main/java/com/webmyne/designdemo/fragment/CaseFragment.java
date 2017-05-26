package com.webmyne.designdemo.fragment;


import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.webmyne.designdemo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CaseFragment extends Fragment {

    private static final String KEY_TITLE = "key_title";

    public CaseFragment() {
        // Required empty public constructor
    }


    public static Fragment newInstance() {
        CaseFragment caseFragment = new CaseFragment();
        Bundle args = new Bundle();
        caseFragment.setArguments(args);
        return caseFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_case, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
