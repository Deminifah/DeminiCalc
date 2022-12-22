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

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawer;
    NavigationView NavBar;
    private ActionBarDrawerToggle toggleNav;
    private EditText sol;
    TextView resultScreen;
    ImageView openScienceDialog;
    String Temp = "";
    String ScreenDisplay = "";
    ArrayList<String> Data =new ArrayList<>();
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
        setContentView(R.layout.activity_main);
        intent = new Intent(this,MainActivity.class);
//        solution = findViewById(R.id.solution_screen);
//        result = findViewById(R.id.result_screen);
        sol=findViewById(R.id.solutionScreen);
        resultScreen=findViewById(R.id.resultScreen);
        openScienceDialog=findViewById(R.id.scienceFunc);
        openScienceDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OpenScienceDialog();
            }
        });
//        sol.setTag(sol.getKeyListener());
//        sol.setKeyListener(null);
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
                   case R.id.unit:
                       intent = new Intent(MainActivity.this,UnitActivity.class);
                       startActivity(intent);
                       drawer.closeDrawer(GravityCompat.START);
                       break;
                   case R.id.health:
                       intent = new Intent(MainActivity.this,HealthActivity.class);
                       startActivity(intent);
                       drawer.closeDrawer(GravityCompat.START);
                       break;
                   }
               return true;
           }
       });

    }

    public void HandleClick(View view)
    {
        String bodmas="()✖÷+−";
        TextView btn=(TextView) view;
        String val= btn.getText().toString();
        if(bodmas.contains(val)){
            Data.add(Temp);
            Data.add(val);
            Temp = "";
        }else{
            Temp += val;
        }
        ScreenDisplay += val;
        sol.setText(ScreenDisplay);
        if(StartCalc(ScreenDisplay)){
            new Calculation().calculate(Data, sol);
        }

    }
    void OpenScienceDialog(){
    }
    boolean StartCalc(String solution){
        String bodmas="()✖÷+−";
        boolean verify = false;
        for (int i = 0; i < bodmas.length(); i++) {
            if(solution.contains(""+bodmas.charAt(i))){
                verify =true;
                break;
            }
        }
        for (int i = 0; i < bodmas.length(); i++) {
            if(solution.charAt(solution.length() - 1) == bodmas.charAt(i)){
                verify = false;
                break;
            }
        }
        return verify;
    }
}