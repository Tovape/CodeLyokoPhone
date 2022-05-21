package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {

    // Request Permission for app
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_CONTACTS = 1;

    // Variables
    EditText Title;
    EditText SearchBox;
    ImageButton Goback;

    // Other
    Context context = this;

    // Methods
    public void getContactList() {
        ArrayList ContactArraylist = new ArrayList();
        int usercount = 0;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String user = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            ContactArraylist.add(new ContactArray(user, phone));
            usercount++;
            Log.d("LOGCAT", "ENTRY : " + usercount + " " + ContactArraylist.get(0));
        }
        phones.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // Getting Variable Instances
        Title = (EditText) findViewById(R.id.Title);
        SearchBox = (EditText) findViewById(R.id.SearchBox);
        Goback = (ImageButton) findViewById(R.id.Goback);

        // Set Fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Applying font
        Typeface font = Typeface.createFromAsset(getAssets(), "font/Square.ttf");
        Title.setTypeface(font);
        SearchBox.setTypeface(font);

        // Enabling Read Only in Title
        Title.setEnabled(false);

        // Go Back

        Goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        // Load Contacts
        if (ContextCompat.checkSelfPermission(ContactList.this, android.Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(ContactList.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactList.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            ActivityCompat.requestPermissions(ContactList.this, new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS);
        } else {
            getContactList();
        }

        // Filtering
        SearchBox.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable SearchBox) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Action Here
            }
        });

    }
}