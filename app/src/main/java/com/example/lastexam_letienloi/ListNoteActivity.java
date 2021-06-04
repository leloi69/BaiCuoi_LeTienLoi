package com.example.lastexam_letienloi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.example.lastexam_letienloi.model.ListNote;

import java.util.Calendar;
public class ListNoteActivity extends AppCompatActivity {
    String id,dateTime,title,des;
    EditText edtitle,eddes,edtime,eddate;
    Button btnupdate, btnback;
    SQLiteHelpterProject sqLiteHelpterProject;
    int year,month,day;
    int hour,minute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_note);
        edtitle=findViewById(R.id.title);
        eddes=findViewById(R.id.des);
        edtime=findViewById(R.id.time);
        eddate=findViewById(R.id.date);
        btnupdate=findViewById(R.id.btnupdate);
        sqLiteHelpterProject=new SQLiteHelpterProject(this);
        Intent intent=getIntent();
        id=intent.getStringExtra("id");
        title=intent.getStringExtra("title");
        dateTime=intent.getStringExtra("datetime");
        des=intent.getStringExtra("des");
        edtitle.setText(title);
        eddes.setText(des);
        edtime.setText(getTime(dateTime));
        eddate.setText(getDate(dateTime));
        btnback = findViewById(R.id.back);
        btnupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title=edtitle.getText().toString();
                String dateed=eddate.getText().toString()+"-"+edtime.getText().toString();
                String des=eddes.getText().toString();
                SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("profile", MainActivity.MODE_PRIVATE);
                String username = sharedPreferences.getString("name", null);
                sqLiteHelpterProject.updateListnote(new ListNote(Integer.parseInt(id),title,des,dateed,username));
                Intent intent = new Intent(ListNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListNoteActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        eddate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calender = Calendar.getInstance();
                year = calender.get(Calendar.YEAR);
                month = calender.get(Calendar.MONTH);
                day = calender.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(ListNoteActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog timePickerDialog=new TimePickerDialog(ListNoteActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        edtime.setText(""+hourOfDay+":"+minute);
                    }
                },hour,minute,true);
                timePickerDialog.show();
            }
        });
    }
    private String getDate(String cn){
        String [] time_spilt=cn.split("-");
        String date=time_spilt[0];
        String time=time_spilt[1];
        return date;
    }
    private String getTime(String cn){
        String [] time_spilt=cn.split("-");
        String date=time_spilt[0];
        String time=time_spilt[1];
        return time;
    }
}