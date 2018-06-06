package com.example.ddine.perimeterandarea;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import java.util.List;

public class CalculationHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculation_history);
        readRecords();

    }
    public void readRecords(){
        DBOpenHelper dbOpenHelper = new DBOpenHelper(this);
        TableLayout tableLayoutRecords =  findViewById(R.id.tableLayoutRecords);
        tableLayoutRecords.removeAllViews();


        List<Triangle> triangles = dbOpenHelper.ObjectRead();

        if (triangles.size() > 0) {

            for (Triangle obj : triangles) {

                View tableRow = LayoutInflater.from(this).inflate(R.layout.table_item,null,false);
                TextView valueA = tableRow.findViewById(R.id.valueA);
                TextView valueB = tableRow.findViewById(R.id.valueB);
                TextView valueC = tableRow.findViewById(R.id.valueC);
                TextView result = tableRow.findViewById(R.id.result);
                TextView explanation = tableRow.findViewById(R.id.explanation);

                valueA.setText(obj.valueA);
                valueB.setText(obj.valueB);
                valueC.setText(obj.valueC);
                result.setText(obj.Result);
                explanation.setText(obj.history);
                tableLayoutRecords.addView(tableRow);

            }

        }

        else {

            TextView locationItem = new TextView(this);
            locationItem.setPadding(8, 8, 8, 8);
            locationItem.setText("No records yet.");
            tableLayoutRecords.addView(locationItem);
        }

    }

}
