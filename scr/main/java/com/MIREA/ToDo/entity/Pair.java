package com.MIREA.ToDo.entity;

import data.PairParser;

import javax.persistence.*;

@Entity
@Table(name = "t_pair")
public class Pair {
    public static final int ODD_WEEK = 0, EVEN_WEEK = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String group;

    private int weekday;
    private int parity;

    private String number;
    private String begin;
    private String end;

    private String discipline;
    private String type_occupation;
    private String teacher;

    public Pair() {
    }

    public Pair(String group, int weekday, int parity, PairParser pairParser) {
        this.weekday = weekday;
        this.parity = parity;
        this.number = pairParser.GetNumber();
        this.begin = pairParser.GetBegin();
        this.end = pairParser.GetEnd();
        this.discipline = pairParser.GetDiscipline(parity);
        this.type_occupation = pairParser.GetTypeOccupation(parity);
        this.teacher = pairParser.GetTeacher(parity);
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public int getWeekday() {
        return weekday;
    }

    public void setWeekday(int weekday) {
        this.weekday = weekday;
    }

    public int getParity() {
        return parity;
    }

    public void setParity(int parity) {
        this.parity = parity;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getBegin() {
        return begin;
    }

    public void setBegin(String begin) {
        this.begin = begin;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getType_occupation() {
        return type_occupation;
    }

    public void setType_occupation(String type_occupation) {
        this.type_occupation = type_occupation;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }
}
