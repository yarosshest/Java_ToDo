package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DayRepository extends JpaRepository<Day, Long> {
}
