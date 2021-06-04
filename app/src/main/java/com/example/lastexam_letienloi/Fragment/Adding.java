package com.example.lastexam_letienloi.Fragment;

import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.lastexam_letienloi.ListNoteActivity;
import com.example.lastexam_letienloi.MainActivity;
import com.example.lastexam_letienloi.R;
import com.example.lastexam_letienloi.RegisterAct;
import com.example.lastexam_letienloi.SQLiteHelpterProject;
import com.example.lastexam_letienloi.model.ListNote;
import com.example.lastexam_letienloi.model.User;

import java.util.Calendar;

public class Adding extends Fragment {
    User u;
    EditText edtitle,eddes,edtime,eddate;
    Button btadd;
    int year,month,day;
    int hour,minute;
    SQLiteHelpterProject sqLiteHelpterProject;
    private Context mContext;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtitle=view.findViewById(R.id.title);
        eddes=view.findViewById(R.id.des);
        edtime=view.findViewById(R.id.time);
        eddate=view.findViewById(R.id.date);
        btadd=view.findViewById(R.id.btnadd);
        sqLiteHelpterProject=new SQLiteHelpterProject(view.getContext());
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(view.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        eddate.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, year, month, day);
                datePickerDialog.show();
            }
        });
        edtime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                hour = calender.get(Calendar.HOUR_OF_DAY);
                minute = calender.get(Calendar.MINUTE);
                TimePickerDialog timePickerDialog=new TimePickerDialog(view.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtime.setText(""+hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
        btadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=edtitle.getText().toString();
                String datetime=eddate.getText().toString()+"-"+edtime.getText().toString();
                System.out.println(datetime);
                String des=eddes.getText().toString();
                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("profile", MainActivity.MODE_PRIVATE);
                String username = sharedPreferences.getString("name", null);
                sqLiteHelpterProject.addListnote(new ListNote(title,des,datetime,username));
                Toast.makeText(v.getContext(), "Thêm thành công ", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_adding, container, false);
    }


}