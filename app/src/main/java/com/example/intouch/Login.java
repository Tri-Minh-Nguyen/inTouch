package com.example.intouch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Login extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private Button btLogin;
    private Button btSingUp;
    private Button btRecover;
    private TextView tvPassword;
    private DatabaseSingleton dbHelperSingleton;
    private DatabaseHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelperSingleton = DatabaseSingleton.getInstance(this);
        dbHelper = dbHelperSingleton.getDatabaseHelper();

        edtEmail = findViewById(R.id.edt_email);
        edtPassword = findViewById(R.id.edt_password);
        btLogin = findViewById(R.id.bt_login);
        btSingUp = findViewById(R.id.bt_signup);
        btRecover = findViewById(R.id.bt_forgotpassword);
        tvPassword = findViewById(R.id.tv_incorrect_login);

        dbHelper.addUser("test@test.com","1234");

        btLogin.setOnClickListener(view -> {
            String email = edtEmail.getText().toString();
            String password = edtPassword.getText().toString();
            Boolean proceed = dbHelper.loginUser(email, password);
            if (proceed) {
                Intent intent = new Intent(this, Contacts.class);
                startActivity(intent);
            }
            else {
                tvPassword.setVisibility(View.VISIBLE);
            }

        });

        btSingUp.setOnClickListener(view -> {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
        });

    }
}

