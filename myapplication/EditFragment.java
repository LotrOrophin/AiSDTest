package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class EditFragment extends Fragment {
    private MainActivity mainActivity;
    private Student student;
    private EditText editFio;
    private EditText editDodik;
    public EditFragment(MainActivity mainActivity) {
        // Required empty public constructor
        this.mainActivity = mainActivity;
    }
    public void SetStudent(Student student){
        this.student = student;
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_edit, container, false);
        editFio = v.findViewById((R.id.fio));
        editDodik = v.findViewById((R.id.dodik));
        Button buttonEdit = v.findViewById(R.id.edit);
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fio = editFio.getText().toString();
                int dodik = Integer.parseInt(editDodik.getText().toString());
                student.setFio(fio);
                student.setMark(dodik);
                mainActivity.sAdapter.notifyDataSetChanged();
            }
        });
        Button buttonBack = v.findViewById(R.id.back);
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.toAddFragment();
            }
        });
        return v;
    }
    public void onStart(){
        super.onStart();
        if(student!=null){
            editDodik.setText(String.valueOf(student.getMark()));
            editFio.setText(student.getFio());
        }
    }
}