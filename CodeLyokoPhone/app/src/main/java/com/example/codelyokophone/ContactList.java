package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import java.util.ArrayList;

public class ContactList extends AppCompatActivity {

    // Request Permission for app
    private static final int REQUEST_CALL = 1;
    private static final int REQUEST_READ_CONTACTS = 1;
    private static final int REQUEST_WRITE_CONTACTS = 1;

    // Variables
    LinearLayout contactEntry;
    String numberSelected;
    EditText Title;
    EditText SearchBox;
    ImageButton Goback;
    ListView contactlist;
    ArrayList ContactArraylist = new ArrayList();
    ContactArray[] contactarray = new ContactArray[1000];
    private int contactCount = 0;
    String SearchBoxFilter;

    // Other
    Context context = this;

    // Methods
    public void getContactList() {
        contactCount = 0;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Uri simUri = Uri.parse("content://icc/adn");
        Cursor cursorSim = this.getContentResolver().query(simUri, null, null,null, null);

        // Get Normal Contacts
        while (phones.moveToNext()) {
            String user = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            String phone = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            contactarray[contactCount] = new ContactArray(user, phone);
            ContactArraylist.add(contactarray[contactCount]);
            Log.d("LOGCAT", "NEW: " + user + " | phone: " + phone);
            contactCount++;
        }

        // Get Sim Contacts
        while (cursorSim.moveToNext()) {
            String user = cursorSim.getString(cursorSim.getColumnIndexOrThrow("name"));
            String phone = cursorSim.getString(cursorSim.getColumnIndexOrThrow("number"));
            Log.d("LOGCAT", "NEW: " + user + " | phone: " + phone);
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
                ContextCompat.checkSelfPermission(ContactList.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(ContactList.this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactList.this, new String[] {Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            ActivityCompat.requestPermissions(ContactList.this, new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_READ_CONTACTS);
            ActivityCompat.requestPermissions(ContactList.this, new String[] {Manifest.permission.READ_CONTACTS}, REQUEST_WRITE_CONTACTS);
        } else {
            getContactList();
        }

        // Load List of Contacts

        CustomAdapter customAdapter = new CustomAdapter();
        contactlist.setAdapter(customAdapter);

        // Filtering
        SearchBox.addTextChangedListener(new TextWatcher() {

            @Override
            public void afterTextChanged(Editable SearchBox) {}

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SearchBoxFilter = SearchBox.getText().toString();
                CustomAdapterFilter customAdapterfilter = new CustomAdapterFilter();
                contactlist.setAdapter(customAdapterfilter);
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

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",numberSelected);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            });

            return view;
        }
    }

    class CustomAdapterFilter extends BaseAdapter {

        @Override
        public int getCount() { return contactCount; }

        @Override
        public Object getItem(int position) { return null; }

        @Override
        public long getItemId(int position) { return 0; }

        @Override
        public View getView(int i, View view, ViewGroup viewgroup) {
            view = getLayoutInflater().inflate(R.layout.contacts_list_item, null);
            contactEntry = (LinearLayout) view.findViewById(R.id.contactEntry);

            Button contactname = (Button) view.findViewById(R.id.contactname);
            Button contactphone = (Button) view.findViewById(R.id.contactphone);

            contactname.setText(contactarray[i].getNameFiltered(SearchBoxFilter));
            contactphone.setText(contactarray[i].getPhoneFiltered(SearchBoxFilter));

            if (contactarray[i].getNameFiltered(SearchBoxFilter).equals("")) {
                contactEntry.setVisibility(View.GONE);
            }

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

                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("result",numberSelected);
                    setResult(Activity.RESULT_OK,returnIntent);
                    finish();
                }
            });

            return view;
        }
    }

}