package com.example.lastexam_letienloi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastexam_letienloi.model.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private Button login, register;
    private EditText ed1, ed2;
    private int REQUEST_CODE = 1111;
    private User user;
    SQLiteHelpterProject sqLiteHelpterProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ed1 = findViewById(R.id.ed1);
        ed2 = findViewById(R.id.ed2);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnRegis);
        sqLiteHelpterProject=new SQLiteHelpterProject(LoginActivity.this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sqLiteHelpterProject.login(ed1.getText().toString(), ed2.getText().toString())) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    User user = new User(ed1.getText().toString(), ed2.getText().toString());
                    SharedPreferences.Editor editor = getSharedPreferences( "profile", MODE_PRIVATE).edit();
                    editor.putString("name", user.getName());
                    editor.apply();
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Sai tên đăng nhập hoặc tài khoản", Toast.LENGTH_SHORT).show();
                }
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterAct.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    user = (User) data.getSerializableExtra("user");
                    ed1.setText(user.getName());
                    ed2.setText(user.getPass());
                } else {
                    Toast.makeText(this, "Dang ky chua thanh cong", Toast.LENGTH_SHORT);
                }
            }
        }
    }











}