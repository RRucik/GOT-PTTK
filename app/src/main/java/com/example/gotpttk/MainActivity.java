package com.example.gotpttk;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gotpttk.view.adminGui.accountFragments.AccountDetailsFragment;
import com.example.gotpttk.view.adminGui.sectionsFragments.SectionsManagementFragment;
import com.example.gotpttk.view.adminGui.spotsFragments.SpotsManagementFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener
{
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Informacje o koncie");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close);
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
        // If menu is open and return button pressed, close the menu first
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.nav_manage_spots:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SpotsManagementFragment()).commit();
                getSupportActionBar().setTitle("Zarządzanie punktami");
                break;
            case R.id.nav_manage_sections:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SectionsManagementFragment()).commit();
                getSupportActionBar().setTitle("Zarządzanie odcinkami");
                break;
            case R.id.nav_account:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new AccountDetailsFragment()).commit();
                getSupportActionBar().setTitle("Informacje o koncie");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
