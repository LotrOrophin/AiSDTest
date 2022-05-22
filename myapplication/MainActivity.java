package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<Student> students = new ArrayList<Student>();
    StudentAdapter sAdapter;
    Fragment addFragment;
    EditFragment editFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        students.add(new Student("Oleg Olegovich", 5));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addFragment = new ButtonFragment(this);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.addFragment,addFragment);
        ft.commit();
        editFragment = new EditFragment(this);
        // находим список
        ListView lvMain = (ListView) findViewById(R.id.lvMain);

        sAdapter = new StudentAdapter(this, students);
        // присваиваем адаптер списку
        lvMain.setAdapter(sAdapter);
        ListView listView = (ListView) findViewById(R.id.lvMain);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = (Student) parent.getItemAtPosition(position);
                editFragment.SetStudent(student);
                FragmentTransaction ftEdit = getSupportFragmentManager().beginTransaction();
                ftEdit.add(R.id.addFragment,editFragment);
                ftEdit.commit();
            }
        });
    }
    public void toAddFragment(){
        FragmentTransaction ftEdit = getSupportFragmentManager().beginTransaction();
        ftEdit.replace(R.id.addFragment,addFragment);
        ftEdit.commit();
    }
    public void report(int dodik){
        int selected = 0;
        Intent intent = new Intent(this,ReportActivity.class);
        for(int i = 0; i<students.size();i++){
            if(students.get(i).getMark() == dodik){
                intent.putExtra(String.valueOf(selected),students.get(i).toString());
                selected++;
            }
        }
        intent.putExtra("size",selected);
        startActivity(intent);
    }
}