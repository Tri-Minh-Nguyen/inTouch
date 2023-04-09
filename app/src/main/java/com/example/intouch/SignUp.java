package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUp extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private EditText edtPassword2;
    private Button btSignUp;

    private TextView tvUnmatched;

    private DatabaseSingleton dbHelperSingleton;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelperSingleton = DatabaseSingleton.getInstance(this);
        dbHelper = dbHelperSingleton.getDatabaseHelper();

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password1);
        edtPassword2 = findViewById(R.id.edt_password2);
        btSignUp = findViewById(R.id.bt_signup);
        tvUnmatched = findViewById(R.id.tv_unmatched_password);


        btSignUp.setOnClickListener(view -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            String password2 = edtPassword2.getText().toString();
            Boolean proceed = dbHelper.loginUser(email, password);


            if (password.equals(password2)) {
                dbHelper.addUser(email, password);
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
            else {
                tvUnmatched.setVisibility(View.VISIBLE);}

        });
    }
}