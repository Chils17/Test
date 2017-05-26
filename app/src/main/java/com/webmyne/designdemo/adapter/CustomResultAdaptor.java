package com.webmyne.designdemo.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.webmyne.designdemo.R;
import com.webmyne.designdemo.model.User;
import com.webmyne.designdemo.ui.FullScreenActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chiragpatel on 22-05-2017.
 */

public class CustomResultAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context mContext;
    private List<User> mObjects;


    public static final int COL = 0;

    public CustomResultAdaptor(Context mContext, List<User> mObjects) {
        this.mObjects = new ArrayList<>();
        this.mContext = mContext;
        this.mObjects = mObjects;
    }

    public void setDataList(List<User> dataList) {
        mObjects = new ArrayList<>();
        mObjects = dataList;
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        switch (viewType) {
            case COL:
                View itemView0 = inflater.inflate(R.layout.item_column, parent, false);
                return new RowViewHolder(itemView0);

            default:
                View itemView11 = inflater.inflate(R.layout.item_column, parent, false);
                return new RowViewHolder(itemView11);
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final User user = mObjects.get(position);
        switch (getItemViewType(position)) {
            case COL:
                RowViewHolder rowViewHolder = (RowViewHolder) holder;
                rowViewHolder.edtName.setText(user.getName());
                rowViewHolder.editTime.setText(user.getTime());
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }


    public class RowViewHolder extends RecyclerView.ViewHolder {

        private EditText edtName;
        private EditText editTime;

        public RowViewHolder(View itemView) {
            super(itemView);
            edtName = (EditText) itemView.findViewById(R.id.edtName);
            editTime = (EditText) itemView.findViewById(R.id.editTime);

        }
    }

}
