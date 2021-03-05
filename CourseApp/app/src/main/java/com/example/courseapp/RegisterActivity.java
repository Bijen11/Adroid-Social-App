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
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {
    private EditText signup_email, signup_pass, sign_confirmPass;
    private FirebaseAuth mAuth;
    private TextView rbtn;
    private DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        signup_email = findViewById(R.id.signup_email_id);
        signup_pass = findViewById(R.id.signup_pass_id);
        sign_confirmPass = findViewById(R.id.signup_confirmpass_id);
        rbtn = findViewById(R.id.signup_login_id);
        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        rbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub

                /*
                 * Intent is just like glue which helps to navigate one activity
                 * to another.
                 */
                Intent intent = new Intent(RegisterActivity.this,
                        LoginActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }

    @Override
    public void onStart() {

        super.onStart();
    }

    public void signUp(View view) {
        String email = signup_email.getText().toString().trim();
        String pass = signup_pass.getText().toString().trim();
        String confirm_pass = sign_confirmPass.getText().toString().trim();
        if (pass.equals(confirm_pass)) {
            createAccount(email, pass);
        }
        else{

            Toast.makeText(RegisterActivity.this, "Retype",
                    Toast.LENGTH_SHORT).show();
        }


    }

    private void createAccount(String email, String pass) {
        final String username = signup_email.getText().toString().substring(0, email.lastIndexOf("@"));;
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            String userid = firebaseUser.getUid();
                            dbref = FirebaseDatabase.getInstance().getReference("User").child(userid);
                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id",userid);
                            hashMap.put("username",username);
                            hashMap.put("imageURL","default");
                            dbref.setValue(hashMap);
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(RegisterActivity.this, "Signup Success.",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(RegisterActivity.this,MainActivity.class));

                        } else {

                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }
}
