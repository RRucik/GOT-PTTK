package com.example.gotpttk.presenter;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.example.gotpttk.R;
import com.example.gotpttk.view.adminGui.accountFragments.AccountDetailsFragment;
import com.example.gotpttk.view.adminGui.sectionsFragments.SectionsManagementFragment;
import com.example.gotpttk.view.adminGui.spotsFragments.AddSpotFragment;
import com.example.gotpttk.view.adminGui.spotsFragments.EditSpotFragment;
import com.example.gotpttk.view.adminGui.spotsFragments.RemoveSpotFragment;
import com.example.gotpttk.view.adminGui.spotsFragments.SpotsManagementFragment;
import com.google.android.material.navigation.NavigationView;

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{

    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informacje o koncie");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close){
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset){
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(drawerView.getWindowToken(), 0);
            }
        };
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // This ensures that when we rotate device behavior won't be overwritten
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountDetailsFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_account);
        }
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }

    @Override
    public void onBackPressed()
    {
        FragmentManager fm = getSupportFragmentManager();
        // If menu is open and return button pressed, close the menu first
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else if(fm.getBackStackEntryCount() > 0)
        {
            NavigationView navigationView = findViewById(R.id.nav_view);
            if(fm.getBackStackEntryCount() == 1){
                getSupportActionBar().setTitle("Informacje o koncie");
                navigationView.setCheckedItem(R.id.nav_account);
            }
            else if(fm.getBackStackEntryCount() == 2){
                Fragment fragment = fm.findFragmentByTag("tag2");
                if(fragment instanceof EditSpotFragment || fragment instanceof RemoveSpotFragment || fragment instanceof AddSpotFragment){
                    getSupportActionBar().setTitle("Zarządzanie punktami");
                    navigationView.setCheckedItem(R.id.nav_manage_spots);
                }
                else{
                    getSupportActionBar().setTitle("Zarządzanie odcinkami");
                    navigationView.setCheckedItem(R.id.nav_manage_sections);
                }
            }
            fm.popBackStack();
        }
        else{
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        FragmentManager fm = getSupportFragmentManager();
        for(int i = 0; i < fm.getBackStackEntryCount(); ++i) {
            fm.popBackStack();
        }
        switch (item.getItemId())
        {
            case R.id.nav_manage_spots:
                fm.beginTransaction().replace(R.id.fragment_container, new SpotsManagementFragment()).addToBackStack("tag1").commit();
                getSupportActionBar().setTitle("Zarządzanie punktami");
                break;
            case R.id.nav_manage_sections:
                fm.beginTransaction().replace(R.id.fragment_container, new SectionsManagementFragment()).addToBackStack("tag1").commit();
                getSupportActionBar().setTitle("Zarządzanie odcinkami");
                break;
            case R.id.nav_account:
                fm.beginTransaction().replace(R.id.fragment_container, new AccountDetailsFragment()).addToBackStack("tag1").commit();
                getSupportActionBar().setTitle("Informacje o koncie");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}