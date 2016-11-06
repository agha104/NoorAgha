package com.noor.ag.noortasksmanger;

import android.content.Intent;
import android.location.Location;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.noor.ag.noortasksmanger.data.MyTask;

import java.util.Calendar;
import java.util.Date;

public class AddTaskActivity extends AppCompatActivity {
    private EditText etText;
    private EditText etPhone;
    private Button btnContacts;
    private EditText etLocation;
    private RatingBar rtBarPriority;
    private Button btnSave;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);
        etPhone = (EditText) findViewById(R.id.etPhone);
        etText = (EditText) findViewById(R.id.etText);
        btnContacts = (Button) findViewById(R.id.btnContacts);
        etLocation = (EditText) findViewById(R.id.etLocation);
        rtBarPriority = (RatingBar) findViewById(R.id.rtBarPriority);
        btnSave = (Button) findViewById(R.id.btnSave);

        eventHandler();
    }

    private void dataHandler() {

        String stText = etText.getText().toString();
        String stPhone = etPhone.getText().toString();
        String stLocation = etLocation.getText().toString();
        int prio=rtBarPriority.getProgress();
        Date date= Calendar.getInstance().getTime();
        boolean isOk = true;

        if (stText.length() == 0) {
            etText.setError("Wrong Text");
            isOk = false;
        }
        if (stPhone.length() < 10) {
            etPhone.setError("Wrond Phone");
            isOk = false;
        }
        if (stLocation.length() == 0) {
            etLocation.setError("Wrong Location");
            isOk = false;
        }
        if (isOk) {
            MyTask m=new MyTask();
            m.setAdress(stLocation);
            m.setPhone(stPhone);
            m.setId(stText);
            m.setWhen(date);
            m.setPriority(prio);
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
            //  reference.push().setValue("hello world");
            String email= FirebaseAuth.getInstance().getCurrentUser().getEmail();
            email=email.replace(".","_");
            reference.child(email).child("MyTasks").push().setValue(m, new DatabaseReference.CompletionListener() {
                @Override
                public void onComplete(DatabaseError databaseError, DatabaseReference databaseReference) {
                    if (databaseError == null)//add successful
                    {
                        Toast.makeText(AddTaskActivity.this, "saved successful", Toast.LENGTH_LONG).show();
                    } else//adding failed
                    {
                        Toast.makeText(AddTaskActivity.this, "saved Failed" + databaseError.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    private void eventHandler() {
        btnContacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dataHandler();
                Intent i=new Intent(AddTaskActivity.this,TasksListActivity.class);
                startActivity(i);
                dataHandler();

            }
        });



    }


}