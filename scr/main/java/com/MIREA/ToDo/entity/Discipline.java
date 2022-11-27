package com.MIREA.ToDo.entity;

import javax.persistence.*;

@Entity
@Table(name = "t_discipline")
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String discipline;

    public Discipline() {
    }

    public Discipline(String discipline) {
        this.discipline = discipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public Long getId() {
        return id;
    }
}
