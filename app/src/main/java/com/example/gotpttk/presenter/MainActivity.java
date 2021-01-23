package com.example.gotpttk.presenter;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.gotpttk.R;
import com.example.gotpttk.view.adminGui.accountFragments.AccountDetailsFragment;
import com.example.gotpttk.view.adminGui.sectionsFragments.SectionsManagementFragment;
import com.example.gotpttk.view.guestGui.loginFragments.LoginFragment;
import com.example.gotpttk.view.guestGui.planRouteFragments.PlanRouteFragment;
import com.example.gotpttk.view.guestGui.searchSectionsFragments.SearchSectionsFragment;
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
        getSupportActionBar().setTitle("Logowanie");

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.menu_open, R.string.menu_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // This ensures that when we rotate device behavior won't be overwritten
        if (savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_login);
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
            case R.id.nav_search_sections:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SearchSectionsFragment()).commit();
                getSupportActionBar().setTitle("Przeglądanie odcinków");
                break;
            case R.id.nav_plan_travel_route:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new PlanRouteFragment()).commit();
                getSupportActionBar().setTitle("Planowanie wycieczki");
                break;
            case R.id.nav_login:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new LoginFragment()).commit();
                getSupportActionBar().setTitle("Logowanie");
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}
