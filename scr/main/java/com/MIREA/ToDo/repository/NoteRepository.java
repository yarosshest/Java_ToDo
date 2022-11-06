package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByTitle(String title);
}