package com.noor.ag.noortasksmanger;

import android.content.Intent;
import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.noor.ag.noortasksmanger.data.MyAdapterTask;
import com.noor.ag.noortasksmanger.data.MyTask;

public class TasksListActivity extends AppCompatActivity {
    private EditText etText;
    private Button btnFastAdd;
    private MyAdapterTask adapterTask;
    private ListView listView;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_tasks);
        listView=(ListView) findViewById(R.id.listView);
        adapterTask=new MyAdapterTask(this,R.layout.my_a_doupter);
        listView.setAdapter(adapterTask);
        etText = (EditText) findViewById(R.id.etText);
        btnFastAdd = (Button) findViewById(R.id.btnFastAdd);
        initListView();
        eventHandler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.itmLogOut:
                FirebaseAuth.getInstance().signOut();
                Intent i=new Intent(TasksListActivity.this,LoginActivity.class);
                startActivity(i);
                break;
        }
        return true;
    }






    private void dataHandler() {

        String stText = etText.getText().toString();
        boolean isok=true;

        if (stText.length() == 0) {
            etText.setError("Wrong Text");
            isok = false;
        }
        if (isok){
            Intent i=new Intent(TasksListActivity.this, AddTaskActivity.class);
              startActivity(i);

        }

    }


    private void eventHandler()
    {
        btnFastAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              //  Intent i=new Intent(TasksListActivity.this, AddTaskActivity.class);
             //   startActivity(i);
                dataHandler();



            }
        });
    }
    private void initListView(){
        String email=FirebaseAuth.getInstance().getCurrentUser().getEmail().replace('.','_');
        DatabaseReference reference=FirebaseDatabase.getInstance().getReference(email);
        reference.child("MyTasks").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                adapterTask.clear();
                for (DataSnapshot ds:dataSnapshot.getChildren())
                {
                    MyTask myTask=ds.getValue(MyTask.class);
                    myTask.setId(ds.getKey());
                    adapterTask.add(myTask);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}


