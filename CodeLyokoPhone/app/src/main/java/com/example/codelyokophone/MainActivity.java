package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    // Request Permission for app
    private static final int REQUEST_CALL = 1;

    // Variables
    EditText numberInput;
    ImageButton button1;

    // Main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting Variable Instances
        numberInput = (EditText)findViewById(R.id.numberInput);
        button1 = (ImageButton)findViewById(R.id.button1);

        // Performing action on button click
        button1.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String number = numberInput.getText().toString();

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
                    numberInput.setText("Grant Permission First");
                } else {
                    // Make Phone Call
                    if (number.trim().length() > 0) {
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:"+number));
                        startActivity(callIntent);
                    }
                }
            }
        });
    }
}



