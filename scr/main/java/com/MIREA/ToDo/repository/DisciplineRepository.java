package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    boolean existsByDiscipline(String discipline);
    Discipline findAllByDiscipline(String discipline);
    Discipline findAllById(Long id);
}
