package com.webmyne.designdemo.fragment;


import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webmyne.designdemo.R;
import com.webmyne.designdemo.adapter.CustomAdaptor;
import com.webmyne.designdemo.model.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private RecyclerView rvMultipleViewType;
    private  static List<User> mData;
    private CustomAdaptor adaptor;
    private EditText editName;
    private EditText editTime;
    private Button btnSave;


    public HomeFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        HomeFragment homeFragment = new HomeFragment();
        Bundle args = new Bundle();
        homeFragment.setArguments(args);
        return homeFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        rvMultipleViewType = (RecyclerView) view.findViewById(R.id.rv_multipe_view_type);
        editName = (EditText) view.findViewById(R.id.edtName);
        editTime = (EditText) view.findViewById(R.id.editTime);
        btnSave = (Button) view.findViewById(R.id.btnSave);

        init();

        return view;
    }

    private void init() {

        mData = new ArrayList<>();

       /* for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setId(i);
            user.setImage(R.drawable.logo);
            user.setItemName("Vada Pav");
            user.setName("Select Name");
            user.setCity("Delicious");
            user.setTime("10:00 AM");
            mData.add(user);

            for (Map.Entry<Integer, User> sList : CustomAdaptor.selectedList.entrySet()) {
                mData.add(sList.getValue());
            }
        }*/

        mData.add(new User(1,R.drawable.logo,"Select Name","Vada Pav","Delicious","10:00 AM"));
        mData.add(new User(2,R.drawable.logo,"Select Name","Dabeli","Delicious","11:00 AM"));
        mData.add(new User(3,R.drawable.logo,"Select Name","Franky","Delicious","12:00 AM"));
        mData.add(new User(4,R.drawable.logo,"Select Name","Pizza","Delicious","1:00 PM"));
        mData.add(new User(5,R.drawable.logo,"Select Name","Burger","Delicious","2:00 PM"));
        mData.add(new User(6,R.drawable.logo,"Select Name","Hot Dog","Delicious","3:00 PM"));
        mData.add(new User(7,R.drawable.logo,"Select Name","Puff","Delicious","4:00 PM"));
        mData.add(new User(8,R.drawable.logo,"Select Name","Sandwitch","Delicious","5:00 PM"));
        mData.add(new User(9,R.drawable.logo,"Select Name","Spring Roll","Delicious","6:00 PM"));


        adaptor = new CustomAdaptor(getContext(), mData);
        //rvMultipleViewType.addItemDecoration(new DividerItemDecoration(getContext(), LinearLayoutManager.VERTICAL));
        rvMultipleViewType.setAdapter(adaptor);
        rvMultipleViewType.setLayoutManager(new LinearLayoutManager(getContext()));


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adaptor.setDataList(mData);

                Log.e("Data", "" + mData);

//                Log.e("selected Data", "" + adaptor.getSelectedData());

//                mData = adaptor.getSelectedData();

                Log.e("mData", "" + mData);

                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                ResultFragment resultFragment = new ResultFragment();
                Bundle args = new Bundle();
                //args.putSerializable("Data", (Serializable) mData);
                resultFragment.setArguments(args);
                fragmentTransaction.replace(R.id.containerLayout, resultFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }
        });

    }

}
