package com.webmyne.designdemo.ui;


import android.animation.ObjectAnimator;
import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webmyne.designdemo.R;
import com.webmyne.designdemo.fragment.CaseFragment;
import com.webmyne.designdemo.fragment.HomeFragment;
import com.webmyne.designdemo.fragment.ResultFragment;

import net.cachapa.expandablelayout.ExpandableLayout;

import org.w3c.dom.Text;

public class CustomDrawerActivity extends AppCompatActivity implements View.OnClickListener {

    private DrawerLayout mDrawerLayout;
    private LinearLayout navLinear;
    private ExpandableLayout expandable_layout;
    private TextView txtDown;
    private TextView txtCase;
    private TextView txtHome;
    private TextView txtResult;
    private String font = "PT_Serif-Web-Regular.ttf";
    private TextView txtLog;
    private TextView txtClose;
    private TextView txtEdit;
    private TextView txtReopen;
    private TextView txtAdd;
    private TextView txtReplace;
    private TextView txtDelete;
    private TextView txtHistory;
    private TextView txtHighlight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_drawer);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer);

        setFragment(HomeFragment.newInstance());

        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        mDrawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        navLinear = (LinearLayout) findViewById(R.id.nav);

        navLinear.setOnClickListener(this);

        View headerView = LayoutInflater.from(this).inflate(R.layout.main_layout, null, false);
        navLinear.removeAllViews();
        navLinear.addView(headerView);

        expandable_layout = (ExpandableLayout) headerView.findViewById(R.id.expandable_layout);

        txtHome = (TextView) headerView.findViewById(R.id.txtHome);
        txtResult = (TextView) headerView.findViewById(R.id.txtResults);
        txtCase = (TextView) headerView.findViewById(R.id.txtCase);
        txtDown = (TextView) headerView.findViewById(R.id.txtDown);
        txtLog = (TextView) headerView.findViewById(R.id.txtLog);

        txtAdd = (TextView) headerView.findViewById(R.id.txtAdd);
        txtEdit = (TextView) headerView.findViewById(R.id.txtEdit);
        txtReopen = (TextView) headerView.findViewById(R.id.txtReopen);
        txtReplace = (TextView) headerView.findViewById(R.id.txtReplace);
        txtClose = (TextView) headerView.findViewById(R.id.txtClose);
        txtDelete = (TextView) headerView.findViewById(R.id.txtDelete);
        txtHistory = (TextView) headerView.findViewById(R.id.txtHistory);
        txtHighlight = (TextView) headerView.findViewById(R.id.txtHighlight);


        Typeface tf = Typeface.createFromAsset(getResources().getAssets(), font);
        txtHome.setTypeface(tf);
        txtResult.setTypeface(tf);
        txtCase.setTypeface(tf);
        txtLog.setTypeface(tf);

        txtAdd.setTypeface(tf);
        txtEdit.setTypeface(tf);
        txtReopen.setTypeface(tf);
        txtReplace.setTypeface(tf);
        txtClose.setTypeface(tf);
        txtDelete.setTypeface(tf);
        txtHistory.setTypeface(tf);
        txtHighlight.setTypeface(tf);

        txtHome.setOnClickListener(this);
        txtResult.setOnClickListener(this);
        txtCase.setOnClickListener(this);
        txtDown.setOnClickListener(this);

    }

    private void setFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.containerLayout, fragment);
        fragmentTransaction.commit();

        // Set action bar title
        setTitle(getTitle());
        // Close the navigation drawer
        mDrawerLayout.closeDrawers();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.txtHome:
                setFragment(HomeFragment.newInstance());
                break;

            case R.id.txtResults:
                setFragment(ResultFragment.newInstance());
                break;

            case R.id.txtCase:
                //setFragment(CaseFragment.newInstance());
                if (expandable_layout.isExpanded()) {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtDown, "rotation", 180f, 0f);
                    animation.setDuration(400).start();
                    expandable_layout.collapse();
                } else {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtDown, "rotation", 0f, 180f);
                    animation.setDuration(400).start();
                    expandable_layout.expand();
                }
                break;

            case R.id.txtDown:
                if (expandable_layout.isExpanded()) {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtDown, "rotation", 180f, 0f);
                    animation.setDuration(400).start();
                    expandable_layout.collapse();
                } else {
                    ObjectAnimator animation = ObjectAnimator.ofFloat(txtDown, "rotation", 0f, 180f);
                    animation.setDuration(400).start();
                    expandable_layout.expand();
                }
                break;
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
