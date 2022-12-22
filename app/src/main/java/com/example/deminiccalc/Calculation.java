package com.example.deminiccalc;

import android.widget.EditText;

import java.util.ArrayList;

public class Calculation {
    private int Add(int a, int b){
        return a + b;
    };
    private int multiply(int a, int b){
        return a * b;
    };
    private int Division(int a, int b){
        return a / b;
    };
    private int Subtraction(int a, int b){
        return a - b;
    };
    public String calculate(ArrayList<String> data, EditText solScreen){
        int res = 0;
        String bodmas = "✖÷+−";
        int Str_len = data.size();
                                                //Check for Bracket
        if (data.contains("(")){
            boolean step1 = data.contains(")");
            if (step1){
                int start = data.indexOf('(') - 1;
                int end = data.indexOf(')');
                String temp = data.subList(0, start - 1) + calculate((ArrayList<String>) data.subList(start, end),solScreen)+ data.subList(end + 1, Str_len);
            }else{
                //data.replace("(","");
            }
        }
        return(Integer.toString(res));
    }
}
