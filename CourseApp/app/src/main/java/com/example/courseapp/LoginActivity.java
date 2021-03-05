package com.example.courseapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private EditText login_email, login_pass;
    private TextView mbtn;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        login_email = findViewById(R.id.login_email_id);
        login_pass = findViewById(R.id.login_pass_id);
        mbtn = findViewById(R.id.login_signup_id);

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();
        mbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

    public void login(View view) {
        String email = login_email.getText().toString().trim();
        String pass = login_pass.getText().toString().trim();
        userLogin(email, pass);
    }

    private void userLogin(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LoginActivity.this, "Login success", Toast.LENGTH_SHORT).show();

                            startActivity(new Intent(LoginActivity.this,MainActivity.class));

                        } else {
                            Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
