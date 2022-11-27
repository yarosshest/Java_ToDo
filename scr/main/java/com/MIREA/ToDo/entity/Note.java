package com.MIREA.ToDo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long idOwn;

    private String discipline;
    private String title;
    private String data;
    private String text;

    public Note() {
    }
    public Note(Long idOwn, String title, String text, String discipline) {
        this.idOwn = idOwn;
        this.title = title;
        this.text = text;
        this.discipline = discipline;
    }

    public Long GetId() {
        return id;
    }

    public Long getIdOwn() {
        return idOwn;
    }

    public void setIdOwn(Long idOwn) {
        this.idOwn = idOwn;
    }

    public String GetTitle() {
        return title;
    }

    public String GetData() {
        return data;
    }

    public String GetText() {
        return text;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setData(String data) {
        this.data = data;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }
}
