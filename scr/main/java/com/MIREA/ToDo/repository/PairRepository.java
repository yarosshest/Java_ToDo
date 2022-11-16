package com.MIREA.ToDo.repository;

import com.MIREA.ToDo.entity.Pair;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PairRepository extends JpaRepository<Pair, Long> {
    List<Pair> findAllByStudygr(String studygr);
}
