package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        Intent intent = getIntent();
        ArrayList<Student> students = new ArrayList<Student>();

        int size = intent.getIntExtra("size",0);
        for(int i=0;i<size;i++){
            students.add(Student.parse(intent.getStringExtra(String.valueOf(i))));
        }
        StudentAdapter adapter = new StudentAdapter(this,students);
        ListView listView = (ListView) findViewById(R.id.lvMain);
        listView.setAdapter(adapter);
    }
}