package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ContactList extends AppCompatActivity {

    // Request Permission for app
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_CONTACTS = 1;

    // Variables
    String numberSelected;
    EditText Title;
    EditText SearchBox;
    ImageButton Goback;
    ListView contactlist;
    ArrayList ContactArraylist = new ArrayList();
    ContactArray[] contactarray = new ContactArray[100];
    private int contactCount = 0;

    // Other
    Context context = this;

    // Methods
    public void getContactList() {
        contactCount = 0;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        while (phones.moveToNext()) {
            String user = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactarray[contactCount] = new ContactArray(user, phone);
            ContactArraylist.add(contactarray[contactCount]);
            contactCount++;
        }
        phones.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // Getting Variable Instances
        contactlist = (ListView) findViewById(R.id.contactlist);
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

        // Load List of Contacts
        CustomAdapter customAdapter = new CustomAdapter();
        contactlist.setAdapter(customAdapter);

        // Load Phone from List of Contacts


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

    class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return contactCount;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewgroup) {
            view = getLayoutInflater().inflate(R.layout.contacts_list_item, null);
            Button contactname = (Button) view.findViewById(R.id.contactname);
            Button contactphone = (Button) view.findViewById(R.id.contactphone);
            contactname.setText(contactarray[i].getName());
            contactphone.setText(contactarray[i].getPhone());

            contactname.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    numberSelected = contactarray[i].getPhone();
                    Log.d("LOGCAT", "Selected: " + contactarray[i].getPhone() + " | conversion: " + numberSelected);

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",numberSelected);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();

                }
            });

            contactphone.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    numberSelected = contactarray[i].getPhone();
                    Log.d("LOGCAT", "Selected: " + contactarray[i].getPhone() + " | conversion: " + numberSelected);
                    finish();
                }
            });

            return view;
        }
    }
}