package com.example.codelyokophone;

import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> implements View.OnClickListener {

    private RecyclerView contactlist;
    private View.OnClickListener listener;
    private final ArrayList contactArraylist;
    private Activity activity;

    public ContactAdapter(@NonNull ArrayList contactArraylist, RecyclerView contactlist, Activity activity) {
        this.contactlist = contactlist;
        this.contactArraylist = contactArraylist;
        this.activity = activity;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View row = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_list_item, parent, false);
        row.setOnClickListener((View.OnClickListener) this);
        return new ContactViewHolder(row);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactViewHolder holder, int position) {
        String[] splited = contactArraylist.get(position).toString().split("SEPARATORTVP");
        holder.contactname.setText(splited[0]);
        holder.contactphone.setText(splited[1]);
    }

    @Override
    public int getItemCount() {
        return contactArraylist.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        String[] splited = contactArraylist.get(contactlist.getChildAdapterPosition(view)).toString().split("SEPARATORTVP");
        Log.d("LOGCAT", "Calling " + splited[1]);
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result", splited[1]);
        activity.setResult(activity.RESULT_OK,returnIntent);
        activity.finish();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        private final Button contactname;
        private final Button contactphone;

        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            contactname = (Button) itemView.findViewById(R.id.contactname);
            contactphone = (Button) itemView.findViewById(R.id.contactphone);
        }
    }
}


