package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DayRepository extends JpaRepository<Day, Long> {
    Optional<Day> findByGroup(String group);
}
