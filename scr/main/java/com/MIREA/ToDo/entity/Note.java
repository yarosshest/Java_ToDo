package com.MIREA.ToDo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String data;
    private String text;

    public Note() {
    }
    public Note(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public Long GetId() {
        return id;
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

}
