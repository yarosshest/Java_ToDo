package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {
    Note findByTitle(String title);
    List<Note> findAllByIdOwn(Long id);
}