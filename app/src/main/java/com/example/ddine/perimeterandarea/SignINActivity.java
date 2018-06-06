package com.example.ddine.perimeterandarea;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignINActivity extends AppCompatActivity {

    Button btnLogIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btnLogIn = findViewById(R.id.buttonLogIn);


// get the References of views
        final EditText editTextUserName = findViewById(R.id.editTextUserNameToLogin);
        final EditText editTextPassword = findViewById(R.id.editTextPasswordToLogin);
        final DBOpenHelper loginDataBaseAdapter = new DBOpenHelper(this);
// Set On ClickListener
        btnLogIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
// get The User name and Password
                String userName=editTextUserName.getText().toString();
                String password=editTextPassword.getText().toString();

// fetch the Password form database for respective user name
                String storedPassword = loginDataBaseAdapter.getSingleEntry(userName);

// check if the Stored password matches with Password entered by user
                if(password.equals(storedPassword))
                {
                    Intent intentMain = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intentMain);

                }
                else
                {
                    Toast.makeText(SignINActivity.this, "User Name or Password does not match", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
