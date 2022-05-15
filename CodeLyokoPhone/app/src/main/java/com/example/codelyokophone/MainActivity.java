package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class MainActivity extends AppCompatActivity {
    // Request Permission for app
    private static final int REQUEST_CALL = 1;

    // Variables

    // Number Input
    EditText numberInput;

    // Slide Options
    ImageButton buttonFwr;
    ImageButton buttonAsper;
    ImageButton buttonDone;

    // Top Options
    ImageButton buttonCall;
    ImageButton buttonClear;
    ImageButton buttonHang;

    // Bottom Options
    ImageButton buttonCrew;
    ImageButton buttonZero;
    ImageButton buttonHastag;

    // Numbers
    ImageButton buttonOne;
    ImageButton buttonTwo;
    ImageButton buttonThree;
    ImageButton buttonFour;
    ImageButton buttonFive;
    ImageButton buttonSix;
    ImageButton buttonSeven;
    ImageButton buttonEight;
    ImageButton buttonNine;

    // Main Activity
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Getting Variable Instances
        numberInput = (EditText)findViewById(R.id.numberInput);
        buttonDone = (ImageButton)findViewById(R.id.buttonDone);

        // Set Number Input as Number Field
        numberInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        // Enabling Read Only in numberInput
        numberInput.setEnabled(false);

        // Performing action on button click
        buttonDone.setOnClickListener(new View.OnClickListener(){

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



