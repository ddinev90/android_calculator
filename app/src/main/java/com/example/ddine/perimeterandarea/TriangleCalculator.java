package com.example.ddine.perimeterandarea;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class TriangleCalculator extends AppCompatActivity implements TextWatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_triangle_calculator);
        final Spinner menuSelect = findViewById(R.id.triangle_formula_spinner);
        final EditText input1 = findViewById(R.id.SquareInput1);
        final EditText input2 = findViewById(R.id.SquareInput2);
        Button calculate = findViewById(R.id.calculate);
        final EditText input3 = findViewById(R.id.Input3);

        final TextView Result = findViewById(R.id.SquareResult);
        Result.setVisibility(View.GONE);
        input3.setVisibility(View.INVISIBLE);
        final DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        calculate.setEnabled(false);

        input1.addTextChangedListener(this);
        input2.addTextChangedListener(this);

        menuSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        input1.setHint("Base");
                        input2.setHint("Height");
                        input3.setVisibility(View.INVISIBLE);

                        break;
                    case 1:
                        input1.setHint("Side A");
                        input2.setHint("Side B");
                        input3.setHint("Side C");
                        input3.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        input1.setHint("Side A");
                        input2.setHint("Side B");
                        input3.setHint("Angle C");
                        input3.setVisibility(View.VISIBLE);
                        break;
                    case 3:
                        input1.setHint("Side A");
                        input2.setHint("Side B");
                        input3.setHint("Side C");
                        input3.setVisibility(View.VISIBLE);
                        break;
                    case 4:
                        input1.setHint("Side A");
                        input2.setHint("Side B");
                        input3.setHint("Angle C");
                        input3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DecimalFormat df = new DecimalFormat("#.00");
                     if (menuSelect.getSelectedItemPosition()  == 0 ){
                     double base = Double.parseDouble(input1.getText().toString());
                     double height = Double.parseDouble(input2.getText().toString());
                     double result = (base*height)/2;
                     dbOpenHelper.insertTriangleCalc(String.valueOf(base),String.valueOf(height),"n/a",String.valueOf(df.format(result)),"Found triangle area using width and height");
                     Result.setText((df.format(result)));
                     Result.setVisibility(View.VISIBLE);

                    }
                    else if(menuSelect.getSelectedItemPosition()  == 1 ){
                        double a = Double.parseDouble(input1.getText().toString());
                        double b = Double.parseDouble(input2.getText().toString());
                        double c = Double.parseDouble(input3.getText().toString());
                        double p = (a+b+c)/2;
                        double area = Math.sqrt((p*(p-a)*(p-b)*(p-c)));
                        dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),String.valueOf(c),String.valueOf(df.format(area)),"Found triangle area using all sides");
                        Result.setText(df.format(area));
                        Result.setVisibility(View.VISIBLE);

                }
                else if(menuSelect.getSelectedItemPosition() == 2){
                        double a = Double.parseDouble(input1.getText().toString());
                        double b = Double.parseDouble(input2.getText().toString());
                        double c = Double.parseDouble(input3.getText().toString());
                        double radians = Math.toRadians(c);
                        double area = Math.sin(radians) * ((a*b)/2);
                        dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),String.valueOf(c),String.valueOf(df.format(area)),"Found triangle area using trig functions");
                        Result.setText(df.format(area));
                        Result.setVisibility(View.VISIBLE);
                    }

                    else if(menuSelect.getSelectedItemPosition() == 3){
                        double a = Double.parseDouble(input1.getText().toString());
                        double b = Double.parseDouble(input2.getText().toString());
                        double c = Double.parseDouble(input3.getText().toString());
                        double p = a+b+c;
                        dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),String.valueOf(c),String.valueOf(df.format(p)),"Found triangle perimeter using all sides");
                        Result.setText(df.format(p));
                        Result.setVisibility(View.VISIBLE);

                    }
                    else if(menuSelect.getSelectedItemPosition() == 4) {
                        double a = Double.parseDouble(input1.getText().toString());
                        double b = Double.parseDouble(input2.getText().toString());
                        double c = Double.parseDouble(input3.getText().toString());
                        double radians = Math.toRadians(c);
                        double d = Math.sqrt((Math.pow(a,2)+Math.pow(b,2)-(2*a*b*Math.cos(radians))));

                        double result = d + a + b;
                        dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),String.valueOf(c),String.valueOf(df.format(result)),"Found triangle perimeter using Law of Cosines");
                        Result.setText(df.format(result));
                        Result.setVisibility(View.VISIBLE);
                }

        }

    });
}

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
        final EditText input1 = findViewById(R.id.SquareInput1);
        final EditText input2 = findViewById(R.id.SquareInput2);
        Button calculate = findViewById(R.id.calculate);

        if (input1.getText().length() > 0
                && input2.getText().length() > 0
               ) {

            calculate.setEnabled(true);
        } else {

            calculate.setEnabled(false);
        }
    }
}
