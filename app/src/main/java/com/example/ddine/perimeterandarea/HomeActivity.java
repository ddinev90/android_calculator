package com.example.ddine.perimeterandarea;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity
{
    Button btnSignIn,btnSignUp;
    DBOpenHelper loginDataBaseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnSignIn= findViewById(R.id.buttonSignIn);
        btnSignUp= findViewById(R.id.buttonSignUp);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent intentSignIn = new Intent(getApplicationContext(),SignINActivity.class);
               startActivity(intentSignIn);
            }
        });
// Set OnClick Listener on SignUp button
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
/// Create Intent for SignUpActivity abd Start The Activity

                Intent intentSignUP=new Intent(getApplicationContext(),SignUPActivity.class);
                startActivity(intentSignUP);
            }
        });
    }
    // Methods to handleClick Event of Sign In Button


    @Override
    protected void onDestroy() {
        super.onDestroy();
// Close The Database
        loginDataBaseAdapter.close();
    }
}