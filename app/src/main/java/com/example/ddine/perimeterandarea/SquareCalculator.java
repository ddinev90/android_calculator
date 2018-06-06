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

import java.text.DecimalFormat;

public class SquareCalculator extends AppCompatActivity implements TextWatcher {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square_calculator);
        final Spinner menuSelect = findViewById(R.id.rectangle_formula_spinner);
        final EditText input1 = findViewById(R.id.SquareInput1);
        final EditText input2 = findViewById(R.id.SquareInput2);
        Button calculate = findViewById(R.id.calculate);
        final TextView Result = findViewById(R.id.SquareResult);
        Result.setVisibility(View.GONE);
        final DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        input1.addTextChangedListener(this);
        input2.addTextChangedListener(this);
        calculate.setEnabled(false);
        menuSelect.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        input1.setHint("Width");
                        input2.setHint("Height");


                        break;
                    case 1:
                        input1.setHint("Length");
                        input2.setHint("Diagonal");

                        break;

                    case 2:
                        input1.setHint("Width");
                        input2.setHint("Height");

                        break;
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
                    double width = Double.parseDouble(input1.getText().toString());
                    double height = Double.parseDouble(input2.getText().toString());
                    double result = width*height;
                    dbOpenHelper.insertTriangleCalc(String.valueOf(width),String.valueOf(height),"n/a",String.valueOf(df.format(result)),"Found rectangle area using width and length");
                    Result.setText(df.format(result));
                    Result.setVisibility(View.VISIBLE);
                } else if(menuSelect.getSelectedItemPosition()  == 1 ){
                    double a = Double.parseDouble(input1.getText().toString());
                    double b = Double.parseDouble(input2.getText().toString());
                    double d = Math.sqrt((Math.pow(a,2)+ Math.pow(b,2)));
                    double result = a*d;
                    dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),"n/a",String.valueOf(df.format(result)),"Found rectangle area using length and diagonal");
                    Result.setText(df.format(result));
                    Result.setVisibility(View.VISIBLE);
                }
                else if(menuSelect.getSelectedItemPosition() == 2){
                    double a = Double.parseDouble(input1.getText().toString());
                    double b = Double.parseDouble(input2.getText().toString());
                    double result = 2*a+2*b;
                    dbOpenHelper.insertTriangleCalc(String.valueOf(a),String.valueOf(b),"n/a",String.valueOf(df.format(result)),"Found rectangle perimeter using width and length");
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
