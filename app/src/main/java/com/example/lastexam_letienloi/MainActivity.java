package com.example.lastexam_letienloi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.lastexam_letienloi.model.User;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    ViewPager vp;
    TabLayout tl;
    TabAdapter td;
    private User user;
    public static String global = "profile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Intent intent = getIntent();
//        User u1 = (User) intent.getSerializableExtra("user");
//        String email = u1.getName();
//        String pass = u1.getPass();
//        Intent intent = getIntent();
//        User u1 = (User) intent.getSerializableExtra("user");
//        if (u1.getName() != null) {
//            SharedPreferences.Editor editor = getSharedPreferences(global, MODE_PRIVATE).edit();
//            editor.putString("name", u1.getName());
//            editor.apply();
//        }
        vp =findViewById(R.id.viewp);
        td = new TabAdapter(getSupportFragmentManager(), TabAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        vp.setAdapter(td);
        tl = findViewById(R.id.tablayout);
        tl.setupWithViewPager(vp);
        tl.getTabAt(0).setText("Danh sach").setIcon(R.drawable.list_icon);
        tl.getTabAt(1).setText("Thêm").setIcon(R.drawable.add_icon);
        tl.getSelectedTabPosition();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_top, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.thongtin:
                Intent intent = new Intent(MainActivity.this, UserProfile.class);
                startActivity(intent);
            case R.id.thoat:
                Intent intentt = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intentt);
        }
        return true;
    }
}