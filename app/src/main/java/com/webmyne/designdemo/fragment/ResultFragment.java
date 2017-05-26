package com.webmyne.designdemo.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webmyne.designdemo.R;
import com.webmyne.designdemo.adapter.CustomAdaptor;
import com.webmyne.designdemo.adapter.CustomResultAdaptor;
import com.webmyne.designdemo.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private RecyclerView recyclerView;
    private static List<User> users;
    private CustomResultAdaptor customResultAdaptor;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        ResultFragment resultFragment = new ResultFragment();
        Bundle args = new Bundle();
        resultFragment.setArguments(args);
        return resultFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);

        init();

        return view;
    }

    private void init() {
        users = new ArrayList<>();
        //users.add(data);
//        users = (List<User>) getArguments().getSerializable("Data");

        for (Map.Entry<Integer, User> sList : CustomAdaptor.selectedList.entrySet()) {
            users.add(sList.getValue());
        }

        Log.e("Result", "" + users);

        if (users == null) {
            users = new ArrayList<>();
        }

        customResultAdaptor = new CustomResultAdaptor(getContext(), users);
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(customResultAdaptor);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}
