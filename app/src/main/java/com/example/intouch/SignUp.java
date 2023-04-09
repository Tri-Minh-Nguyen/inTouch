package com.example.intouch;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class SignUp extends AppCompatActivity {

    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPassword2;
    private Button btSignUp;
    private TextView tvUnmatched;
    private TextView tvFailedSignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mAuth = FirebaseAuth.getInstance();

        edtName = findViewById(R.id.edt_name);
        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password1);
        edtPassword2 = findViewById(R.id.edt_password2);
        btSignUp = findViewById(R.id.bt_signup);
        tvUnmatched = findViewById(R.id.tv_unmatched_password);
        tvFailedSignup = findViewById(R.id.tv_failed_signup);


        btSignUp.setOnClickListener(view -> {
            String name = edtName.getText().toString();
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String password2 = edtPassword2.getText().toString();
            if (password.equals(password2)) {
                signUp(email, password);
            }
            else {
                tvFailedSignup.setVisibility(View.INVISIBLE);
                tvUnmatched.setVisibility(View.VISIBLE);
            }
        });
    }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(SignUp.this, Login.class);
                            startActivity(intent);
                        } else {
                            tvUnmatched.setVisibility(View.INVISIBLE);
                            tvFailedSignup.setVisibility(View.VISIBLE);
                        }
                    }
                });
    }

}