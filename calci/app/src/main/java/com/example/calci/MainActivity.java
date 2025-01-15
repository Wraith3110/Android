package com.example.calci;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
public class MainActivity extends AppCompatActivity {
    boolean isEmpty = true;
    String numOld = "";
    String op = "+";
    EditText edt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt = findViewById(R.id.editText);
    }
    @SuppressLint("NonConstantResourceId")
    public void numberEvent(View view) {
        if (isEmpty) {
            edt.setText("");
        }
        isEmpty = false;
        String number = edt.getText().toString();

        int id = view.getId();
        if (id == R.id.butDot) {
            number += ".";
        } else if (id == R.id.but0) {
            number += "0";
        } else if (id == R.id.but1) {
            number += "1";
        } else if (id == R.id.but2) {
            number += "2";
        } else if (id == R.id.but3) {
            number += "3";
        } else if (id == R.id.but4) {
            number += "4";
        } else if (id == R.id.but5) {
            number += "5";
        } else if (id == R.id.but6) {
            number += "6";
        } else if (id == R.id.but7) {
            number += "7";
        } else if (id == R.id.but8) {
            number += "8";
        } else if (id == R.id.but9) {
            number += "9";
        } else if (id == R.id.butPlusMinus) {
            number = "-" + number;
        }

        edt.setText(number);
    }

    public void operatorEvent(View view) {
        isEmpty = true;
        numOld = edt.getText().toString();

        int id = view.getId();
        if (id == R.id.butDivide) {
            op = "/";
        } else if (id == R.id.butMultiply) {
            op = "*";
        } else if (id == R.id.butPlus) {
            op = "+";
        } else if (id == R.id.butMinus) {
            op = "-";
        }
    }

    public void equalEvent(View view) {
        String numNew = edt.getText().toString();
        double result = 0.0;
        switch (op){
            case "+":
                result=Double.parseDouble(numOld)+Double.parseDouble(numNew);
                break;
            case "-":
                result=Double.parseDouble(numOld)-
                        Double.parseDouble(numNew);
                break;
            case "*":
                result=Double.parseDouble(numOld)*Double.parseDouble(numNew);
                break;
            case "/":
                result=Double.parseDouble(numOld)/Double.parseDouble(numNew);
                break;
        }
        edt.setText(result+"");
    }
    public void clearEvent(View view) {
        edt.setText("0");
        isEmpty = true;
    }
    public void moduloEvent(View view) {
        double num = Double.parseDouble(edt.getText().toString())/100;
        edt.setText(num+"");
        isEmpty=true;
    }
}