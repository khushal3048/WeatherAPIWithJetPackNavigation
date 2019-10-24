package com.example.weatherapiwithjetpacknavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    public Toolbar toolbar;
    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public NavController navController;
    public Controller controller;
    public int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupNavigation();

    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(Navigation.findNavController(this,R.id.host_frag),drawerLayout);
    }

    public void setupNavigation(){

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        drawerLayout = findViewById(R.id.drawer_layout);

        navigationView = findViewById(R.id.navigationview);

        navController = Navigation.findNavController(this,R.id.host_frag);

        NavigationUI.setupActionBarWithNavController(this,navController,drawerLayout);
        NavigationUI.setupWithNavController(navigationView,navController);

        navigationView.setNavigationItemSelectedListener(this);

    }



  @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        menuItem.setCheckable(true);

        drawerLayout.closeDrawers();

        id = menuItem.getItemId();
        Bundle bundle = new Bundle();
        int geoId,bbcId;

        switch (id){

            default:
                geoId = 3534;
                bbcId = 6077243;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.montreal:
                geoId = 3534;
                bbcId = 6077243;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.ahemdabad:
                geoId = 2295402;
                bbcId = 1279233;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.surat:
                geoId = 2295405;
                bbcId = 1255364;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.toronto:
                geoId = 4118;
                bbcId = 6167865;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.bombay:
                geoId = 12586539;
                bbcId = 1275339;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
            case R.id.pune:
                geoId = 2295412;
                bbcId = 1259229;
                bundle.putInt("geoId",geoId);
                bundle.putInt("bbcId",bbcId);
                navController.navigate(R.id.dashboard,bundle);
                break;
        }

        return true;
    }
}
