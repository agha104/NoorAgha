package com.noor.ag.noortasksmanger;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity
{
    private EditText etEmail;
    private EditText etPassword;
    private Button btnSignUp;
    private Button btnLogIn;
    private Button btnForgetMyPassword;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        etEmail=(EditText)findViewById(R.id.etEmail);
        etPassword=(EditText)findViewById(R.id.etPassword);
        btnSignUp=(Button)findViewById(R.id.btnSignUp);
        btnLogIn=(Button)findViewById(R.id.btnLogIn);
        btnForgetMyPassword=(Button)findViewById(R.id.btnForgetMyPassword);
        auth=FirebaseAuth.getInstance();

        eventHandler();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;

    }


    /**
     * 1.getting data from the ui from (edittext, checkbox, radiobutton etc..)
     * 2. checking data(the email text is ok, the password)
     * 3. deaking with the data
     */
    private void dataHandler() {
        //1.getting data
        String stEmail = etEmail.getText().toString();
        String stPassword=etPassword.getText().toString();
        boolean isOk=true;
        //2.checking
        if (stEmail.length() == 0) {
            etEmail.setError("Wrong Email");
            isOk = false;
        }
        if (stPassword.length()==0) {
            etPassword.setError("Wrong Password");
            isOk = false;
        }
        if (isOk)
        signIn(stEmail,stPassword);

    }
    private void signIn(String email, String passw) {
        auth.signInWithEmailAndPassword(email,passw).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(LoginActivity.this, "signIn Successful.", Toast.LENGTH_SHORT).show();
                    // Intent intent=new Intent(LogInActivity.this,MainFCMActivity.class);
                    //   startActivity(intent);

                    Intent i=new Intent(LoginActivity.this, TasksListActivity.class);
                    startActivity(i);
                    finish();
                }
                else
                {
                    Toast.makeText(LoginActivity.this, "signIn failed."+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    task.getException().printStackTrace();
                }
            }
        });
    }
    private void eventHandler()
    {
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Intent i=new Intent(LoginActivity.this,SignUpActivity.class);
                startActivity(i);

            }
        });
        btnLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              dataHandler();


            }
        });
        btnForgetMyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(authStateListener);
    }
    @Override
    protected void onStop() {
        super.onStop();
        if(authStateListener!=null)
            auth.removeAuthStateListener(authStateListener);
    }
    private FirebaseAuth.AuthStateListener authStateListener=new FirebaseAuth.AuthStateListener() {
        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
            //4.
            FirebaseUser user=firebaseAuth.getCurrentUser();
            if(user!=null)
            {
                //user is signed in
                Toast.makeText(LoginActivity.this, "user is signed in.", Toast.LENGTH_SHORT).show();

            }
            else
            {
                //user signed out
                Toast.makeText(LoginActivity.this, "user signed out.", Toast.LENGTH_SHORT).show();

            }
        }
    };

    }








