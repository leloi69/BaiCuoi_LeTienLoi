package com.example.lastexam_letienloi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.lastexam_letienloi.model.ListNote;

import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class UserProfile extends AppCompatActivity {
    SQLiteHelpterProject sqLiteHelpterProject;
    TextView userName, totalNote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sqLiteHelpterProject=new SQLiteHelpterProject(UserProfile.this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile);
        Button bt = findViewById(R.id.back);
        userName = findViewById(R.id.name);
        totalNote = findViewById(R.id.size);
        SharedPreferences sharedPreferences = UserProfile.this.getSharedPreferences("profile", MainActivity.MODE_PRIVATE);
        String username = sharedPreferences.getString("name", null);
        List<ListNote> list = sqLiteHelpterProject.getAllListnoteByUserName(username);
        int total = list.size();
        userName.setText(username);
        totalNote.setText(String.valueOf(total));
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserProfile.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}