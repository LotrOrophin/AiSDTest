package com.example.myapplication;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class ButtonFragment extends Fragment {
    private MainActivity mainActivity;

    public ButtonFragment(MainActivity mainActivity) {
        // Required empty public constructor
        this.mainActivity = mainActivity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_button, container, false);
        EditText editTextFio = v.findViewById((R.id.fio));
        EditText editTextDodik = v.findViewById((R.id.dodik));
        Button buttonAdd = v.findViewById(R.id.add);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String fio = editTextFio.getText().toString();
                int dodik = Integer.parseInt(editTextDodik.getText().toString());
                Student student = new Student(fio,dodik);
                mainActivity.students.add(student);
                mainActivity.sAdapter.notifyDataSetChanged();
            }
        });
        Button buttonReport = v.findViewById(R.id.report);
        buttonReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.report(Integer.parseInt(editTextDodik.getText().toString()));
            }
        });
        return v;
    }
}