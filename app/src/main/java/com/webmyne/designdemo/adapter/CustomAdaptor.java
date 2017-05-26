package com.webmyne.designdemo.adapter;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by chiragpatel on 22-05-2017.
 */

public class CustomAdaptor extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<User> mObjects;
    public static Map<Integer, User> selectedList = new HashMap<>();
    private String font = "PT_Serif-Web-Bold.ttf";

    public static final int Row = 0;
    public static final int Col = 1;

    public CustomAdaptor(Context mContext, List<User> mObjects) {
        this.mContext = mContext;
        this.mObjects = mObjects;
    }

    /*public List<User> getSelectedData() {
        return new ArrayList<>();
    }*/

    @Override
    public int getItemViewType(int position) {
        if (position % 2 == 0)
            return Row;
        else
            return Col;
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
            case Row:
                View itemView0 = inflater.inflate(R.layout.item_row, parent, false);
                return new RowViewHolder(itemView0);
            case Col:
                View itemView1 = inflater.inflate(R.layout.item_column, parent, false);
                return new ColumnViewHolder(itemView1);

            default:
                View itemView11 = inflater.inflate(R.layout.item_row, parent, false);
                return new RowViewHolder(itemView11);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        final User user = mObjects.get(position);
        switch (getItemViewType(position)) {
            case Row:
                RowViewHolder rowViewHolder = (RowViewHolder) holder;
                //rowViewHolder.imageView.setImageResource(user.getImage());
                String img = "http://weknowyourdreams.com/images/food/food-07.jpg";

                Glide.with(mContext)
                        .load(img)
                        .placeholder(R.mipmap.ic_launcher)
                        .into(rowViewHolder.imageItem);

                rowViewHolder.textName.setText(user.getItemName());
                rowViewHolder.textDesc.setText(user.getDesc());
                break;

            case Col:
                ColumnViewHolder columnViewHolder = (ColumnViewHolder) holder;
                columnViewHolder.setValues(mObjects.get(position), position);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return mObjects.size();
    }


    public class RowViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageItem;
        private TextView textName;
        private TextView textDesc;

        public RowViewHolder(View itemView) {
            super(itemView);
            imageItem = (ImageView) itemView.findViewById(R.id.imageItem);
            textName = (TextView) itemView.findViewById(R.id.textName);
            textDesc = (TextView) itemView.findViewById(R.id.textDesc);

            Typeface tf = Typeface.createFromAsset(mContext.getResources().getAssets(), font);
            textName.setTypeface(tf);
            textDesc.setTypeface(tf);

            imageItem.setOnClickListener(new View.OnClickListener() {
                @TargetApi(Build.VERSION_CODES.LOLLIPOP)
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, FullScreenActivity.class);

                    View sharedView = imageItem;
                    String transitionName = mContext.getString(R.string.profile_name);

                    ActivityOptions transitionActivityOptions = ActivityOptions
                            .makeSceneTransitionAnimation((Activity) mContext, sharedView, transitionName);
                    mContext.startActivity(intent, transitionActivityOptions.toBundle());

                    //mContext.startActivity(intent);
                    //((Activity) mContext).overridePendingTransition(R.anim.slide_in_right, R.anim.slide_in_right);

                }
            });
        }
    }

    public class ColumnViewHolder extends RecyclerView.ViewHolder {

        private EditText edtName;
        private EditText editTime;
        private int mHour, mMinute, mAm;

        public ColumnViewHolder(View itemView) {
            super(itemView);
            edtName = (EditText) itemView.findViewById(R.id.edtName);
            editTime = (EditText) itemView.findViewById(R.id.editTime);

            Typeface tf = Typeface.createFromAsset(mContext.getResources().getAssets(), font);
            edtName.setTypeface(tf);
            editTime.setTypeface(tf);
        }

        private void setValues(final User user, final int position) {
            edtName.setText(user.getName());
            editTime.setText(user.getTime());

            edtName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = edtName.getText().toString();
                    user.setName(name);
                    openDialog(user, position);
                }
            });

            editTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final Calendar c = Calendar.getInstance();
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);
                    mAm = c.get(Calendar.AM_PM);

                    // Launch Time Picker Dialog
                    TimePickerDialog timePickerDialog = new TimePickerDialog(mContext,
                            AlertDialog.THEME_DEVICE_DEFAULT_DARK, new TimePickerDialog.OnTimeSetListener() {

                        @Override
                        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

                            String timeSet = "";
                            if (hourOfDay > 12) {
                                hourOfDay -= 12;
                                timeSet = "PM";
                            } else if (hourOfDay == 0) {
                                hourOfDay += 12;
                                timeSet = "AM";
                            } else if (hourOfDay == 12)
                                timeSet = "PM";
                            else
                                timeSet = "AM";

                            String minutes = "";
                            if (minute < 10)
                                minutes = "0" + minute;
                            else
                                minutes = String.valueOf(minute);

                            editTime.setText(hourOfDay + ":" + minutes + " " + timeSet);
                            user.setTime(hourOfDay + ":" + minutes + " " + timeSet);
                            editTime.setTag(mContext);

                            mObjects.get(position).setTime(hourOfDay + ":" + minutes + " " + timeSet);
                            //mObjects.get(position).setEditable(true);

                            selectedList.put(user.getId(), user);

                        }
                    }, mHour, mMinute, false);

                    timePickerDialog.show();
                }
            });
        }

        private void openDialog(final User user, final int position) {

            LayoutInflater inflater = LayoutInflater.from(mContext);
            View subView = inflater.inflate(R.layout.dialog_layout, null);
            final EditText dialogEditText = (EditText) subView.findViewById(R.id.dialogEditText);
            final Button btnOK = (Button) subView.findViewById(R.id.btn_ok);
            final Button btnCancel = (Button) subView.findViewById(R.id.btn_cancel);

            final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setTitle("Update Name");
            builder.setView(subView);
            builder.setCancelable(false);


            final AlertDialog dialog = builder.create();

            dialog.show();

            btnOK.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = dialogEditText.getText().toString();

                    if (!name.isEmpty()) {
                        edtName.setText(name);
                        mObjects.get(position).setName(name);
                        selectedList.put(user.getId(), user);
                        dialog.dismiss();

                    } else {
                        Toast.makeText(mContext, "Please enter the name", Toast.LENGTH_LONG).show();
                    }
                }
            });

            btnCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });



            /*builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                   *//* String name = dialogEditText.getText().toString();

                    if (!name.isEmpty()) {
                        edtName.setText(name);
                        mObjects.get(position).setName(name);
                        selectedList.put(user.getId(), user);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mContext, "Please enter the name", Toast.LENGTH_LONG).show();
                        return;
                    }*//*
                    //mObjects.get(position).setEditable(true);
                }
            });

            builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                  *//*  Toast.makeText(mContext, "Cancel", Toast.LENGTH_LONG).show();
                    dialog.dismiss();*//*
                }
            });*/


           /* dialog.setButton(DialogInterface.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String name = dialogEditText.getText().toString();

                    if (!name.isEmpty()) {
                        edtName.setText(name);
                        mObjects.get(position).setName(name);
                        selectedList.put(user.getId(), user);
                        dialog.dismiss();
                    } else {
                        Toast.makeText(mContext, "Please enter the name", Toast.LENGTH_LONG).show();
                        return;
                    }
                }
            });

            dialog.setButton(,DialogInterface.BUTTON_NEGATIVE).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "Cancel", Toast.LENGTH_LONG).show();
                    dialog.dismiss();
                }
            });*/

            //builder.show();
        }

    }
}
