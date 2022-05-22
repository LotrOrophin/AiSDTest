package com.example.myapplication;

public class Student {
    private String fio;
    private int mark;

    public Student(String fio, int mark) {
        this.fio = fio;
        this.mark = mark;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    @Override
    public String toString() {
        return fio + ":" + mark;
    }

    public static Student parse(String str) {
        String[] params = str.split(":");
        return new Student(params[0], Integer.parseInt(params[1]));
    }
}
