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
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
public class HealthActivity extends AppCompatActivity implements Health_ageDialog.Dialog_pickerListener, Health_HeightDialog.Dialog_pickerListener,Health_genderDialog.Dialog_pickerListener,Health_weightDialog.Dialog_pickerListener{
    DrawerLayout drawer;
    NavigationView NavBar;
    private ActionBarDrawerToggle toggleNav;
    Intent intent;
    TextView Age;
    TextView Gender;
    TextView Height;
    TextView Weight;
    TextView bmi_output;
    TextView bmr_output;

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
        setContentView(R.layout.health_calculator);
        Weight = findViewById(R.id.txt_weight);
        Gender = findViewById(R.id.txt_gender);
        Age=findViewById(R.id.txt_age);
        Height =findViewById(R.id.txt_height);
        bmi_output =findViewById(R.id.bmi_output);
        bmr_output = findViewById(R.id.bmr_output);
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
                        intent = new Intent(HealthActivity.this,UnitActivity.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                    case R.id.basic:
                        intent = new Intent(HealthActivity.this,MainActivity.class);
                        startActivity(intent);
                        drawer.closeDrawer(GravityCompat.START);
                        break;
                }
                return true;
            }
        });

    }
    public void HandleClick(View view){

        switch (view.getId()){
            case R.id.Weight:
                Health_weightDialog stat=new Health_weightDialog();
                stat.show(getSupportFragmentManager(),"Weight");
                break;
            case R.id.Height:
                Health_HeightDialog statB=new Health_HeightDialog();
                statB.show(getSupportFragmentManager(),"Height");
                break;
            case R.id.Gender:
                Health_genderDialog statC=new Health_genderDialog();
                statC.show(getSupportFragmentManager(),"Gender");
                break;
            case R.id.Age:
                Health_ageDialog statD=new Health_ageDialog();
                statD.show(getSupportFragmentManager(),"Age");
                break;
        }

        if (Integer.parseInt(Age.getText().toString()) > 0){
            bmi_output.setText(Double.toString(BMI(Height,Weight)));
        }
        if( Integer.parseInt(Age.getText().toString()) > 0 && Integer.parseInt(Weight.getText().toString()) > 0 && Integer.parseInt(Height.getText().toString()) > 0 ){
            bmr_output.setText(Double.toString(BMR(Age,Gender,Weight,Height)));
        }
    }
    public double BMR(TextView age, TextView gender, TextView weight, TextView height){
        System.out.println(age.getText().toString());
        if (age.getText().toString()=="Set"){
            return 0;
        }
        if (age.getText().toString() != "Set" && gender.getText().toString() != "Set" && weight.getText().toString() !="Set" && height.getText().toString()!= "Set")
        {
            if(gender.getText().toString().toLowerCase() == "male"){
                double weigh = Integer.parseInt(weight.getText().toString());
                double heigh = Integer.parseInt(height.getText().toString());
                double Age = Integer.parseInt(age.getText().toString());
                double bmr=66.47 +(3.75 * weigh) + (5.003 * heigh) - ( 6.755 * Age);
                return bmr;

            }else{
                double weigh = Double.parseDouble(weight.getText().toString());
                double heigh = Double.parseDouble(height.getText().toString());
                double Age = Double.parseDouble(age.getText().toString());
                double bmr = 655.1 + ( 9.563 * weigh) + ( 1.85 * heigh) - ( 4.676 * Age);
                return bmr;
            }
        }
        return (0);
    }
    public double BMI(TextView height, TextView weight){
        double meter=Double.parseDouble(height.getText().toString()) / 100;
        double weigh=Double.parseDouble(weight.getText().toString());
        double bmi= weigh / meter * meter;
        return bmi;
    }

    @Override
    public void applyStatD(String height) {
        Height.setText(height);
    }

    @Override
    public void applyStatB(String age) {
        Age=findViewById(R.id.txt_age);
        Age.setText(age);
    }

    @Override
    public void applyStatC(String gender) {
        Gender = findViewById(R.id.txt_gender);
        Gender.setText(gender);
    }

    @Override
    public void applyStat(String weight) {
        Weight = findViewById(R.id.txt_weight);
        Weight.setText(weight);
    }
}
