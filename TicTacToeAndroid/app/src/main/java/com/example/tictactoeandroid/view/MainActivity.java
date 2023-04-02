package com.example.tictactoeandroid.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tictactoeandroid.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.INTERNET}, PackageManager.PERMISSION_GRANTED);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);



        String password = "1234";
        String userName = "user";
        ((Button)findViewById(R.id.logInBtn)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText userNameTxt = ((EditText)findViewById(R.id.sIdInput));
                EditText passwordTxt = ((EditText)findViewById(R.id.sPasswordInput));

                Intent intent = new Intent(MainActivity.this, GameActivity.class);
                intent.putExtra("username", userNameTxt.getText().toString());
                startActivity(intent);
            }
        });
    }

}