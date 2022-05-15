package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import android.Manifest;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.InputType;
import android.util.Log;
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
        buttonZero = (ImageButton)findViewById(R.id.buttonZero);
        buttonOne = (ImageButton)findViewById(R.id.buttonOne);
        buttonTwo = (ImageButton)findViewById(R.id.buttonTwo);
        buttonThree = (ImageButton)findViewById(R.id.buttonThree);
        buttonFour = (ImageButton)findViewById(R.id.buttonFour);
        buttonFive = (ImageButton)findViewById(R.id.buttonFive);
        buttonSix = (ImageButton)findViewById(R.id.buttonSix);
        buttonSeven = (ImageButton)findViewById(R.id.buttonSeven);
        buttonEight = (ImageButton)findViewById(R.id.buttonEight);
        buttonNine = (ImageButton)findViewById(R.id.buttonNine);
        buttonClear = (ImageButton)findViewById(R.id.buttonClear);
        buttonCrew = (ImageButton)findViewById(R.id.buttonCrew);

        // Set Number Input as Number Field with custom Font
        numberInput.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_NUMBER_FLAG_SIGNED);

        // Applying font
        Typeface font = Typeface.createFromAsset(getAssets(), "font/Square.ttf");
        numberInput.setTypeface(font);

        // Enabling Read Only in numberInput
        numberInput.setEnabled(false);

        // Button Actions

        buttonZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("0");
            }
        });

        buttonOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("1");
            }
        });

        buttonTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("2");
            }
        });

        buttonThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("3");
            }
        });

        buttonFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("4");
            }
        });

        buttonFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("5");
            }
        });

        buttonSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("6");
            }
        });

        buttonSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("7");
            }
        });

        buttonEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("8");
            }
        });

        buttonNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("9");
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.setText("");
            }
        });

        buttonCrew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberInput.append("+");
            }
        });

        // Internet Status

        ConnectivityManager connectivityManager = (ConnectivityManager)this.getSystemService(CONNECTIVITY_SERVICE);
        NetworkCapabilities nc = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
        int downSpeed = nc.getLinkDownstreamBandwidthKbps();
        int upSpeed = nc.getLinkUpstreamBandwidthKbps();

        Log.d("Download Speed: ", Integer.toString(downSpeed));
        Log.d("Upload Speed: ", Integer.toString(upSpeed));

        // Performing action on button click
        buttonDone.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                String number = numberInput.getText().toString();

                if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
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



