package com.MIREA.ToDo.entity;

import data.PairParser;

import javax.persistence.*;

@Entity
@Table(name = "t_pair")
public class Pair {
    public static final int ODD_WEEK = 1, EVEN_WEEK = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String studygr;

    private int weekday;
    private int parity;

    private int number;
    private String begin;
    private String end_t;

    private String discipline;
    private Long discipline_id;
    private String type_occupation;
    private String teacher;

    public Pair() {
    }

    public Pair(String group, int weekday, int parity, Long discipline_id, PairParser pairParser) {
        this.weekday = weekday;
        this.parity = parity;
        this.discipline_id = discipline_id;
        this.number = (int)Float.parseFloat(pairParser.GetNumber());
        this.begin = pairParser.GetBegin();
        this.end_t = pairParser.GetEnd();
        this.discipline = pairParser.GetDiscipline(parity);
        this.type_occupation = pairParser.GetTypeOccupation(parity);
        this.teacher = pairParser.GetTeacher(parity);
        this.studygr = group;
    }

    public int getWeekday() {
        return weekday;
    }
    public int getParity() {
        return parity;
    }
    public int getNumber() {
        return number;
    }
    public String getDiscipline() {
        return discipline;
    }
    public Long getDisciplineId() {
        return discipline_id;
    }

    public int compareTo(Pair pr) {

        return this.number - pr.number;
    }
}
