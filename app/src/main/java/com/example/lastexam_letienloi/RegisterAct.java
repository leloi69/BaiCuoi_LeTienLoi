package com.example.lastexam_letienloi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lastexam_letienloi.model.User;

public class RegisterAct extends AppCompatActivity {
    private Button cancle, register;
    private EditText ed1, ed2, ed3;
    SQLiteHelpterProject sqLiteHelpterProject;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ed1 = findViewById(R.id.userN);
        ed2 = findViewById(R.id.userP);
        ed3 = findViewById(R.id.userP2);
        cancle = findViewById(R.id.btnCancle);
        register = findViewById(R.id.btnRegis);
        sqLiteHelpterProject=new SQLiteHelpterProject(RegisterAct.this);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = ed1.getText().toString() ;
                String password = ed2.getText().toString() ;
                String repassword = ed3.getText().toString() ;
                if (password.equals(repassword)) {
                    User user = new User(name, password);
                    sqLiteHelpterProject.addUser(user);
                    Toast.makeText(RegisterAct.this, "Thêm thành công ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("user", user);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        });
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(RESULT_CANCELED, null);
                finish();
            }
        });
    }
}