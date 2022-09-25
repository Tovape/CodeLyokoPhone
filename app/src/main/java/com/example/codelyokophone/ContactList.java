package com.example.codelyokophone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.Manifest;
import android.app.Activity;
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
import java.util.Arrays;

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
    RecyclerView contactlist;
    ArrayList ContactArraylist = new ArrayList();
    ContactArray[] contactarray = new ContactArray[1000];
    private int contactCount = 0;
    String SearchBoxFilter;
    private ContactViewModel contactViewModel;

    // Other
    Activity activity = this;
    Context context = this;

    // Methods
    public void getContactList() {
        contactCount = 0;
        Cursor phones = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
        Uri simUri = Uri.parse("content://icc/adn");
        Cursor cursorSim = this.getContentResolver().query(simUri, null, null,null, null);

        ArrayList checker = new ArrayList();

        String user;
        String phone;

        // Get Normal Contacts
        while (phones.moveToNext()) {
            user = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            phone = phones.getString(phones.getColumnIndexOrThrow(ContactsContract.CommonDataKinds.Phone.NUMBER));
            if (!checker.contains(user.toUpperCase())) {
                checker.add(user.toUpperCase());
                contactarray[contactCount] = new ContactArray(user, phone);
                ContactArraylist.add(contactarray[contactCount]);
                contactCount++;
                //Log.d("LOGCAT", "NEW: " + user + " | phone: " + phone);
            }
        }

        // Get Sim Contacts
        while (cursorSim.moveToNext()) {
            user = cursorSim.getString(cursorSim.getColumnIndexOrThrow("name"));
            phone = cursorSim.getString(cursorSim.getColumnIndexOrThrow("number"));
            if (!checker.contains(user.toUpperCase())) {
                checker.add(user.toUpperCase());
                contactarray[contactCount] = new ContactArray(user, phone);
                ContactArraylist.add(contactarray[contactCount]);
                contactCount++;
                //Log.d("LOGCAT", "NEW: " + user + " | phone: " + phone);
            }
        }

        phones.close();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_list);

        // Getting Variable Instances
        contactlist = (RecyclerView) findViewById(R.id.contactlist);
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
        contactViewModel = new ViewModelProvider(this).get(ContactViewModel.class);
        contactlist.setAdapter(new ContactAdapter(ContactArraylist, contactlist, activity));

        contactlist.setLayoutManager(new LinearLayoutManager(context));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(contactlist.getContext(),
                ((LinearLayoutManager) contactlist.getLayoutManager()).getOrientation());
        contactlist.addItemDecoration(dividerItemDecoration);

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

            }
        });

    }
}