package com.example.ddine.perimeterandarea;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;


public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton triangle = findViewById(R.id.triangleButton);
        ImageButton square = findViewById(R.id.squareButton);
        ImageButton history = findViewById(R.id.historyButton);

        triangle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent triangleCalculator = new Intent(MainActivity.this,TriangleCalculator.class);
                startActivity(triangleCalculator);
            }
        });
        square.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent squareCalculator = new Intent(MainActivity.this,SquareCalculator.class);
                startActivity(squareCalculator);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent calculationHistory = new Intent(MainActivity.this,CalculationHistory.class);
                startActivity(calculationHistory);
            }
        });
    }
}
