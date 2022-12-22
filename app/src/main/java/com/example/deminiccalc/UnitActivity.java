package com.example.deminiccalc;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
public class UnitActivity extends AppCompatActivity{
    DrawerLayout drawer;
    NavigationView NavBar;
    private ActionBarDrawerToggle toggleNav;
    EditText first_convert;
    EditText second_convert;
    Intent intent;


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (toggleNav.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.unit);
        first_convert=findViewById(R.id.first_convert);
        second_convert=findViewById(R.id.second_convert);
        drawer = findViewById(R.id.drawer);
        NavBar =findViewById(R.id.navigationView);
        toggleNav = new ActionBarDrawerToggle(this,drawer,R.string.menu_open, R.string.menu_close);
        drawer.addDrawerListener(toggleNav);
        toggleNav.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);




        NavBar.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.health:
                        intent = new Intent(UnitActivity.this,HealthActivity.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.basic:
                        intent = new Intent(UnitActivity.this,MainActivity.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

    }
    public void handleClick(View view){
    }


}
